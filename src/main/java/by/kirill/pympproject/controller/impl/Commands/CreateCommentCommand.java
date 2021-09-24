package by.kirill.pympproject.controller.impl.Commands;

import by.kirill.pympproject.bean.Comment;
import by.kirill.pympproject.bean.News;
import by.kirill.pympproject.bean.User;
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


public class CreateCommentCommand implements Command {

    private final static ServiceProvider provider = ServiceProvider.getInstance();
    private final CommentService commentService = provider.getCommentService();
    private final static String PATH = "/WEB-INF/jsp/account.jsp";
    private final static String REQUEST_PARAMETER_COMMENT = "comment";
    private final static String NEWS_ATTRIBUTE = "newsAttribute";
    private final static String USER_ATTRIBUTE = "user";

    private static final Logger logger = LogManager.getLogger(CreateCommentCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String commentText = request.getParameter(REQUEST_PARAMETER_COMMENT);
        HttpSession session = request.getSession(true);
        User user = (User) session.getAttribute(USER_ATTRIBUTE);
        News news = (News) session.getAttribute(NEWS_ATTRIBUTE);
        String newsTitle = news.getTitle();
        String author = user.getName();
        Comment comment = new Comment(1, commentText, newsTitle, author);
        try {
            commentService.createComment(comment);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(PATH);
            requestDispatcher.forward(request, response);
        } catch (ServiceException e) {
            logger.warn(e.getMessage());
        }
    }
}
