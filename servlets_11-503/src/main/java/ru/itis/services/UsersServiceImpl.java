package ru.itis.services;

import ru.itis.models.User;
import ru.itis.repository.UsersRepository;

import java.sql.SQLException;
import java.util.List;

public class UsersServiceImpl implements UsersService {

    private UsersRepository usersRepository;

    public UsersServiceImpl(UsersRepository userRepository) {
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
