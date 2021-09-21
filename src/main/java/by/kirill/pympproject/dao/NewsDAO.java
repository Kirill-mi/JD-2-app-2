package by.kirill.pympproject.dao;

import by.kirill.pympproject.bean.News;

import java.util.List;


public interface NewsDAO {
    boolean create(News news) throws DAOException;

    boolean deleteNews(String title) throws DAOException;

    boolean update(String title, String text) throws DAOException;

    News read(String title) throws DAOException;

    List<News> readLastNews(int offset, int noOfRecords) throws DAOException;

    int getNoOfRecords();
}
