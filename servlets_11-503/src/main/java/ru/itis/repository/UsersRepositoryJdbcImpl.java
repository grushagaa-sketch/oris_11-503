package ru.itis.repository;

import ru.itis.models.User;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsersRepositoryJdbcImpl implements UsersRepository {

    private DataSource dataSource;

    private static final String SQL_INSERT_INTO_ACCOUNT = "insert into account (email, first_name, last_name, password) values (?,?,?,?)";
    private static final String SQL_SELECT_ALL_FROM_ACCOUNT = "select * from account";

    public UsersRepositoryJdbcImpl (DataSource dataSource) {
        this.dataSource = dataSource;
    }


    private Connection connection;

    public UsersRepositoryJdbcImpl(Connection connection) {
        this.connection = connection;
    }

    private static final String SQL_INSERT_INTO =
            "insert into drivers (first_name, last_name, age, city, email, phone, profession, password) values (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SELECT_ALL_FROM_DRIVERS = "select * from drivers";
    private static final String SELECT_ALL_BY_PROFESSION =
            "select id, first_name, last_name, age, city, email, phone, profession from drivers where profession = ?";

    private static final String SELECT_ALL_BY_CITY =
            "select id, first_name, last_name, age, city, email, phone, profession from drivers where city = ?";

    private static final String SELECT_ALL_BY_PHONE =
            "select id, first_name, last_name, age, city, email, phone, profession from drivers where phone = ?";

    private static final String SELECT_USERS_TO_ADD = "select users_to_add from settings limit 1";

    @Override
    public void save(User entity) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(SQL_INSERT_INTO);
        ps.setString(1, entity.getName());
        ps.setString(2, entity.getSurname());
        ps.setInt(3, entity.getAge());
        ps.setString(4, entity.getCity());
        ps.setString(5, entity.getEmail());
        ps.setString(6, entity.getPhone());
        ps.setString(7, entity.getProfession());
        ps.setString(8, entity.getPassword());
        ps.executeUpdate();
    }

    @Override
    public List findAll() throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(SELECT_ALL_FROM_DRIVERS);
        List<User> result = new ArrayList<>();
        while (resultSet.next()) {
            User user = User.builder()
                    .id(resultSet.getLong("id"))
                    .name(resultSet.getString("first_name"))
                    .surname(resultSet.getString("last_name"))
                    .age(resultSet.getInt("age"))
                    .email(resultSet.getString("email"))
                    .city(resultSet.getString("city"))
                    .phone(resultSet.getString("phone"))
                    .profession(resultSet.getString("profession"))
                    .build();
            result.add(user);
        }
        return result;
    }

    @Override
    public List<User> findAllByCity(String city) throws SQLException {
        return executeQueryWithStringParam(SELECT_ALL_BY_CITY, city);
    }

    @Override
    public List<User> findAllByPhone(String phone) throws SQLException {
        return executeQueryWithStringParam(SELECT_ALL_BY_PHONE, phone);
    }

    @Override
    public List<User> findAllByProfession(String profession) throws SQLException {
        return executeQueryWithStringParam(SELECT_ALL_BY_PROFESSION, profession);
    }

    @Override
    public int getUsersToAddCount() throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(SELECT_USERS_TO_ADD);
        if (resultSet.next()) {
            return resultSet.getInt("users_to_add");
        }
        return 3;
    }

    private List<User> executeQueryWithStringParam(String sql, String param) throws SQLException {
        List<User> result = new ArrayList<>();
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, param);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            User user = User.builder()
                    .id(rs.getLong("id"))
                    .name(rs.getString("first_name"))
                    .surname(rs.getString("last_name"))
                    .age(rs.getInt("age"))
                    .city(rs.getString("city"))
                    .email(rs.getString("email"))
                    .phone(rs.getString("phone"))
                    .profession(rs.getString("profession"))

                    .build();
            result.add(user);
        }
        return result;
    }
}
