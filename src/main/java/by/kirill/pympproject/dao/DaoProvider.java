package by.kirill.pympproject.dao;

public final class DaoProvider {
    private final static DaoProvider instance = new DaoProvider();
    private final UserDao userDao = new UserDaoImpl();

    private DaoProvider() {
    }

    public static DaoProvider getInstance() {
        return instance;
    }

    public UserDao getUserDao() {
        return userDao;
    }
}
