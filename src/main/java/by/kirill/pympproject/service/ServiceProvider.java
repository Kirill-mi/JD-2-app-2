package by.kirill.pympproject.service;

public final class ServiceProvider {
    private final static ServiceProvider instance = new ServiceProvider();
    private final UserService userService = new UserServiceImpl();
    private final NewsService newsService = new NewsServiceImpl();

    private ServiceProvider() {

    }

    public static ServiceProvider getInstance() {
        return instance;
    }

    public UserService getUserService() {
        return userService;
    }
    public NewsService getNewsService() {
        return newsService;
    }

}
