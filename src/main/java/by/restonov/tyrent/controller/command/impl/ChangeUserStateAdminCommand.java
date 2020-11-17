package by.restonov.tyrent.controller.command.impl;

import by.restonov.tyrent.controller.command.ActionCommand;
import by.restonov.tyrent.manager.PageName;
import by.restonov.tyrent.manager.ParameterName;
import by.restonov.tyrent.model.entity.User;
import by.restonov.tyrent.model.exception.ServiceException;
import by.restonov.tyrent.model.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class ChangeUserStateAdminCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger();
    private UserService service = new UserService();

    @Override
    public String execute(HttpServletRequest request) {
        String userStateParam = request.getParameter(ParameterName.USER_STATE);
        User.State userState = User.State.valueOf(userStateParam);
        String userId = request.getParameter(ParameterName.USER_ID);
        try {
            Optional<User> optionalUser = service.findUserById(Long.parseLong(userId));
            if (optionalUser.isPresent()) {
                User user = optionalUser.get();
                service.changeUserState(user, userState);
            } else {
                logger.error("No User with ID {} in DB", userId);
            }
        } catch (ServiceException e) {
            logger.error("Find User by ID {} - error", userId, e);
        }
        return PageName.USER_LIST_PAGE;
    }
}

