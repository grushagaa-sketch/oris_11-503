package ru.itis.serlets;

import ru.itis.models.User;
import ru.itis.repository.UsersRepository;
import ru.itis.repository.UsersRepositoryJdbcImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@WebServlet("/save")
public class SaveServlet extends HttpServlet {
    private static final String DB_USERNAME = "postgres";
    private static final String DB_PASSWORD = "Chucha2011!";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/testdb";
    private UsersRepository userRepository;

    @Override
    public void init() throws ServletException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            userRepository = new UsersRepositoryJdbcImpl(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int cnt = userRepository.getUsersToAddCount();
            request.setAttribute("count", cnt);
            request.getRequestDispatcher("/jsp/add_users.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        try {
            int count = userRepository.getUsersToAddCount();

            for (int i = 1; i <= count; i++) {
                String name = request.getParameter("name_" + i);
                String surname = request.getParameter("surname_" + i);
                int age = Integer.parseInt(request.getParameter("age_" + i));
                String city = request.getParameter("city_" + i);
                String email = request.getParameter("email_" + i);
                String phone = request.getParameter("phone_" + i);
                String profession = request.getParameter("profession_" + i);

                User user = new User(null, name, surname, age, email, phone, city, profession);
                userRepository.save(user);
            }

            response.sendRedirect("/html/successful_add.html");
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
}
