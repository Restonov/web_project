package by.restonov.tyrent.controller.command.impl;

import by.restonov.tyrent.controller.command.ActionCommand;
import by.restonov.tyrent.manager.AttributeName;
import by.restonov.tyrent.manager.PageName;
import by.restonov.tyrent.manager.ParameterName;
import by.restonov.tyrent.model.entity.User;
import by.restonov.tyrent.model.exception.ServiceException;
import by.restonov.tyrent.model.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;


/**
 * Admin panel command. Shows profile page of app User
 *
 */
public class ShowClientProfileAdminCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger();
    private UserService service = new UserService();

    @Override
    public String execute(HttpServletRequest request) {
        String page = PageName.INDEX_PAGE;
        String userId = request.getParameter(ParameterName.USER_ID);
        try {
            Optional<User> optionalClient = service.findUserById(Long.parseLong(userId));
            if (optionalClient.isPresent()) {
                User client = optionalClient.get();
                request.setAttribute(AttributeName.USER, client);
                page = PageName.CLIENT_PROFILE_PAGE;
            }
        } catch (ServiceException e) {
            logger.error("Find user by id - error");
            page = PageName.INDEX_PAGE;
        }
        return page;
    }
}
