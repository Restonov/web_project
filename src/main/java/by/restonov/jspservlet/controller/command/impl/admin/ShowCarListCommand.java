package by.restonov.jspservlet.controller.command.impl.admin;

import by.restonov.jspservlet.controller.command.ActionCommand;
import by.restonov.jspservlet.manager.PageName;

import javax.servlet.http.HttpServletRequest;

public class ShowCarListCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {
        String page;

        page = PageName.CAR_LIST_PAGE;

        return page;
    }
}
