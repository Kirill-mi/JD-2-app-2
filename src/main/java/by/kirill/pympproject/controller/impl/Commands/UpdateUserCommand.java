package by.kirill.pympproject.controller.impl.Commands;

import by.kirill.pympproject.bean.RegistrationInfo;
import by.kirill.pympproject.bean.User;
import by.kirill.pympproject.controller.Command;
import by.kirill.pympproject.service.ServiceException;
import by.kirill.pympproject.service.ServiceProvider;
import by.kirill.pympproject.service.user.UserService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;


public class UpdateUserCommand implements Command {

    private final static ServiceProvider provider = ServiceProvider.getInstance();
    private final UserService userService = provider.getUserService();
    private final static String GO_TO_ACCOUNT = "Controller?command=user_s_account";
    private final static String GO_TO_EDIT_PROFILE = "Controller?command=go_to_edit_profile";
    private final static String PARAMETER_EMAIL = "email";
    private final static String PARAMETER_NAME = "name";
    private final static String PARAMETER_PASS = "pass";
    private final static String PARAMETER_NEW_PASS = "pass_new";
    private final static String ATTRIBUTE_USER = "user";
    private static final Logger logger = LogManager.getLogger(UpdateUserCommand.class);
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter(PARAMETER_NAME);
        String pass = request.getParameter(PARAMETER_PASS);
        String controlPass = request.getParameter(PARAMETER_NEW_PASS);
        String email = request.getParameter(PARAMETER_EMAIL);
        RegistrationInfo registrationInfo = new RegistrationInfo(name, email, pass, controlPass);
        try {
            boolean registrationStatus = userService.updateUser(registrationInfo);
            if (registrationStatus) {
                Optional<User> optionalUser = userService.readUser(email);
                HttpSession session = request.getSession(true);
                session.removeAttribute(ATTRIBUTE_USER);
                optionalUser.ifPresent(user -> session.setAttribute(ATTRIBUTE_USER, user));
                response.sendRedirect(GO_TO_ACCOUNT);
            } else {
                response.sendRedirect(GO_TO_EDIT_PROFILE);
            }
        } catch (ServiceException e) {
            logger.warn(e.getMessage());
            response.sendRedirect(GO_TO_EDIT_PROFILE);
        }
    }
}
