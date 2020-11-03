package by.restonov.jspservlet.controller.command.impl;

import by.restonov.jspservlet.controller.command.ActionCommand;
import by.restonov.jspservlet.entity.User;
import by.restonov.jspservlet.entity.builder.UserBuilder;
import by.restonov.jspservlet.manager.AttributeName;
import by.restonov.jspservlet.manager.PageName;
import by.restonov.jspservlet.manager.ParameterName;
import by.restonov.jspservlet.model.service.UserService;
import by.restonov.jspservlet.util.DataValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Action command for new User registering
 */
public class RegisterCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger();
    UserService service = new UserService();

    /**
     * Registering of new User via
     * validation of received user data
     * check if User with same login and / or email not exists
     * and put user data to database
     * @param request HttpServletRequest
     * @return forward page
     */
    @Override
    public String execute(HttpServletRequest request) {
        String page;
        UserBuilder factory = new UserBuilder();
        String firstName = request.getParameter(ParameterName.FIRST_NAME);
        String lastName = request.getParameter(ParameterName.LAST_NAME);
        String email = request.getParameter(ParameterName.EMAIL);
        String login = request.getParameter(ParameterName.LOGIN);
        String password = request.getParameter(ParameterName.PASSWORD);

        Map<String, String> userData = new HashMap<>();
        userData.put(ParameterName.FIRST_NAME, firstName);
        userData.put(ParameterName.LAST_NAME, lastName);
        userData.put(ParameterName.EMAIL, email);
        userData.put(ParameterName.LOGIN, login);

        if (DataValidator.validateData(userData) && DataValidator.validatePassword(password)) {
            if (service.checkUserNotExists(userData)) {
                User user = factory.buildUserFromMap(userData);
                if (service.registerNewUser(user, password)) {
                    user.setStatus(User.Status.DEACTIVATED);
                    user.setOnlineState(true);
                    request.getSession().setAttribute(AttributeName.USER, user);
                    page = PageName.MAIN_PAGE;
//                    MailSender sender = new MailSender();
//                    sender.send();
                } else {
                   request.setAttribute(AttributeName.REGISTRATION_ERROR, true);
                   page = PageName.REGISTER_PAGE;
                }
            } else {
                logger.info("Attempt of register user with existing data");
                request.setAttribute(AttributeName.USER_EXISTS_ERROR, true);
                page = PageName.REGISTER_PAGE;
            }
        } else {
            request.setAttribute(AttributeName.DATA_VALIDATION_ERROR, true);
            page = PageName.REGISTER_PAGE;
        }
        return page;
    }
}