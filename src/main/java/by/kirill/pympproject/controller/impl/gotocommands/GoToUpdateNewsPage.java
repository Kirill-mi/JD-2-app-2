package by.kirill.pympproject.controller.impl.gotocommands;


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


public class GoToUpdateNewsPage implements Command {
    private final static String PATH = "/WEB-INF/jsp/update_news.jsp";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(PATH);
        requestDispatcher.forward(request, response);
    }
}
