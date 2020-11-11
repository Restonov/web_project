package by.restonov.tyrent.controller.command.impl;

import by.restonov.tyrent.controller.command.ActionCommand;
import by.restonov.tyrent.manager.PageName;

import javax.servlet.http.HttpServletRequest;

public class ChangeRoleAdminCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {


        System.out.println(request.getParameter("user_role"));

        return PageName.USER_LIST_PAGE;
    }
}
