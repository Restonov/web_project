package by.restonov.jspservlet.controller.command.impl;

import by.restonov.jspservlet.controller.command.ActionCommand;
import by.restonov.jspservlet.manager.PageName;
import by.restonov.jspservlet.manager.ParameterName;

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
