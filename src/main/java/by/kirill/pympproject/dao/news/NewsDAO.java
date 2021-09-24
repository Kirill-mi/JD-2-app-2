package by.kirill.pympproject.dao.news;

import by.kirill.pympproject.bean.Comment;
import by.kirill.pympproject.bean.News;
import by.kirill.pympproject.dao.DAOException;
import by.kirill.pympproject.service.ServiceException;

import java.util.ArrayList;
import java.util.List;


public interface NewsDAO {
    boolean create(News news) throws DAOException;

    boolean deleteNews(String title) throws DAOException;

    boolean update(String title, String text) throws DAOException;

    News read(String title) throws DAOException;

    List<News> readLastNews(int offset, int noOfRecords) throws DAOException;

    int getNoOfRecords();

}
