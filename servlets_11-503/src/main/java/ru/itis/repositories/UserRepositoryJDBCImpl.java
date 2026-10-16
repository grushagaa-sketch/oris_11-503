package ru.itis.repositories;

import ru.itis.models.User;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepositoryJDBCImpl implements UserRepository{
    private Connection connection;

    UserRepositoryJDBCImpl(Connection connection) {
        this.connection = connection;
    }

    private static final String SQL_INSERT_INTO = "insert into account (email, first_name, last_name) values (?, ?, ?)";
    private static final String SELECT_ALL_FROM_ACCOUNT = "select * from account";


    @Override
    public void save(User entity) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_INTO);
        preparedStatement.setString(1, entity.getEmail());
        preparedStatement.setString(2, entity.getName());
        preparedStatement.setString(3, entity.getSurname());
        preparedStatement.executeUpdate();
        System.out.println(entity);
    }

    public List findAll() throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(SELECT_ALL_FROM_ACCOUNT);
        List<User> result = new ArrayList<>();
        while (resultSet.next()) {
            User user = new User().builder()
                    .id(resultSet.getLong("id"))
                    .name(resultSet.getString("first_name"))
                    .surname(resultSet.getString("last_name"))
                    .build();
            result.add(user);
        }
        return result;
    }
}
