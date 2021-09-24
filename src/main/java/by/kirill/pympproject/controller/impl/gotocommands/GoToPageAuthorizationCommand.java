package by.kirill.pympproject.controller.impl.gotocommands;

import by.kirill.pympproject.controller.Command;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class GoToPageAuthorizationCommand implements Command {
    private final static String PATH = "/WEB-INF/jsp/validate.jsp";
    private final static String ATTRIBUTE_PATH = "path";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        session.setAttribute(ATTRIBUTE_PATH, PATH);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(PATH);
        requestDispatcher.forward(request, response);
    }
}
