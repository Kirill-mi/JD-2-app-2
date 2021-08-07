package by.kirill.pympproject.DAO;

public final class DaoProvider {
    private final static DaoProvider instance = new DaoProvider();
    private final UserDao userDao = new UserDaoImpl();
    private final NewsDao newsDao = new NewsDaoImpl();

    private DaoProvider() {
    }

    public static DaoProvider getInstance() {
        return instance;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public NewsDao getNewsDao() {
        return newsDao;
    }
}
