package by.kirill.pympproject.DAO;

import by.kirill.pympproject.bean.News;
import by.kirill.pympproject.bean.User;

import java.time.LocalDate;
import java.util.ArrayList;

public interface NewsDAO {
    boolean create(User user) throws DAOException;

    boolean deleteNews(String title) throws DAOException;

    boolean update(News news) throws DAOException;

    ArrayList<News> readNews(LocalDate date) throws DAOException;
}
