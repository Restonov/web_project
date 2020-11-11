package by.restonov.tyrent.controller.command.impl;

import by.restonov.tyrent.controller.command.ActionCommand;
import by.restonov.tyrent.model.entity.User;
import by.restonov.tyrent.manager.AttributeName;
import by.restonov.tyrent.manager.PageName;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LogoutCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(AttributeName.USER);
        user.setOnline(false);
        session.invalidate();
        return PageName.INDEX_PAGE;
    }
}
