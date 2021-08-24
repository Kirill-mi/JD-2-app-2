package by.kirill.pympproject.service;

import by.kirill.pympproject.bean.RegistrationInfo;
import by.kirill.pympproject.bean.User;
import by.kirill.pympproject.dao.DAOException;
import by.kirill.pympproject.dao.DaoProvider;
import by.kirill.pympproject.dao.UserDAO;

import org.apache.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;

import java.util.Optional;

public class UserServiceImpl implements UserService {
    private final static DaoProvider daoProvider = DaoProvider.getInstance();
    private final UserDAO userDao = daoProvider.getUserDao();
    private static final Logger logger = Logger.getLogger(UserServiceImpl.class);
    private final static String INCORRECT_NAME = "Enter correct name";
    private final static String INCORRECT_PASS = "Enter correct password";
    private final static String INCORRECT_EMAIL = "Enter correct email";
    private final static String USER_ADDED = "User added";
    private final static String USER_EXISTS = "User exists";

    @Override
    public String createUser(RegistrationInfo registrationInfo) throws ServiceException {
        String name = registrationInfo.getName();
        String pass = registrationInfo.getPass();
        String controlPass = registrationInfo.getControlPass();
        String email = registrationInfo.getEmail();

        if (checkStringLine(name)) {
            return INCORRECT_NAME;
        }
        if (checkEmail(email)) {
            return INCORRECT_EMAIL;
        }
        if (checkStringLine(pass)) {
            return INCORRECT_PASS;
        }
        if (checkPassword(pass, controlPass)) {
            return INCORRECT_PASS;
        }
        try {
            if (!userDao.readUser(email).isPresent()) {
                String hashedPass = BCrypt.hashpw(pass, BCrypt.gensalt());
                User user = new User(name, hashedPass, email);
                userDao.add(user);
            } else {
                return USER_EXISTS;
            }
        } catch (DAOException e) {
            logger.warn(e.getMessage());
            throw new ServiceException(e);
        }
        return USER_ADDED;
    }

    @Override
    public boolean deleteUser(RegistrationInfo registrationInfo) throws ServiceException {
        String pass = registrationInfo.getPass();
        String email = registrationInfo.getEmail();
        boolean flag = false;
        try {
            Optional<User> userFromDAO = userDao.readUser(email);
            if (userFromDAO.isPresent() && BCrypt.checkpw(pass, userFromDAO.get().getPass())) {
                userDao.deleteUser(registrationInfo);
                flag = true;
            }
        } catch (DAOException e) {
            logger.warn(e.getMessage());
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
            Optional<User> userFromDAO = userDao.readUser(email);
            if (userFromDAO.isPresent() && BCrypt.checkpw(pass, userFromDAO.get().getPass())) {
                flag = true;
            }
        } catch (DAOException e) {
            logger.warn(e.getMessage());
            throw new ServiceException(e);
        }
        return flag;
    }

    @Override
    public boolean updateUser(RegistrationInfo registrationInfo) throws ServiceException {
        String name = registrationInfo.getName();
        String pass = registrationInfo.getPass();
        String controlPass = registrationInfo.getControlPass();
        String email = registrationInfo.getEmail();
        if (checkStringLine(name)) {
            return false;
        }
        if (checkEmail(email)) {
            return false;
        }
        if (checkStringLine(pass)) {
            return false;
        }
        String hashedPass = BCrypt.hashpw(controlPass, BCrypt.gensalt());
        User user = new User(name, hashedPass, email);
        try {
            userDao.update(user);
        } catch (DAOException e) {
            logger.warn(e.getMessage());
            throw new ServiceException(e);
        }
        return true;
    }

    @Override
    public Optional<User> readUser(String email) throws ServiceException {
        try {
            return userDao.readUser(email);
        } catch (DAOException e) {
            e.printStackTrace();
        }
        return Optional.empty();
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
