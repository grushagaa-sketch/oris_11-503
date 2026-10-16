package ru.itis.services;

import ru.itis.models.User;
import ru.itis.repositories.UserRepository;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService{

    private UserRepository usersRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.usersRepository = userRepository;
    }

    @Override
    public List<User> getAll() throws SQLException {
        return usersRepository.findAll();
    }

    @Override
    public void addUser(User user) throws SQLException {

    }
}
