package by.kirill.pympproject.controller.impl.Commands;

import by.kirill.pympproject.controller.Command;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class UnknownCommand implements Command {
    private final static String PATH_TO_INDEX_JSP = "/index.jsp";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(PATH_TO_INDEX_JSP);
        requestDispatcher.forward(request, response);
    }
}
