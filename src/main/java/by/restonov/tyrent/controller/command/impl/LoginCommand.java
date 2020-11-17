package by.restonov.tyrent.controller.command.impl;

import by.restonov.tyrent.controller.command.ActionCommand;
import by.restonov.tyrent.model.entity.User;
import by.restonov.tyrent.model.exception.ServiceException;
import by.restonov.tyrent.manager.AttributeName;
import by.restonov.tyrent.manager.PageName;
import by.restonov.tyrent.manager.ParameterName;
import by.restonov.tyrent.model.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

/**
 * User login command
 *
 */
public class LoginCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger();
    private UserService service = new UserService();

    /**
     * User login to the application POST method
     * Get login and password parameters from jsp form
     * delegating user validation to the {@link UserService}
     * if user validated forward to the main page
     * otherwise returns to the login page with alert message
     *
     * @param request - HttpServletRequest
     * @return result page
     */
    @Override
    public String execute(HttpServletRequest request) {
        String page;
        HttpSession session = request.getSession();
        String login = request.getParameter(ParameterName.USER_LOGIN);
        String password = request.getParameter(ParameterName.USER_PASSWORD);
        Optional<User> optionalUser;
        if (service.validateUser(login, password)) {
            try {
                if (service.checkUserLoginAndPassword(login, password)) {
                    optionalUser = service.findUserByLogin(login);
                    if (optionalUser.isPresent()) {
                        User user = optionalUser.get();
                        if (user.getState() != User.State.BLOCKED) {
                            session.setAttribute(AttributeName.USER, user);
                            request.setAttribute(AttributeName.WELCOME_MESSAGE, true);
                            page = PageName.MAIN_PAGE;
                        } else {
                            request.setAttribute(AttributeName.USER_BLOCKED_ALERT, true);
                            page = PageName.LOGIN_PAGE;
                        }
                    } else {
                        request.setAttribute(AttributeName.LOGIN_PASSWORD_ERROR, true);
                        page = PageName.LOGIN_PAGE;
                    }
                } else {
                    request.setAttribute(AttributeName.LOGIN_PASSWORD_ERROR, true);
                    page = PageName.LOGIN_PAGE;
                }
            } catch (ServiceException e) {
                logger.error("Error while checking user login and password", e);
                request.setAttribute(AttributeName.LOGIN_PASSWORD_ERROR, true);
                page = PageName.LOGIN_PAGE;
            }
        } else {
            request.setAttribute(AttributeName.LOGIN_PASSWORD_ERROR, true);
            page = PageName.LOGIN_PAGE;
        }
        return page;
    }
}
