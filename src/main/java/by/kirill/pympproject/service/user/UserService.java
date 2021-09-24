package by.kirill.pympproject.service.user;

import by.kirill.pympproject.bean.RegistrationInfo;
import by.kirill.pympproject.bean.User;
import by.kirill.pympproject.service.ServiceException;

import java.util.Optional;

public interface UserService {
    String createUser(RegistrationInfo registrationInfo) throws ServiceException;

    boolean deleteUser(RegistrationInfo registrationInfo) throws ServiceException;

    boolean validateUser(RegistrationInfo registrationInfo) throws ServiceException;

    boolean updateUser(RegistrationInfo registrationInfo) throws ServiceException;
    Optional<User> readUser(String email) throws ServiceException;

}
