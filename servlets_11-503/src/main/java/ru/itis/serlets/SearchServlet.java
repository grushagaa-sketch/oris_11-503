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
import java.util.List;

@WebServlet("/search")
public class SearchServlet extends HttpServlet {
    private static final String DB_USERNAME = "postgres";
    private static final String DB_PASSWORD = "Chucha2011!";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/testdb";
    private UsersRepository usersRepository;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/html/search.html").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        String type = req.getParameter("filter");
        String value = req.getParameter("filterValue");

        if (type == null || value == null) {
            resp.sendRedirect("/search");
            return;
        }

        try {
            System.out.println("Пришло из формы: type=" + type + " value=" + value);
            List<User> users = null;
            if ("city".equals(type)) {
                users = usersRepository.findAllByCity(value);
            } else if ("phone".equals(type)) {
                users = usersRepository.findAllByPhone(value);
            } else if ("profession".equals(type)) {
                users = usersRepository.findAllByProfession(value);
            }


            System.out.println("результаты поиска:");
            if (users != null) {
                for (User u : users) {
                    System.out.println(u);
                }
            }
            System.out.println();

            req.getRequestDispatcher("/html/search.html").forward(req, resp);

        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    @Override
    public void init() throws ServletException {
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            usersRepository = new UsersRepositoryJdbcImpl(connection);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
