package by.kirill.pympproject.service;

import by.kirill.pympproject.bean.News;
import by.kirill.pympproject.dao.DAOException;
import by.kirill.pympproject.dao.DaoProvider;
import by.kirill.pympproject.dao.NewsDAO;
import org.apache.log4j.Logger;

import java.time.LocalDate;
import java.util.ArrayList;

public class NewsServiceImpl implements NewsService {
    private static final Logger LOGGER = Logger.getLogger(NewsServiceImpl.class);
    private final static DaoProvider daoProvider = DaoProvider.getInstance();
    private final NewsDAO newsDao = daoProvider.getNewsDao();

    @Override
    public boolean createNews(String title, String text, String author) throws ServiceException {
        News news = new News.Builder()
                .setTitle(title)
                .setBrief(text.substring(0, text.length() / 10))
                .setDate(LocalDate.now())
                .setText(text)
                .setAuthor(author)
                .build();
        try {
            newsDao.create(news);
        } catch (DAOException e) {
            e.printStackTrace();
            LOGGER.warn(e.getMessage());
            throw new ServiceException(e);
        }
        return false;
    }

    @Override
    public boolean deleteNews(String title) throws ServiceException {
        boolean flag;
        try {
            flag = newsDao.deleteNews(title);
        } catch (DAOException e) {
            e.printStackTrace();
            LOGGER.warn(e.getMessage());
            throw new ServiceException(e);
        }
        return flag;
    }

    @Override
    public boolean updateNews(News news) throws ServiceException {
        boolean flag;
        try {
            flag = newsDao.update(news);
        } catch (DAOException e) {
            e.printStackTrace();
            LOGGER.warn(e.getMessage());
            throw new ServiceException(e);
        }
        return flag;
    }

    @Override
    public News read(String title) throws ServiceException {
        News news;
        try {
            news = newsDao.read(title);
        } catch (DAOException e) {
            e.printStackTrace();
            LOGGER.warn(e.getMessage());
            throw new ServiceException(e);
        }
        return news;
    }

    @Override
    public ArrayList<News> getLastNews() throws ServiceException {
        ArrayList<News> newsArrayList;
        LocalDate date = LocalDate.now();
        try {
            newsArrayList = new ArrayList<>(newsDao.readLastNews(date.minusDays(20)));
        } catch (DAOException e) {
            e.printStackTrace();
            LOGGER.warn(e.getMessage());
            throw new ServiceException(e);
        }
        return newsArrayList;
    }
}
