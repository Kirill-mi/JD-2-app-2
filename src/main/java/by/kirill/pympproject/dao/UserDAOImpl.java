package by.kirill.pympproject.dao;

import by.kirill.pympproject.bean.RegistrationInfo;
import by.kirill.pympproject.bean.User;

import java.sql.*;
import java.util.Optional;

public class UserDAOImpl implements UserDAO {
    private final static String SQL_ADD_USER = "INSERT INTO user ( name , pass,role,email,status) Values (?,?,?,?,?)";
    private final static String SQL_UPDATE_USER = "UPDATE * FROM user WHERE email=?";
    private final static String SQL_DELETE_USER = "DELETE * FROM user WHERE email=?";
    private final static String SQL_READ_USER = "SELECT * FROM user WHERE email=?";

    @Override
    public boolean add(User user) throws DAOException {

        return enterData(user, SQL_ADD_USER);
    }

    @Override
    public boolean update(User user) throws DAOException {
        return enterData(user, SQL_UPDATE_USER);
    }

    @Override
    public boolean deleteUser(RegistrationInfo registrationInfo) throws DAOException {
        String email = registrationInfo.getEmail();
        int rows;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_USER)) {
            preparedStatement.setString(1, email);
            rows = preparedStatement.executeUpdate();
        } catch (SQLException e1) {
            throw new DAOException(e1);
        }
        return rows != 0;
    }

    @Override
    public Optional<User> readUser(String email) throws DAOException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_READ_USER)) {
            preparedStatement.setString(1, email);
            try (ResultSet result = preparedStatement.executeQuery()) {
                if (result.next()) {
                    String name = result.getString(1);
                    String password = result.getString(2);
                    String emailFromBase = result.getString(4);
                    return Optional.of(new User(name, password, emailFromBase));
                }
            } catch (SQLException e1) {
                throw new DAOException(e1);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return Optional.empty();
    }

    private boolean enterData(User user, String sql) throws DAOException {
        int rows;
        String name = user.getName();
        String password = user.getPass();
        String role = user.getRole();
        String email = user.getEmail();
        String status = user.getStatus();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, role);
            preparedStatement.setString(4, email);
            preparedStatement.setString(5, status);
            rows = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return rows != 0;
    }
}
