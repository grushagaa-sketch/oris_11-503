package ru.itis.repositories;

import ru.itis.models.User;

import java.sql.SQLException;
import java.util.List;

public interface UserRepository<T> extends CrudRepository<User>{
    List<T> findAll() throws SQLException;
}
