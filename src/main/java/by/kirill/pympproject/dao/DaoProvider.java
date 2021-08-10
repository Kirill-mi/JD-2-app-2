package by.kirill.pympproject.dao;

public final class DaoProvider {
    private final static DaoProvider instance = new DaoProvider();
    private final UserDAO userDao = new UserDAOImpl();
    private final NewsDAO newsDao = new NewsDAOImpl();

    private DaoProvider() {
    }

    public static DaoProvider getInstance() {
        return instance;
    }

    public UserDAO getUserDao() {
        return userDao;
    }

    public NewsDAO getNewsDao() {
        return newsDao;
    }
}
