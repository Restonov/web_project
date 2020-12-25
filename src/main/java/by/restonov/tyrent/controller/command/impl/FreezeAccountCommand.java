package by.restonov.tyrent.controller.command.impl;

import by.restonov.tyrent.controller.command.ActionCommand;
import by.restonov.tyrent.manager.PageName;
import by.restonov.tyrent.manager.ParameterName;
import by.restonov.tyrent.model.entity.impl.User;
import by.restonov.tyrent.model.exception.ServiceException;
import by.restonov.tyrent.model.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class FreezeAccountCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger();
    private UserService service = new UserService();

    @Override
    public String execute(HttpServletRequest request) {
        String userIdParam = request.getParameter(ParameterName.USER_ID);
        long userId = Long.parseLong(userIdParam);
        Optional<User> optionalUser;
        try {
            optionalUser = service.findUserById(userId);
            if (optionalUser.isPresent()) {
                User user = optionalUser.get();
                service.changeUserState(user, User.State.FROZEN);
                request.getSession().invalidate();
            }
        } catch (ServiceException e) {
            logger.error("Freeze User by ID {} - error", userId, e);
        }
        return PageName.INDEX_PAGE;
    }
}
