package by.kirill.pympproject.controller.impl;

import by.kirill.pympproject.bean.User;
import by.kirill.pympproject.controller.Command;
import by.kirill.pympproject.service.NewsService;
import by.kirill.pympproject.service.ServiceException;
import by.kirill.pympproject.service.ServiceProvider;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public class CreateNewsCommand implements Command {

    private final static ServiceProvider provider = ServiceProvider.getInstance();
    private final NewsService newsService = provider.getNewsService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String title = request.getParameter("title");
        String text = request.getParameter("text");
        HttpSession session = request.getSession(true);
        User user = (User) session.getAttribute("user");
        String author = user.getEmail();
        try {
            newsService.createNews(title, text, author);
        } catch (ServiceException e) {
            e.printStackTrace();
            throw new ServletException(e);
        }
    }
}
