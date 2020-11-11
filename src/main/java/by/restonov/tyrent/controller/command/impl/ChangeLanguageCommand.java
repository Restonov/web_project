package by.restonov.tyrent.controller.command.impl;

import by.restonov.tyrent.controller.command.ActionCommand;
import by.restonov.tyrent.manager.AttributeName;
import by.restonov.tyrent.manager.ParameterName;
import by.restonov.tyrent.manager.PageName;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ChangeLanguageCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        String page;
        String language = request.getParameter(ParameterName.PAGE_LANGUAGE);
        HttpSession session = request.getSession();
        //TODO use Locale
        session.setAttribute(AttributeName.LOCALE, language);
        page = PageName.MAIN_PAGE;
        return page;
    }
}
