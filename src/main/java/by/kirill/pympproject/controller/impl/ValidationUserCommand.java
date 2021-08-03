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

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String pass = request.getParameter("pass");
        RegistrationInfo registrationInfo = new RegistrationInfo(email,pass);

        boolean flag = false;
        try {
            flag = userService.validateUser(registrationInfo);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        if (flag) {
            response.sendRedirect("Controller?command=GO_TO_MAIN");
        } else {

            HttpSession session = request.getSession(true);
            session.setAttribute("message", "User is incorrect");
            response.sendRedirect("Controller?command=GO_TO_AUTHORIZATION&message");
        }
    }

}
