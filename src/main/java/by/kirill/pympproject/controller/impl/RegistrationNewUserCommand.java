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

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
                response.sendRedirect("Controller?command=go_to_authorization");
            } else {
                response.sendRedirect("Controller?command=go_to_registration");
            }
        } catch (ServiceException e) {
            response.sendRedirect("Controller?command=REGISTRATION&message=Some problem");
        }

    }

}
