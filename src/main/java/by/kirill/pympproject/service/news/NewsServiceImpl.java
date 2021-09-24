package by.kirill.pympproject.service.news;

import by.kirill.pympproject.bean.News;
import by.kirill.pympproject.dao.DAOException;
import by.kirill.pympproject.dao.DaoProvider;
import by.kirill.pympproject.dao.news.NewsDAO;
import by.kirill.pympproject.service.ServiceException;

import java.time.LocalDate;
import java.util.ArrayList;

public class NewsServiceImpl implements NewsService {

    private final static DaoProvider daoProvider = DaoProvider.getInstance();
    private final NewsDAO newsDao = daoProvider.getNewsDAO();

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
            throw new ServiceException(e);
        }
        return flag;
    }

    @Override
    public boolean updateNews(String title, String text) throws ServiceException {
        boolean flag;
        try {
            flag = newsDao.update(title, text);
        } catch (DAOException e) {
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
            throw new ServiceException(e);
        }
        return news;
    }

    @Override
    public ArrayList<News> getLastNews(int offset, int noOfRecords) throws ServiceException {
        ArrayList<News> newsArrayList;
        LocalDate date = LocalDate.now();
        try {
            newsArrayList = new ArrayList<>(newsDao.readLastNews(offset, noOfRecords));
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return newsArrayList;
    }

    @Override
    public int getNoOfRecords() {
        return newsDao.getNoOfRecords();
    }

}
