package by.kirill.pympproject.DAO;

import by.kirill.pympproject.bean.User;

import java.sql.*;

public class UserDaoImpl implements UserDao {
    @Override
    public boolean create(User user) throws DAOException {
        int rows;
        String name = user.getName();
        String password = user.getPass();
        String role = user.getRole();
        String email = user.getEmail();
        String status = user.getStatus();
        String sql = "INSERT INTO user ( name , pass,role,email,status) Values (?,?,?,?,?)";
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

    @Override
    public boolean deleteUser(String email) throws DAOException {
        String sql = "DELETE * FROM user WHERE email=?";
        int rows;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, email);
            rows = preparedStatement.executeUpdate();
        } catch (SQLException e1) {
            throw new DAOException(e1);
        }
        return rows != 0;
    }

    @Override
    public boolean update(User user) throws DAOException {
        return false;
    }

    @Override
    public User readUser(String email) throws DAOException {
        String sql = "SELECT * FROM user WHERE email=?";
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, email);
            try (ResultSet result = preparedStatement.executeQuery()) {
                if (result.next()) {
                    String name = result.getString(1);
                    String password = result.getString(2);
                    String emailFromBase = result.getString(4);
                    return new User(name, password, emailFromBase);
                }
            } catch (SQLException e1) {
                throw new DAOException(e1);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return null;
    }
}