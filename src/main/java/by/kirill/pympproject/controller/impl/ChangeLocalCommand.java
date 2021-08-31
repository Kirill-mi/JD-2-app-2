package by.kirill.pympproject.controller.impl;

import by.kirill.pympproject.controller.Command;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public class ChangeLocalCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        session.setAttribute("local", request.getParameter("location"));
        request.getRequestDispatcher((String) session.getAttribute("path")).forward(request, response);

    }
}
