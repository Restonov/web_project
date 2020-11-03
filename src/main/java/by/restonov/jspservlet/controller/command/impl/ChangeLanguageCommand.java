package by.restonov.jspservlet.controller.command.impl;

import by.restonov.jspservlet.controller.command.ActionCommand;
import by.restonov.jspservlet.manager.AttributeName;
import by.restonov.jspservlet.manager.ParameterName;
import by.restonov.jspservlet.manager.PageName;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ChangeLanguageCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        String page;
        String language = request.getParameter(ParameterName.PAGE_LANGUAGE);
        HttpSession session = request.getSession();
        session.setAttribute(AttributeName.LOCALE, language);
        page = PageName.MAIN_PAGE;
        return page;
    }
}
