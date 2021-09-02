package by.kirill.pympproject.service;

import by.kirill.pympproject.dao.DaoProvider;
import by.kirill.pympproject.dao.UserDAO;

public final class ServiceProvider {

    private final static ServiceProvider instance = new ServiceProvider();
    private final UserServiceImpl userService = new UserServiceImpl();
    private final NewsService newsService = new NewsServiceImpl();
    private final DaoProvider daoProvider = DaoProvider.getInstance();
    private final UserDAO userDao = daoProvider.getUserDao();

    private ServiceProvider() {
    }

    public static ServiceProvider getInstance() {
        return instance;
    }

    public UserService getUserService() {
        userService.setUserDao(userDao);
        return userService;
    }

    public NewsService getNewsService() {
        return newsService;
    }

}
