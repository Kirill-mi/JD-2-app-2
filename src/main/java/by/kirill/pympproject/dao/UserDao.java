package by.kirill.pympproject.dao;

import by.kirill.pympproject.bean.User;

import java.util.Optional;

public interface UserDao {
    boolean create(User user) throws DAOException;

    boolean deleteUser(String email) throws DAOException;

    boolean update(User user) throws DAOException;

    User readUser(String email) throws DAOException;
}
