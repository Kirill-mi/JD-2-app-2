package by.kirill.pympproject.dao.user;

import by.kirill.pympproject.bean.RegistrationInfo;
import by.kirill.pympproject.bean.User;
import by.kirill.pympproject.dao.DAOException;

import java.util.Optional;

public interface UserDAO {
    boolean add(User user) throws DAOException;
    boolean deleteUser(RegistrationInfo registrationInfo) throws DAOException;
    boolean update(User user) throws DAOException;
    Optional<User> readUser(String email) throws DAOException;

}
