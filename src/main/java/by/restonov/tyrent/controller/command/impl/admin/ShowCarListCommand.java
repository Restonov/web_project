package by.restonov.tyrent.controller.command.impl.admin;

import by.restonov.tyrent.controller.command.ActionCommand;
import by.restonov.tyrent.manager.PageName;

import javax.servlet.http.HttpServletRequest;

public class ShowCarListCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {
        String page;

        page = PageName.CAR_LIST_PAGE;

        return page;
    }
}
