package by.kirill.pympproject.dao;

import by.kirill.pympproject.bean.RegistrationInfo;
import by.kirill.pympproject.bean.User;

import java.util.Optional;

public interface UserDAO {
    boolean add(User user) throws DAOException;
    boolean deleteUser(RegistrationInfo registrationInfo) throws DAOException;
    boolean update(User user) throws DAOException;
    Optional<User> readUser(String email) throws DAOException;

}