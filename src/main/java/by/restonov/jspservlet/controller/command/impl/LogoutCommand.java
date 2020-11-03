package by.restonov.jspservlet.controller.command.impl;

import by.restonov.jspservlet.controller.command.ActionCommand;
import by.restonov.jspservlet.entity.User;
import by.restonov.jspservlet.manager.AttributeName;
import by.restonov.jspservlet.manager.PageName;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LogoutCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        String indexPage = PageName.INDEX_PAGE;
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(AttributeName.USER);
        user.setOnlineState(false);
        session.invalidate();
        return indexPage;
    }
}
