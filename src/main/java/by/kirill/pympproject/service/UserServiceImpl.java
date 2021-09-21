package by.kirill.pympproject.service;

import by.kirill.pympproject.bean.RegistrationInfo;
import by.kirill.pympproject.bean.User;
import by.kirill.pympproject.dao.DAOException;
import by.kirill.pympproject.dao.UserDAO;
import org.mindrot.jbcrypt.BCrypt;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserServiceImpl implements UserService {

    private final static String INVALID_NAME = "Enter correct name";
    private final static String INVALID_PASS = "Enter correct password";
    private final static String INVALID_EMAIL = "Enter correct email";
    private final static String USER_ADDED = "User added";
    private final static String USER_EXISTS = "User exists";
    private UserDAO userDao;

    @Override
    public String createUser(RegistrationInfo registrationInfo) throws ServiceException {
        String name = registrationInfo.getName();
        String pass = registrationInfo.getPass();
        String controlPass = registrationInfo.getControlPass();
        String email = registrationInfo.getEmail();

        if (isStringLineInvalid(name)) {
            return INVALID_NAME;
        }
        if (isEmailInvalid(email)) {
            return INVALID_EMAIL;
        }
        if (isStringLineInvalid(pass)) {
            return INVALID_PASS;
        }
        if (isPasswordInvalid(pass, controlPass)) {
            return INVALID_PASS;
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
            throw new ServiceException(e);
        }
        return flag;
    }

    @Override
    public boolean validateUser(RegistrationInfo registrationInfo) throws ServiceException {
        String pass = registrationInfo.getPass();
        String email = registrationInfo.getEmail();
        boolean flag = false;
        if (isEmailInvalid(email)) {
            return false;
        }
        if (isStringLineInvalid(pass)) {
            return false;
        }
        try {
            Optional<User> userFromDAO = userDao.readUser(email);
            if (userFromDAO.isPresent() && BCrypt.checkpw(pass, userFromDAO.get().getPass())) {
                flag = true;
            }
        } catch (DAOException e) {
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
        if (isStringLineInvalid(name)) {
            return false;
        }
        if (isEmailInvalid(email)) {
            return false;
        }
        if (isStringLineInvalid(pass)) {
            return false;
        }
        String hashedPass = BCrypt.hashpw(controlPass, BCrypt.gensalt());
        User user = new User(name, hashedPass, email);
        try {
            userDao.update(user);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return true;
    }

    @Override
    public Optional<User> readUser(String email) throws ServiceException {
        try {
            return userDao.readUser(email);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    public void setUserDao(UserDAO userDao) {
        this.userDao = userDao;
    }

    private boolean isStringLineInvalid(String name) {
        return name == null || name.isEmpty() || name.length() > 50;
    }

    private boolean isPasswordInvalid(String pass, String controlPass) {
        return controlPass == null || !(controlPass.equals(pass));
    }

    private boolean isEmailInvalid(String email) {
        Pattern pattern = Pattern.compile("\\w+([.-]?\\w+)*@\\w+([.-]?\\w+)*\\.\\w{2,4}");
        Matcher matcher = pattern.matcher(email);
        boolean matches = matcher.matches();
        return !matches && email.length() <= 100;
    }
}
