package by.restonov.tyrent.controller.command.impl;

import by.restonov.tyrent.controller.command.ActionCommand;
import by.restonov.tyrent.manager.AttributeName;
import by.restonov.tyrent.model.entity.User;
import by.restonov.tyrent.model.exception.ServiceException;
import by.restonov.tyrent.manager.PageName;
import by.restonov.tyrent.manager.ParameterName;
import by.restonov.tyrent.model.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

/**
 * Client activation command
 *
 */
public class ActivateClientCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger();
    private UserService service = new UserService();

    /**
     * user performs GET command with hashed data and user email parameters
     * {@link UserService} performs validation
     * if user validated forward to the profile page if user is online
     * or to the main page if user is offline
     * otherwise forward to the login page
     *
     * @param request - HttpServletRequest
     * @return result page
     */
    @Override
    public String execute(HttpServletRequest request) {
        String page;
        String userData = request.getParameter(ParameterName.USER_DATA);
        String userEmail = request.getParameter(ParameterName.USER_EMAIL);
        Optional<User> optionalUser;
        try {
            optionalUser = service.activateClientAndGet(userData, userEmail);
            if (optionalUser.isPresent()) {
                User sessionUser = (User) request.getSession().getAttribute(AttributeName.USER);
                User user = optionalUser.get();
                if (sessionUser != null && sessionUser.getLogin().equals(user.getLogin())) {
                    //TODO alert success activation
                    request.getSession().setAttribute(AttributeName.USER, user);
                    page = PageName.USER_PROFILE_PAGE;
                } else {
                    page = PageName.LOGIN_PAGE;
                }
            } else {
                logger.info("Attempt of activating user that not exists");
                page = PageName.LOGIN_PAGE;
            }
        } catch (ServiceException e) {
            logger.error("Error while client activation", e);
            page = PageName.LOGIN_PAGE;
        }
        return page;
    }
}
