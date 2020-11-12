package by.restonov.tyrent.controller.command;

import javax.servlet.http.HttpServletRequest;

/**
 * Interface for commands executing
 */
@FunctionalInterface
public interface ActionCommand {
    String execute(HttpServletRequest request);
}
