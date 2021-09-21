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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;


public class GoToNewsPage implements Command {
    private final static ServiceProvider provider = ServiceProvider.getInstance();
    private final NewsService newsService = provider.getNewsService();
    private final static String PATH = "/WEB-INF/jsp/news.jsp";
    private final static String REQUEST_PARAMETER_PAGE = "page";
    private final static String REQUEST_ATTRIBUTE_N_OF_PAGES = "noOfPages";
    private final static String REQUEST_ATTRIBUTE_CURRENT_PAGE = "currentPage";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int page = 1;
            int recordsPerPage = 5;
            HttpSession session = request.getSession();

            if (request.getParameter(REQUEST_PARAMETER_PAGE) != null) {
                page = Integer.parseInt(request.getParameter(REQUEST_PARAMETER_PAGE));
            }
            List<News> news = newsService.getLastNews((page - 1) * recordsPerPage, recordsPerPage);
            int noOfRecords = newsService.getNoOfRecords();
            int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
            session.setAttribute("newsArray", news);
            request.setAttribute(REQUEST_ATTRIBUTE_N_OF_PAGES, noOfPages);
            request.setAttribute(REQUEST_ATTRIBUTE_CURRENT_PAGE, page);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(PATH);
        requestDispatcher.forward(request, response);
    }
}
