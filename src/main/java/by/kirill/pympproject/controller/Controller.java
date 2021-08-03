package by.kirill.pympproject.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/Controller")
public class Controller extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final String COMMAND_REQUEST_PARAM = "command";
    private final CommandProvider commandProvider = new CommandProvider();


    public Controller() {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String commandName = request.getParameter(COMMAND_REQUEST_PARAM);
        System.out.println(commandName);
        Command command = commandProvider.findCommand(commandName);
        command.execute(request, response);
    }
}
