package by.kirill.pympproject.controller;

import by.kirill.pympproject.controller.impl.*;

import java.util.HashMap;
import java.util.Map;

public class CommandProvider {
    private final Map<CommandName, Command> commands = new HashMap<>();

    public CommandProvider() {
        commands.put(CommandName.GO_TO_AUTHORIZATION, new GoToPageAuthorizationCommand());
        commands.put(CommandName.GO_TO_REGISTRATION, new GoToPageRegistrationCommand());
        commands.put(CommandName.LOCAL, new ChangeLocalCommand());
        commands.put(CommandName.REGISTRATION_NEW_USER, new RegistrationNewUserCommand());
        commands.put(CommandName.VALIDATION, new ValidationUserCommand());
        commands.put(CommandName.FORGOT_PASSWORD, new ForgotPassword());
        commands.put(CommandName.GO_TO_NEWS, new GoToNewsPage());
        commands.put(CommandName.USER_S_ACCOUNT, new GoToAccountPage());
        commands.put(CommandName.PROFILE_EDIT, new UpdateUserCommand());
        commands.put(CommandName.GO_TO_EDIT_PROFILE, new GoToEditProfilePage());
        commands.put(CommandName.GO_TO_EDIT_NEWS, new GoToEditNewsPage());
        commands.put(CommandName.CREATE_NEWS, new CreateNewsCommand());
        commands.put(CommandName.GO_TO_WELCOME, new GoToWelcomePage());
        commands.put(CommandName.READ_NEWS, new GoToReadNewsPage());
        commands.put(CommandName.GO_TO_UPDATE_NEWS, new GoToUpdateNewsPage());
        commands.put(CommandName.UPDATE_NEWS, new UpdateNewsCommand());
    }

    public Command findCommand(String name) {
        if (name == null) {
            name = CommandName.UNKNOWN_COMMAND.toString();
        }
        CommandName commandname;
        try {
            commandname = CommandName.valueOf(name.toUpperCase());
        } catch (IllegalArgumentException e) {
            commandname = CommandName.UNKNOWN_COMMAND;
        }
        return commands.get(commandname);
    }
}
