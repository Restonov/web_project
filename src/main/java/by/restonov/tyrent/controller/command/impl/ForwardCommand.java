package by.restonov.tyrent.controller.command.impl;

import by.restonov.tyrent.controller.command.ActionCommand;
import by.restonov.tyrent.manager.PageName;
import by.restonov.tyrent.manager.ParameterName;

import javax.servlet.http.HttpServletRequest;

public class ForwardCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        String parameter = request.getParameter(ParameterName.PATH);
        page = PageName.findPagePath(parameter);
        return page;
    }
}
