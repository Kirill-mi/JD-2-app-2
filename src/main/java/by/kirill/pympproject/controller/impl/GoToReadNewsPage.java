package by.kirill.pympproject.controller.impl;


import by.kirill.pympproject.bean.News;
import by.kirill.pympproject.controller.Command;

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

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        List<?> newsArrayList = (ArrayList<?>) session.getAttribute(ATTRIBUTE_NEWS_ARRAY);
        News news = (News) newsArrayList.get(Integer.parseInt(request.getParameter(PARAMETER_NUMBER)));
        session.setAttribute(ATTRIBUTE_NEWS_ATTRIBUTE, news);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(PATH);
        requestDispatcher.forward(request, response);
    }
}
