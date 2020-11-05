package by.restonov.tyrent.controller.command.impl;

import by.restonov.tyrent.controller.command.ActionCommand;
import by.restonov.tyrent.entity.User;
import by.restonov.tyrent.manager.AttributeName;
import by.restonov.tyrent.manager.PageName;
import by.restonov.tyrent.manager.ParameterName;
import by.restonov.tyrent.model.service.UserService;
import by.restonov.tyrent.util.DataValidator;
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
        String login = request.getParameter(ParameterName.LOGIN);
        String password = request.getParameter(ParameterName.PASSWORD);
        Optional<User> optionalUser;
        //TODO all logic could be in Service
        if (DataValidator.validateLatinText(login) && DataValidator.validatePassword(password)) {
            if (service.checkUserLoginAndPassword(login, password)) {
                optionalUser = service.findUserByLogin(login);
                if (optionalUser.isPresent()) {
                    User user = optionalUser.get();
                    user.setOnlineState(true);
                    session.setAttribute(AttributeName.USER, user);
                    if (user.receiveRoleId(user.getRole()) > User.CLIENT_ROLE_ID) {
                        session.setAttribute(AttributeName.ACTIVATE_ADMIN_PANEL, true);
                    }
                    page = PageName.MAIN_PAGE;
                } else {
                    logger.warn("Incorrect login and password");
                    request.setAttribute(AttributeName.LOGIN_PASSWORD_ERROR, true);
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
        return page;
    }
}
