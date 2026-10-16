package ru.itis.repository;

import ru.itis.models.User;

import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsersRepositoryJdbcImpl implements UsersRepository{

    private DataSource dataSource;

    private static final String SQL_INSERT_INTO_ACCOUNT = "insert into account (email, first_name, last_name, password) values (?,?,?,?)";
    private static final String SQL_SELECT_ALL_FROM_ACCOUNT = "select * from account";

    public UsersRepositoryJdbcImpl (DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void save(User entity) throws SQLException {
//        System.out.println(entity.getName());
        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_INTO_ACCOUNT);
        preparedStatement.setString(1, entity.getEmail());
        preparedStatement.setString(2, entity.getName());
        preparedStatement.setString(3, entity.getSurname());
        preparedStatement.setString(4, entity.getPassword());
        preparedStatement.executeUpdate();
    }

    @Override
    public List<User> findAll() throws SQLException {
        Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_FROM_ACCOUNT);
        List<User> result = new ArrayList<>();
        while (resultSet.next()) {
            User user = User.builder()
                    .id(resultSet.getLong("id"))
                    .name(resultSet.getString("first_name"))
                    .surname(resultSet.getString("last_name"))
                    .build();
            result.add(user);
        }
        return result;
    }

}
