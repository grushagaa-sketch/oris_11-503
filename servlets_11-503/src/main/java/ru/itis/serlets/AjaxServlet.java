package ru.itis.serlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.itis.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import java.sql.Connection;
import java.sql.SQLException;

public class AjaxServlet extends HttpServlet {
    private UserService userService;
    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public void init() throws ServletException {

    }
}
