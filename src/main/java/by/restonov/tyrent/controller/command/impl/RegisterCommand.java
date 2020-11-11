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
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Action command for new User registering
 */
public class RegisterCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger();
    private UserService service = new UserService();

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        String firstName = request.getParameter(ParameterName.USER_FIRST_NAME);
        String lastName = request.getParameter(ParameterName.USER_LAST_NAME);
        String email = request.getParameter(ParameterName.USER_EMAIL);
        String phone = request.getParameter(ParameterName.USER_PHONE);
        String login = request.getParameter(ParameterName.USER_LOGIN);
        String password = request.getParameter(ParameterName.USER_PASSWORD);

        Map<String, String> userData = new HashMap<>();
        userData.put(ParameterName.USER_FIRST_NAME, firstName);
        userData.put(ParameterName.USER_LAST_NAME, lastName);
        userData.put(ParameterName.USER_EMAIL, email);
        userData.put(ParameterName.USER_PHONE, phone);
        userData.put(ParameterName.USER_LOGIN, login);

        if (service.validateUser(userData, password)) {
            try {
                Optional<User> optionalUser = service.registerNewUserAndGet(userData, password);
                if (optionalUser.isPresent()) {
                    User user = optionalUser.get();
                    service.sendActivationEmail(user.getEmail(), user.getLogin());
                    HttpSession session = request.getSession();
                    session.setAttribute(AttributeName.USER, user);
                    session.setAttribute(AttributeName.ACTIVATE_ADMIN_PANEL, user.getRole() != User.Role.CLIENT);
                    request.setAttribute(AttributeName.WELCOME_MESSAGE, true);
                    page = PageName.MAIN_PAGE;
                } else {
                    logger.info("Attempt of register user with already existing data");
                    request.setAttribute(AttributeName.USER_EXISTS_ERROR, true);
                    page = PageName.REGISTER_PAGE;
                }
            } catch (ServiceException e) {
                logger.error("Error while registering new User", e);
                request.setAttribute(AttributeName.REGISTRATION_ERROR, true);
                page = PageName.REGISTER_PAGE;
            }
        } else {
            logger.error("User not passed the validation");
            request.setAttribute(AttributeName.DATA_VALIDATION_ERROR, true);
            page = PageName.REGISTER_PAGE;
        }
        return page;
    }
}