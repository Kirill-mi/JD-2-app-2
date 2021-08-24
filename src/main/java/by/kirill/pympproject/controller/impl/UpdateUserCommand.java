package by.kirill.pympproject.controller.impl;

import by.kirill.pympproject.bean.RegistrationInfo;
import by.kirill.pympproject.bean.User;
import by.kirill.pympproject.controller.Command;
import by.kirill.pympproject.service.ServiceException;
import by.kirill.pympproject.service.ServiceProvider;
import by.kirill.pympproject.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;


public class UpdateUserCommand implements Command {

    private final static ServiceProvider provider = ServiceProvider.getInstance();
    private final UserService userService = provider.getUserService();
    private final static String GO_TO_ACCOUNT = "Controller?command=user_s_account";
    private final static String GO_TO_EDIT_PROFILE = "Controller?command=profile_edit";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");
        String pass = request.getParameter("pass");
        String controlPass = request.getParameter("pass_new");
        String email = request.getParameter("email");
        RegistrationInfo registrationInfo = new RegistrationInfo(name, email, pass, controlPass);
        try {
            boolean registrationStatus = userService.updateUser(registrationInfo);
            if (registrationStatus) {
                Optional<User> optionalUser = userService.readUser(email);
                HttpSession session = request.getSession(true);
                session.removeAttribute("user");
                optionalUser.ifPresent(user -> session.setAttribute("user", user));
                response.sendRedirect(GO_TO_ACCOUNT);
            } else {
                response.sendRedirect(GO_TO_EDIT_PROFILE);
            }
        } catch (ServiceException e) {
            response.sendRedirect(GO_TO_EDIT_PROFILE);
        }
    }
}