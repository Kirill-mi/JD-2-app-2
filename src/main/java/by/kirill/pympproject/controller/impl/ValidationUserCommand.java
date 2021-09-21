package by.kirill.pympproject.controller.impl;

import by.kirill.pympproject.bean.RegistrationInfo;
import by.kirill.pympproject.bean.User;
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
import java.util.Optional;


public class ValidationUserCommand implements Command {
    private final static ServiceProvider provider = ServiceProvider.getInstance();
    private final UserService userService = provider.getUserService();
    private final static String GO_TO_AUTHORIZATION = "Controller?command=go_to_authorization";
    private final static String GO_TO_NEWS = "Controller?command=go_to_news";
    private final static String ATTRIBUTE_PATH = "path";
    private final static String CHANGE_LOCAL = "change_local";
    private final static String PARAMETER_EMAIL = "email";
    private final static String PARAMETER_PASS = "pass";
    private final static String ATTRIBUTE_USER = "user";
    private final static String ATTRIBUTE_MESSAGE = "message";
    private final static String USER_IS_INCORRECT_MESSAGE = "User is incorrect";
    private static final Logger logger = LogManager.getLogger(ValidationUserCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter(PARAMETER_EMAIL);
        String pass = request.getParameter(PARAMETER_PASS);
        RegistrationInfo registrationInfo = new RegistrationInfo(email, pass);
        HttpSession session = request.getSession(true);
        session.setAttribute(ATTRIBUTE_PATH, GO_TO_AUTHORIZATION);
        session.removeAttribute(CHANGE_LOCAL);
        session.setAttribute(CHANGE_LOCAL, "false");
        boolean flag;
        try {
            flag = userService.validateUser(registrationInfo);
            if (flag) {
                Optional<User> optionalUser = userService.readUser(email);
                optionalUser.ifPresent(user -> session.setAttribute(ATTRIBUTE_USER, user));
                response.sendRedirect(GO_TO_NEWS);
            } else {
                session.setAttribute(ATTRIBUTE_MESSAGE, USER_IS_INCORRECT_MESSAGE);
                response.sendRedirect(GO_TO_AUTHORIZATION);
            }
        } catch (ServiceException e) {
            logger.warn(e.getMessage());
        }
    }
}
