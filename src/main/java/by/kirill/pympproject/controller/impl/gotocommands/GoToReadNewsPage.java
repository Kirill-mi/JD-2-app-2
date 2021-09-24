package by.kirill.pympproject.controller.impl.gotocommands;


import by.kirill.pympproject.bean.Comment;
import by.kirill.pympproject.bean.News;
import by.kirill.pympproject.controller.Command;
import by.kirill.pympproject.service.comment.CommentService;
import by.kirill.pympproject.service.ServiceException;
import by.kirill.pympproject.service.ServiceProvider;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class GoToReadNewsPage implements Command {
    private final static String PATH = "/WEB-INF/jsp/read_news.jsp";
    private final static String ATTRIBUTE_NEWS_ARRAY = "newsArray";
    private final static String PARAMETER_NUMBER = "number";
    private final static String ATTRIBUTE_NEWS_ATTRIBUTE = "newsAttribute";
    private final static String ATTRIBUTE_COMMENTS = "comments";
    private final static ServiceProvider provider = ServiceProvider.getInstance();
    private final CommentService commentService = provider.getCommentService();
    private static final Logger logger = LogManager.getLogger(GoToReadNewsPage.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        List<?> newsArrayList = (ArrayList<?>) session.getAttribute(ATTRIBUTE_NEWS_ARRAY);
        News news = (News) newsArrayList.get(Integer.parseInt(request.getParameter(PARAMETER_NUMBER)));
        session.setAttribute(ATTRIBUTE_NEWS_ATTRIBUTE, news);
        session.removeAttribute(ATTRIBUTE_COMMENTS);
        try {
            ArrayList<Comment> commentArrayList = commentService.getComments(news.getTitle());
            session.setAttribute(ATTRIBUTE_COMMENTS, commentArrayList);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(PATH);
            requestDispatcher.forward(request, response);
        } catch (ServiceException e) {
            logger.warn(e.getMessage());
        }
    }
}
