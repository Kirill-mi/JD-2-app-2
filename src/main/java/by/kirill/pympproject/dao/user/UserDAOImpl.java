package by.kirill.pympproject.dao.user;

import by.kirill.pympproject.bean.RegistrationInfo;
import by.kirill.pympproject.bean.User;
import by.kirill.pympproject.dao.DAOException;
import by.kirill.pympproject.dao.connection.ConnectionPoolOld;

import java.sql.*;

import java.util.Optional;

public class UserDAOImpl implements UserDAO {
    private final static String SQL_ADD_USER = "INSERT INTO user ( name , pass,role,email,status) Values (?,?,?,?,?)";
    private final static String SQL_UPDATE_USER = "UPDATE  user set name=?,pass=? WHERE email=?";
    private final static String SQL_DELETE_USER = "DELETE * FROM user WHERE email=?";
    private final static String SQL_READ_USER = "SELECT * FROM user WHERE email=?";

    @Override
    public boolean add(User user) throws DAOException {
        int rows;
        String name = user.getName();
        String password = user.getPass();
        String role = user.getRole();
        String email = user.getEmail();
        String status = user.getStatus();
        try (Connection connection = ConnectionPoolOld.getInstance().takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_ADD_USER)) {
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

    @Override
    public boolean update(User user) throws DAOException {
        int rows;
        String name = user.getName();
        String password = user.getPass();
        String email = user.getEmail();
        try (Connection connection = ConnectionPoolOld.getInstance().takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_USER)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, email);
            rows = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return rows != 0;
    }

    @Override
    public boolean deleteUser(RegistrationInfo registrationInfo) throws DAOException {
        String email = registrationInfo.getEmail();
        int rows;
        try (Connection connection = ConnectionPoolOld.getInstance().takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_USER)) {
            preparedStatement.setString(1, email);
            rows = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return rows != 0;
    }

    @Override
    public Optional<User> readUser(String email) throws DAOException {
        try (Connection connection = ConnectionPoolOld.getInstance().takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_READ_USER)) {
            preparedStatement.setString(1, email);
            try (ResultSet result = preparedStatement.executeQuery()) {
                if (result.next()) {
                    String name = result.getString(1);
                    String password = result.getString(2);
                    String emailFromBase = result.getString(4);
                    String role = result.getString(3);
                    return Optional.of(new User(name, password, emailFromBase, role));
                }
            } catch (SQLException e) {
                throw new DAOException(e);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return Optional.empty();
    }
}
