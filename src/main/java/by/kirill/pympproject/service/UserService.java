package by.kirill.pympproject.service;


import by.kirill.pympproject.bean.RegistrationInfo;
import by.kirill.pympproject.bean.User;

public interface UserService {
    String createUser(RegistrationInfo registrationInfo) throws ServiceException;

    boolean deleteUser(RegistrationInfo registrationInfo) throws ServiceException;

    boolean validateUser(RegistrationInfo registrationInfo) throws ServiceException;

    boolean updateUser(RegistrationInfo registrationInfo) throws ServiceException;

}
