package by.kirill.pympproject.controller.impl;


import by.kirill.pympproject.controller.Command;
import by.kirill.pympproject.service.NewsService;
import by.kirill.pympproject.service.ServiceProvider;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class GoToAccountPage implements Command {
    private final static String PATH = "/WEB-INF/jsp/account.jsp";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(PATH);
        requestDispatcher.forward(request, response);
    }
}
