package by.kirill.pympproject.controller.impl;

import by.kirill.pympproject.controller.Command;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class UnknownCommand implements Command {
    private final String pathToIndexJsp = "/index.jsp";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServletException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(pathToIndexJsp);
        requestDispatcher.forward(request, response);
    }

}
