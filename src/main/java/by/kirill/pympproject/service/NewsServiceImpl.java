package by.kirill.pympproject.service;

import by.kirill.pympproject.bean.News;
import by.kirill.pympproject.DAO.DAOException;
import by.kirill.pympproject.DAO.DaoProvider;
import by.kirill.pympproject.DAO.NewsDAO;

import java.time.LocalDate;
import java.util.ArrayList;

public class NewsServiceImpl implements NewsService {
    private final static DaoProvider daoProvider = DaoProvider.getInstance();
    private final NewsDAO newsDao = daoProvider.getNewsDao();

    @Override
    public boolean create(News news) {
        return false;
    }

    @Override
    public boolean delete(String title) {
        return false;
    }

    @Override
    public void update(News news) {

    }

    @Override
    public News read(String title) {
        return null;
    }

    @Override
    public ArrayList<News> getLastNews() throws ServiceException {
        ArrayList<News> newsArrayList;
        LocalDate date= LocalDate.now();
        try {
                newsArrayList = new ArrayList<>(newsDao.readNews(date.minusDays(1)));
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return newsArrayList;
    }
}
