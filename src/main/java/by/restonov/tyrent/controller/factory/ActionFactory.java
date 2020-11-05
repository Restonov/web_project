package by.restonov.tyrent.controller.factory;

import by.restonov.tyrent.controller.command.ActionCommand;
import by.restonov.tyrent.controller.command.CommandEnum;
import by.restonov.tyrent.controller.command.impl.EmptyCommand;
import by.restonov.tyrent.manager.MessageManager;
import by.restonov.tyrent.manager.ParameterName;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class ActionFactory {
    static Logger logger = LogManager.getLogger();

    public ActionCommand defineCommand(HttpServletRequest request) {
        ActionCommand command = new EmptyCommand();
        String action = request.getParameter(ParameterName.COMMAND);
        if (action == null || action.isEmpty()) {
            return command;
        }
        try {
            CommandEnum commandEnum = CommandEnum.valueOf(action.toUpperCase());
            command = commandEnum.getCurrentCommand();
        } catch (IllegalArgumentException e) {
            logger.info("Wrong action was performed", e);
            request.setAttribute("wrongAction", action + MessageManager.getProperty("message.wrongaction"));
        }
        return command;
    }
}
