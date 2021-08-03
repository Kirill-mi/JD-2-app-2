package by.kirill.pympproject.controller.impl;

import by.kirill.pympproject.bean.News;
import by.kirill.pympproject.controller.Command;
import by.kirill.pympproject.service.NewsService;
import by.kirill.pympproject.service.ServiceException;
import by.kirill.pympproject.service.ServiceProvider;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


public class GoToNews implements Command {
    private final static ServiceProvider provider = ServiceProvider.getInstance();
    private final NewsService newsService = provider.getNewsService();
    private final String path = "/WEB-INF/jsp/news.jsp";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<News> news = null;
        try {
            news = newsService.getLastNews();
            request.setAttribute("newsArray", news);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(path);
        requestDispatcher.forward(request, response);
    }
}
