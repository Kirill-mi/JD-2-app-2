package by.kirill.pympproject.controller.impl;

import by.kirill.pympproject.bean.RegistrationInfo;
import by.kirill.pympproject.controller.Command;
import by.kirill.pympproject.service.ServiceException;
import by.kirill.pympproject.service.ServiceProvider;
import by.kirill.pympproject.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public class ValidationUserCommand implements Command {
    private final static ServiceProvider provider = ServiceProvider.getInstance();
    private final UserService userService = provider.getUserService();
    private final static String GO_TO_AUTHORIZATION = "Controller?command=go_to_authorization";
    private final static String GO_TO_NEWS = "Controller?command=go_to_news";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String pass = request.getParameter("pass");
        RegistrationInfo registrationInfo = new RegistrationInfo(email, pass);

        boolean flag;
        try {
            flag = userService.validateUser(registrationInfo);
        } catch (ServiceException e) {
            throw new ServletException(e);
        }
        if (flag) {
            response.sendRedirect(GO_TO_NEWS);
        } else {
            HttpSession session = request.getSession(true);
            session.setAttribute("message", "User is incorrect");
            response.sendRedirect(GO_TO_AUTHORIZATION);
        }
    }
}
