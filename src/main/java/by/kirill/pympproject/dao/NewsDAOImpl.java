package by.kirill.pympproject.dao;

import by.kirill.pympproject.bean.News;
import by.kirill.pympproject.bean.User;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class NewsDAOImpl implements NewsDAO {
    private final static String sqlReadNews = "SELECT * FROM news WHERE date >=?";

    @Override
    public boolean create(User user) throws DAOException {
        return false;
    }

    @Override
    public boolean deleteNews(String title) throws DAOException {
        return false;
    }

    @Override
    public boolean update(News news) throws DAOException {
        return false;
    }

    @Override
    public List<News> readNews(LocalDate date) throws DAOException {
        List<News> newsArrayList = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlReadNews)) {
            preparedStatement.setDate(1, Date.valueOf(date));
            try (ResultSet result = preparedStatement.executeQuery()) {
                while (result.next()) {
                    String title = result.getString(2);
                    String brief = result.getString(3);
                    Date dateFromBase = result.getDate(4);
                    newsArrayList.add(new News(title, brief, dateFromBase));
                }
            } catch (SQLException e1) {
                throw new DAOException(e1);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return newsArrayList;
    }
}