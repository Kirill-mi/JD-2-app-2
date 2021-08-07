package by.kirill.pympproject.service;

import by.kirill.pympproject.bean.RegistrationInfo;
import by.kirill.pympproject.bean.User;
import by.kirill.pympproject.DAO.DAOException;
import by.kirill.pympproject.DAO.DaoProvider;
import by.kirill.pympproject.DAO.UserDao;

public class UserServiceImpl implements UserService {
    private final static DaoProvider daoProvider = DaoProvider.getInstance();
    private final UserDao userDao = daoProvider.getUserDao();
    private final String incorrectName = "Enter correct name";
    private final String incorrectPass = "Enter correct password";
    private final String incorrectEmail = "Enter correct email";
    private final String userAdded = "User added";


    @Override
    public String createUser(RegistrationInfo registrationInfo) throws ServiceException {
        String name = registrationInfo.getName();
        String pass = registrationInfo.getPass();
        String controlPass = registrationInfo.getControlPass();
        String email = registrationInfo.getEmail();

        if (checkStringLine(name)) {
            return incorrectName;
        }
        if (checkEmail(email)) {
            return incorrectEmail;
        }
        if (checkStringLine(pass)) {
            return incorrectPass;
        }
        if (checkPassword(pass, controlPass)) {
            return incorrectPass;
        }
        User user = new User(name, pass, email);
        try {
            userDao.create(user);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return userAdded;
    }

    @Override
    public boolean deleteUser(RegistrationInfo registrationInfo) throws ServiceException {
        String pass = registrationInfo.getPass();
        String email = registrationInfo.getEmail();
        boolean flag = false;
        try {
            User userFromDAO = userDao.readUser(email);
            if (userFromDAO != null && userFromDAO.getPass().equals(pass)) {
                userDao.deleteUser(email);
                flag = true;
            }
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return flag;
    }

    @Override
    public boolean validateUser(RegistrationInfo registrationInfo) throws ServiceException {
        String pass = registrationInfo.getPass();
        String email = registrationInfo.getEmail();
        boolean flag = false;
        try {
            User userFromBase = userDao.readUser(email);
            if (userFromBase != null && userFromBase.getPass().equals(pass)) {
                flag = true;
            }
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return flag;
    }

    @Override
    public boolean updateUser(RegistrationInfo registrationInfo) throws ServiceException {
        return false;
    }

    private boolean checkStringLine(String name) {
        return name == null || name.isEmpty() || name.length() > 50;
    }

    private boolean checkPassword(String pass, String controlPass) {
        return controlPass == null || !controlPass.equals(pass);
    }

    private boolean checkEmail(String email) {
        return email == null || email.isEmpty() || email.length() > 100;
    }
}
