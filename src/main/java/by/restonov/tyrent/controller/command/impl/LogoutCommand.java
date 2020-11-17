package by.restonov.tyrent.controller.command.impl;

import by.restonov.tyrent.controller.command.ActionCommand;
import by.restonov.tyrent.manager.PageName;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * User logout command
 *
 */
public class LogoutCommand implements ActionCommand {

    /**
     * User logout from the application
     * set user online status to false
     * and invalidate session
     *
     * @param request - HttpServletRequest
     * @return index page
     */
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();
        return PageName.INDEX_PAGE;
    }
}
