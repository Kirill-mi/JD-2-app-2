package by.kirill.pympproject.dao;

import by.kirill.pympproject.bean.News;

import java.time.LocalDate;
import java.util.List;


public interface NewsDAO {
    boolean create(News news) throws DAOException;

    boolean deleteNews(String title) throws DAOException;

    boolean update(News news) throws DAOException;

    List<News> readNews(LocalDate date) throws DAOException;
}
