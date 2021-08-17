package by.kirill.pympproject.service;

import by.kirill.pympproject.bean.News;
import by.kirill.pympproject.dao.DAOException;
import by.kirill.pympproject.dao.DaoProvider;
import by.kirill.pympproject.dao.NewsDAO;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

public class NewsServiceImpl implements NewsService {
    private final static DaoProvider daoProvider = DaoProvider.getInstance();
    private final NewsDAO newsDao = daoProvider.getNewsDao();

    @Override
    public boolean create(String title, String text) {
        News news = new News.Builder()
                .setTitle(title)
                .setBrief(text.substring(0, 200))
                .setDate(LocalDate.now())
                .setText(text)
                .build();
        try {
            newsDao.create(news);
        } catch (DAOException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(String title) {
        return false;
    }

    @Override
    public void update(String title) {

    }

    @Override
    public News read(String title) {
        return null;
    }

    @Override
    public ArrayList<News> getLastNews() throws ServiceException {
        ArrayList<News> newsArrayList;
        LocalDate date = LocalDate.now();
        try {
            newsArrayList = new ArrayList<>(newsDao.readNews(date.minusDays(1)));
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return newsArrayList;
    }
}
