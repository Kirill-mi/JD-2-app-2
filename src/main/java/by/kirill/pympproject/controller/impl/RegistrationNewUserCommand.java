package by.kirill.pympproject.controller.impl;

import by.kirill.pympproject.bean.RegistrationInfo;
import by.kirill.pympproject.controller.Command;
import by.kirill.pympproject.service.ServiceException;
import by.kirill.pympproject.service.ServiceProvider;
import by.kirill.pympproject.service.UserService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

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
    private final static String PARAMETER_EMAIL = "email";
    private final static String PARAMETER_NAME = "name";
    private final static String PARAMETER_PASS = "pass";
    private final static String PARAMETER_NEW_PASS = "pass_new";
    private final static String REGISTRATION_STATUS = "registration_status";
    private static final Logger logger = LogManager.getLogger(RegistrationNewUserCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String name = request.getParameter(PARAMETER_NAME);
        String pass = request.getParameter(PARAMETER_PASS);
        String controlPass = request.getParameter(PARAMETER_NEW_PASS);
        String email = request.getParameter(PARAMETER_EMAIL);
        RegistrationInfo registrationInfo = new RegistrationInfo(name, email, pass, controlPass);
        try {
            String registrationStatus = userService.createUser(registrationInfo);
            HttpSession session = request.getSession(true);
            session.setAttribute(REGISTRATION_STATUS, registrationStatus);
            System.out.println(registrationStatus);
            if (registrationStatus.equals("User added")) {
                response.sendRedirect(GO_TO_AUTHORIZATION);
            } else {
                response.sendRedirect(GO_TO_REGISTRATION);
            }
        } catch (ServiceException e) {
            logger.warn(e.getMessage());
            response.sendRedirect(GO_TO_REGISTRATION);
        }
    }
}
