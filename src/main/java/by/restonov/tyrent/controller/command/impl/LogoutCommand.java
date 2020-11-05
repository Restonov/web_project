package by.restonov.tyrent.controller.command.impl;

import by.restonov.tyrent.controller.command.ActionCommand;
import by.restonov.tyrent.entity.User;
import by.restonov.tyrent.manager.AttributeName;
import by.restonov.tyrent.manager.PageName;

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
