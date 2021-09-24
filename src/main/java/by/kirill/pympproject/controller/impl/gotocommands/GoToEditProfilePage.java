package by.kirill.pympproject.controller.impl.gotocommands;

import by.kirill.pympproject.controller.Command;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GoToEditProfilePage implements Command {
    private final static String PATH = "/WEB-INF/jsp/edit_profile.jsp";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(PATH);
        requestDispatcher.forward(request, response);
    }
}
