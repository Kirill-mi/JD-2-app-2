package by.kirill.pympproject.service;

import by.kirill.pympproject.bean.News;

import java.util.ArrayList;

public interface NewsService {
    boolean create(String title,String text);

    boolean delete(String title);

    void update(String title);

    News read(String title);

    ArrayList<News> getLastNews() throws ServiceException;
}
