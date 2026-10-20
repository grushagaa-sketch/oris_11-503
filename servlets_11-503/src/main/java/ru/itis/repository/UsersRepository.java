package ru.itis.repository;

import ru.itis.models.User;

import java.sql.SQLException;
import java.util.List;

public interface UsersRepository extends CrudRepository<User>{
    List<User> findAll() throws SQLException;
    List<User> findAllByCity(String city) throws SQLException;
    List<User> findAllByPhone(String phone) throws SQLException;
    List<User> findAllByProfession(String profession) throws SQLException;
    int getUsersToAddCount() throws SQLException;
}
