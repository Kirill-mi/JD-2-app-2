package by.kirill.pympproject.controller.impl;

import by.kirill.pympproject.controller.Command;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public class ChangeLocalCommand implements Command {
    private final static String SESSION_ATTRIBUTE_LOCAL = "local";
    private final static String REQUEST_PARAMETER_LOCATION = "location";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        session.setAttribute(SESSION_ATTRIBUTE_LOCAL, request.getParameter(REQUEST_PARAMETER_LOCATION));
        request.getRequestDispatcher((String) session.getAttribute("path")).forward(request, response);
    }
}
