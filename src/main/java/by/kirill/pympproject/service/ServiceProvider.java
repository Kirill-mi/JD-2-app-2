package by.kirill.pympproject.service;

import by.kirill.pympproject.dao.DaoProvider;
import by.kirill.pympproject.dao.user.UserDAO;
import by.kirill.pympproject.service.comment.CommentService;
import by.kirill.pympproject.service.comment.CommentServiceImpl;
import by.kirill.pympproject.service.news.NewsService;
import by.kirill.pympproject.service.news.NewsServiceImpl;
import by.kirill.pympproject.service.user.UserService;
import by.kirill.pympproject.service.user.UserServiceImpl;

public final class ServiceProvider {

    private final static ServiceProvider instance = new ServiceProvider();
    private final UserServiceImpl userService = new UserServiceImpl();
    private final NewsService newsService = new NewsServiceImpl();
    private final CommentService commentService = new CommentServiceImpl();
    private final DaoProvider daoProvider = DaoProvider.getInstance();
    private final UserDAO userDao = daoProvider.getUserDAO();

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

    public CommentService getCommentService() {
        return commentService;
    }

}
