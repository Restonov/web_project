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

public class LoginCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger();
    private UserService service = new UserService();

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
                        user.setOnline(true);
                        session.setAttribute(AttributeName.USER, user);
                        //TODO move admin panel to filter
                        session.setAttribute(AttributeName.ACTIVATE_ADMIN_PANEL, user.getRole() == User.Role.ADMINISTRATOR);
                        request.setAttribute(AttributeName.WELCOME_MESSAGE, true);
                        page = PageName.MAIN_PAGE;
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
