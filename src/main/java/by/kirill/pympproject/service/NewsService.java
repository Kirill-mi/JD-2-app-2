package by.kirill.pympproject.service;

import by.kirill.pympproject.bean.News;

import java.util.ArrayList;

public interface NewsService {
    boolean create(News news);

    boolean delete(String title);

    void update(News news);

    News read(String title);

    ArrayList<News> getLastNews() throws ServiceException;
}
