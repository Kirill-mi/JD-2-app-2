package by.kirill.pympproject.controller;

import by.kirill.pympproject.controller.impl.*;

import java.util.HashMap;
import java.util.Map;

public class CommandProvider {
    private final Map<CommandName, Command> commands = new HashMap<>();

    public CommandProvider() {
        commands.put(CommandName.GO_TO_AUTHORIZATION, new GoToPageAuthorizationCommand());
        commands.put(CommandName.GO_TO_REGISTRATION, new GoToPageRegistrationCommand());
        commands.put(CommandName.LOCAL, new Local());
        commands.put(CommandName.REGISTRATION_NEW_USER, new RegistrationNewUserCommand());
        commands.put(CommandName.GO_TO_MAIN, new GoToMainPage());
        commands.put(CommandName.VALIDATION, new ValidationUserCommand());
        commands.put(CommandName.FORGOT_PASSWORD, new ForgotPassword());
        commands.put(CommandName.GO_TO_NEWS, new GoToNews());
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
