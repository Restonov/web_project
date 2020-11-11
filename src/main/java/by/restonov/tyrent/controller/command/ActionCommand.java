package by.restonov.tyrent.controller.command;

import javax.servlet.http.HttpServletRequest;

@FunctionalInterface
public interface ActionCommand {
    String execute(HttpServletRequest request);
}
