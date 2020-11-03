package by.restonov.jspservlet.controller.command.impl;

import by.restonov.jspservlet.controller.command.ActionCommand;
import by.restonov.jspservlet.manager.PageName;

import javax.servlet.http.HttpServletRequest;

public class EmptyCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        return PageName.INDEX_PAGE;
    }
}
