package by.kirill.pympproject.dao;

import by.kirill.pympproject.bean.News;
import org.apache.log4j.Logger;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class NewsDAOImpl implements NewsDAO {
    private final static String SQL_READ_LAST_NEWS = "SELECT * FROM news WHERE date >=?";
    private final static String SQL_READ_NEWS = "SELECT * FROM news WHERE title=?";
    private final static String SQL_ADD_NEWS = "INSERT INTO news ( title ,brief,date,text,author) Values (?,?,?,?,?)";
    private final static String SQL_UPDATE_NEWS = "UPDATE  news set text=?,brief=?,date=? WHERE title=?";
    private final static String SQL_DELETE_NEWS = "DELETE * FROM news WHERE title=?";
    private static final Logger logger = Logger.getLogger(UserDAOImpl.class);

    @Override
    public boolean create(News news) throws DAOException {
        int rows;
        String title = news.getTitle();
        String brief = news.getBrief();
        String text = news.getText();
        LocalDate date = news.getDate();
        String author = news.getAuthor();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_ADD_NEWS)) {
            preparedStatement.setString(1, title);
            preparedStatement.setString(2, brief);
            preparedStatement.setDate(3, Date.valueOf(date));
            preparedStatement.setString(4, text);
            preparedStatement.setString(5, author);
            rows = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.warn(e.getMessage());
            e.printStackTrace();
            throw new DAOException(e);
        }
        return rows != 0;
    }

    @Override
    public boolean deleteNews(String title) throws DAOException {
        int rows;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_NEWS)) {
            preparedStatement.setString(1, title);
            rows = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.warn(e.getMessage());
            e.printStackTrace();
            throw new DAOException(e);
        }
        return rows != 0;
    }

    @Override
    public boolean update(News news) throws DAOException {
        int rows;
        String title = news.getTitle();
        String text = news.getText();
        String brief = news.getBrief();
        LocalDate date = news.getDate();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_NEWS)) {
            preparedStatement.setString(1, text);
            preparedStatement.setString(2, brief);
            preparedStatement.setDate(2, Date.valueOf(date));
            preparedStatement.setString(3, title);
            rows = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.warn(e.getMessage());
            e.printStackTrace();
            throw new DAOException(e);
        }
        return rows != 0;
    }

    @Override
    public News read(String title) throws DAOException {
        News news;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_READ_NEWS)) {
            preparedStatement.setString(1, title);
            try (ResultSet result = preparedStatement.executeQuery()) {
                String titleFromBase = result.getString(2);
                String brief = result.getString(3);
                LocalDate date = result.getDate(4).toLocalDate();
                String text = result.getString(5);
                String author = result.getString(6);
                news = new News.Builder()
                        .setTitle(titleFromBase)
                        .setBrief(brief)
                        .setDate(date)
                        .setText(text)
                        .setAuthor(author)
                        .build();
            } catch (SQLException e) {
                logger.warn(e.getMessage());
                e.printStackTrace();
                throw new DAOException(e);
            }
        } catch (SQLException e) {
            logger.warn(e.getMessage());
            e.printStackTrace();
            throw new DAOException(e);
        }
        return news;
    }

    @Override
    public List<News> readLastNews(LocalDate date) throws DAOException {
        List<News> newsArrayList = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_READ_LAST_NEWS)) {
            preparedStatement.setDate(1, Date.valueOf(date));
            try (ResultSet result = preparedStatement.executeQuery()) {
                while (result.next()) {
                    String title = result.getString(2);
                    String brief = result.getString(3);
                    LocalDate dateFromBase = result.getDate(4).toLocalDate();
                    String text = result.getString(5);
                    String author = result.getString(6);
                    newsArrayList.add(new News.Builder()
                            .setTitle(title)
                            .setBrief(brief)
                            .setDate(dateFromBase)
                            .setText(text)
                            .setAuthor(author)
                            .build());
                }
            } catch (SQLException e) {
                logger.warn(e.getMessage());
                e.printStackTrace();
                throw new DAOException(e);
            }
        } catch (SQLException e) {
            logger.warn(e.getMessage());
            e.printStackTrace();
            throw new DAOException(e);
        }
        return newsArrayList;
    }
}
