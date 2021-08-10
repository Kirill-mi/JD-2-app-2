package by.kirill.pympproject.dao;

import by.kirill.pympproject.bean.News;
import by.kirill.pympproject.bean.User;

import java.time.LocalDate;
import java.util.List;


public interface NewsDAO {
    boolean create(User user) throws DAOException;

    boolean deleteNews(String title) throws DAOException;

    boolean update(News news) throws DAOException;

  List<News> readNews(LocalDate date) throws DAOException;
}
