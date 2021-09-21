package by.kirill.pympproject.service;

import by.kirill.pympproject.bean.News;

import java.util.ArrayList;

public interface NewsService {
    boolean createNews(String title, String text, String author) throws ServiceException;

    boolean deleteNews(String title) throws ServiceException;

    boolean updateNews(String title, String tex) throws ServiceException;

    News read(String title) throws ServiceException;

    ArrayList<News> getLastNews(int offset,int noOfRecords) throws ServiceException;

    int getNoOfRecords();
}
