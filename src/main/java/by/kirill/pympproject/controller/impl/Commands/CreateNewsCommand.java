package by.kirill.pympproject.controller.impl.Commands;

import by.kirill.pympproject.bean.User;
import by.kirill.pympproject.controller.Command;
import by.kirill.pympproject.service.news.NewsService;
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


public class CreateNewsCommand implements Command {

    private final static ServiceProvider provider = ServiceProvider.getInstance();
    private final NewsService newsService = provider.getNewsService();
    private final static String PATH = "/WEB-INF/jsp/account.jsp";
    private final static String REQUEST_PARAMETER_TITLE = "title";
    private final static String REQUEST_PARAMETER_TEXT = "text";

    private static final Logger logger = LogManager.getLogger(CreateNewsCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String title = request.getParameter(REQUEST_PARAMETER_TITLE);
        String text = request.getParameter(REQUEST_PARAMETER_TEXT);
        HttpSession session = request.getSession(true);
        User user = (User) session.getAttribute("user");
        String author = user.getEmail();
        try {
            newsService.createNews(title, text, author);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(PATH);
            requestDispatcher.forward(request, response);
        } catch (ServiceException e) {
            logger.warn(e.getMessage());
        }
    }
}
