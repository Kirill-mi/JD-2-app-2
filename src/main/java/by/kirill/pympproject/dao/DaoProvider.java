package by.kirill.pympproject.dao;

import by.kirill.pympproject.dao.comment.CommentDAO;
import by.kirill.pympproject.dao.comment.CommentDAOImpl;
import by.kirill.pympproject.dao.news.NewsDAO;
import by.kirill.pympproject.dao.news.NewsDAOImpl;
import by.kirill.pympproject.dao.user.UserDAO;
import by.kirill.pympproject.dao.user.UserDAOImpl;

public final class DaoProvider {
    private final static DaoProvider instance = new DaoProvider();
    private final UserDAO userDAO = new UserDAOImpl();
    private final NewsDAO newsDAO = new NewsDAOImpl();
    private final CommentDAO commentDAO = new CommentDAOImpl();

    private DaoProvider() {
    }

    public static DaoProvider getInstance() {
        return instance;
    }

    public UserDAO getUserDAO() {
        return userDAO;
    }

    public NewsDAO getNewsDAO() {
        return newsDAO;
    }

    public CommentDAO getCommentDAO() {
        return commentDAO;
    }
}
