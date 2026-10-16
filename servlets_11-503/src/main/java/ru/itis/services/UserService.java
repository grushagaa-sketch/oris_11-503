package ru.itis.services;

import ru.itis.models.User;

import java.sql.SQLException;
import java.util.List;

public interface UserService {
    List<User> getAll() throws SQLException;
    public void addUser(User user) throws SQLException;
}
