package by.restonov.jspservlet.controller.command.impl.admin;

import by.restonov.jspservlet.controller.command.ActionCommand;
import by.restonov.jspservlet.manager.PageName;

import javax.servlet.http.HttpServletRequest;

public class ChangeRoleCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {


        System.out.println(request.getParameter("user_role"));

        return PageName.USER_LIST_PAGE;
    }
}
