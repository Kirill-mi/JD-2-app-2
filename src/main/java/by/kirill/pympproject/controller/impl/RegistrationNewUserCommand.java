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


public class RegistrationNewUserCommand implements Command {

    private final static ServiceProvider provider = ServiceProvider.getInstance();
    private final UserService userService = provider.getUserService();
    private final static String GO_TO_AUTHORIZATION = "Controller?command=go_to_authorization";
    private final static String GO_TO_REGISTRATION = "Controller?command=go_to_registration";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String name = request.getParameter("name");
        String pass = request.getParameter("pass");
        String controlPass = request.getParameter("pass_new");
        String email = request.getParameter("email");
        RegistrationInfo registrationInfo = new RegistrationInfo(name, email, pass, controlPass);
        try {
            String registrationStatus = userService.createUser(registrationInfo);
            HttpSession session = request.getSession(true);
            session.setAttribute("registration_status", registrationStatus);
            System.out.println(registrationStatus);
            if (registrationStatus.equals("User added")) {
                response.sendRedirect(GO_TO_AUTHORIZATION);
            } else {
                response.sendRedirect(GO_TO_REGISTRATION);
            }
        } catch (ServiceException e) {
            response.sendRedirect(GO_TO_REGISTRATION);
        }
    }
}
