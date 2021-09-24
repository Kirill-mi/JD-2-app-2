package by.kirill.pympproject.dao.news;

import by.kirill.pympproject.bean.News;
import by.kirill.pympproject.dao.DAOException;
import by.kirill.pympproject.dao.connection.ConnectionPool;
import by.kirill.pympproject.dao.connection.ConnectionPoolException;


import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class NewsDAOImpl implements NewsDAO {
    private final static String SQL_READ_LAST_NEWS = "SELECT SQL_CALC_FOUND_ROWS * FROM news limit ?,?";
    private final static String SQL_READ_NEWS = "SELECT * FROM news WHERE news_title=?";
    private final static String SQL_ADD_NEWS = "INSERT INTO news ( title ,brief,date,text,author) Values (?,?,?,?,?)";
    private final static String SQL_UPDATE_NEWS = "UPDATE  news set text=?,brief=?,date=? WHERE title=?";
    private final static String SQL_DELETE_NEWS = "DELETE * FROM news WHERE title=?";
    private int noOfRecords;

    @Override
    public boolean create(News news) throws DAOException {
        int rows;
        String title = news.getTitle();
        String brief = news.getBrief();
        String text = news.getText();
        LocalDate date = news.getDate();
        String author = news.getAuthor();
        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_ADD_NEWS)) {
            preparedStatement.setString(1, title);
            preparedStatement.setString(2, brief);
            preparedStatement.setDate(3, Date.valueOf(date));
            preparedStatement.setString(4, text);
            preparedStatement.setString(5, author);
            rows = preparedStatement.executeUpdate();
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException(e);
        }
        return rows != 0;
    }

    @Override
    public boolean deleteNews(String title) throws DAOException {
        int rows;
        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_NEWS)) {
            preparedStatement.setString(1, title);
            rows = preparedStatement.executeUpdate();
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException(e);
        }
        return rows != 0;
    }

    @Override
    public boolean update(String title, String text) throws DAOException {
        int rows;
        String brief = text.trim().substring(0, text.length() / 10);
        LocalDate date = LocalDate.now();
        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_NEWS)) {
            preparedStatement.setString(1, text.trim());
            preparedStatement.setString(2, brief);
            preparedStatement.setDate(3, Date.valueOf(date));
            preparedStatement.setString(4, title.trim());
            rows = preparedStatement.executeUpdate();
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException(e);
        }
        return rows != 0;
    }

    @Override
    public News read(String title) throws DAOException {
        News news;
        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_READ_NEWS)) {
            preparedStatement.setString(1, title);
            try (ResultSet result = preparedStatement.executeQuery()) {
                int newsId = result.getInt(1);
                String titleFromBase = result.getString(2);
                String brief = result.getString(3);
                LocalDate date = result.getDate(4).toLocalDate();
                String text = result.getString(5);
                String author = result.getString(6);
                news = new News.Builder()
                        .setNewsId(newsId)
                        .setTitle(titleFromBase)
                        .setBrief(brief)
                        .setDate(date)
                        .setText(text)
                        .setAuthor(author)
                        .build();
            } catch (SQLException e) {
                throw new DAOException(e);
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException(e);
        }
        return news;
    }

    @Override
    public List<News> readLastNews(int offset, int noOfRecords) throws DAOException {
        List<News> newsArrayList = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_READ_LAST_NEWS);
             PreparedStatement preparedStatement1 = connection.prepareStatement("SELECT FOUND_ROWS()")) {
            preparedStatement.setInt(1, offset);
            preparedStatement.setInt(2, noOfRecords);
            try (ResultSet result = preparedStatement.executeQuery();
                 ResultSet result1 = preparedStatement1.executeQuery()) {
                if (result1.next())
                    this.noOfRecords = result1.getInt(1);
                while (result.next()) {
                    int newsId = result.getInt(1);
                    String title = result.getString(2);
                    String brief = result.getString(3);
                    LocalDate dateFromBase = result.getDate(4).toLocalDate();
                    String text = result.getString(5);
                    String author = result.getString(6);
                    newsArrayList.add(new News.Builder()
                            .setNewsId(newsId)
                            .setTitle(title)
                            .setBrief(brief)
                            .setDate(dateFromBase)
                            .setText(text)
                            .setAuthor(author)
                            .build());
                }
            } catch (SQLException e) {
                throw new DAOException(e);
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException(e);
        }
        return newsArrayList;
    }

    @Override
    public int getNoOfRecords() {
        return noOfRecords;
    }
}
