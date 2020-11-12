package by.restonov.tyrent.controller.factory;

import by.restonov.tyrent.controller.command.ActionCommand;
import by.restonov.tyrent.controller.command.CommandEnum;
import by.restonov.tyrent.manager.AttributeName;
import by.restonov.tyrent.manager.ParameterName;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

/**
 * Factory that works with application commands
 */
public class ActionFactory {

    /**
     * Private constructor to prevent object creating
     */
    private ActionFactory() {
    }

    /**
     * If request "command" parameter is not null or blank
     * try to get command value from {@link CommandEnum}
     * otherwise return empty Optional
     *
     * @param request - HttpServletRequest
     * @return Optional of {@link ActionCommand} to the Controller
     */
    public static Optional<ActionCommand> defineCommand(HttpServletRequest request) {
        Optional<ActionCommand> optionalCommand;
        String action = request.getParameter(ParameterName.COMMAND);
        if (action == null || action.isBlank()) {
            optionalCommand = Optional.empty();
        } else {
            try {
                CommandEnum commandEnum = CommandEnum.valueOf(action.toUpperCase());
                ActionCommand command = commandEnum.getCurrentCommand();
                optionalCommand = Optional.of(command);
            } catch (IllegalArgumentException e) {
                request.setAttribute(AttributeName.ERROR_404, e);
                optionalCommand = Optional.empty();
            }
        }
        return optionalCommand;
    }
}
