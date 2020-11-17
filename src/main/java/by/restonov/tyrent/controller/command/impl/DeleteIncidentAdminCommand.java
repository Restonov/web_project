package by.restonov.tyrent.controller.command.impl;

import by.restonov.tyrent.controller.command.ActionCommand;
import by.restonov.tyrent.manager.PageName;
import by.restonov.tyrent.manager.ParameterName;
import by.restonov.tyrent.model.exception.ServiceException;
import by.restonov.tyrent.model.service.IncidentService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

/**
 * Delete Incident from DB
 *
 */
public class DeleteIncidentAdminCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger();
    private IncidentService service = new IncidentService();

    @Override
    public String execute(HttpServletRequest request) {
        String incidentIdParam = request.getParameter(ParameterName.INCIDENT_ID);
        try {
            service.deleteIncident(Long.parseLong(incidentIdParam));
        } catch (ServiceException e) {
            logger.error("Delete incident {} - error", incidentIdParam, e);
        }
        return PageName.INCIDENT_LIST_PAGE;
    }
}
