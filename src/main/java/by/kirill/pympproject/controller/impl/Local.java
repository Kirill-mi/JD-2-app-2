package by.kirill.pympproject.controller.impl;

import by.kirill.pympproject.controller.Command;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class Local implements Command {
    private final static String PATH = "/WEB-INF/jsp/welcome.jsp";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession(true).setAttribute("local", request.getParameter("location"));
        request.getRequestDispatcher(PATH).forward(request, response);

    }
}
