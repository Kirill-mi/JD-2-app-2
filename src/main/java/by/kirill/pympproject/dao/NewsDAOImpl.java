package by.kirill.pympproject.dao;

import by.kirill.pympproject.bean.News;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class NewsDAOImpl implements NewsDAO {
    private final static String SQL_READ_NEWS = "SELECT * FROM news WHERE date >=?";
    private final static String SQL_ADD_NEWS = "INSERT INTO news ( title ,brief,text,date) Values (?,?,?,?)";

    @Override
    public boolean create(News news) throws DAOException {
        int rows;
        String title = news.getTitle();
        String brief = news.getBrief();
        String text = news.getText();
        LocalDate date = news.getDate();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_ADD_NEWS)) {
            preparedStatement.setString(1, title);
            preparedStatement.setString(2, brief);
            preparedStatement.setString(3, text);
            preparedStatement.setDate(4, Date.valueOf(date));
            rows = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return rows != 0;
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
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_READ_NEWS)) {
            preparedStatement.setDate(1, Date.valueOf(date));
            try (ResultSet result = preparedStatement.executeQuery()) {
                while (result.next()) {
                    String title = result.getString(2);
                    String brief = result.getString(3);
                    LocalDate dateFromBase = result.getDate(4).toLocalDate();
                    String text = result.getString(5);
                    newsArrayList.add(new News.Builder()
                            .setTitle(title)
                            .setBrief(brief)
                            .setDate(dateFromBase)
                            .setText(text)
                            .build());
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