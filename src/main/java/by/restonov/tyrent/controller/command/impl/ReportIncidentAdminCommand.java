package by.restonov.tyrent.controller.command.impl;

import by.restonov.tyrent.controller.command.ActionCommand;
import by.restonov.tyrent.manager.PageName;
import by.restonov.tyrent.manager.ParameterName;
import by.restonov.tyrent.model.exception.ServiceException;
import by.restonov.tyrent.model.service.IncidentService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;


/**
 * Report order incident command
 */
public class ReportIncidentAdminCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger();
    private IncidentService service = new IncidentService();

    /**
     * Create new Incident from request parameters
     * and add it to DB
     *
     * @param request HttpRequest
     * @return Incident list admin page
     */
    @Override
    public String execute(HttpServletRequest request) {
        String incidentTypeId = request.getParameter(ParameterName.INCIDENT_TYPE_ID);
        String carId = request.getParameter(ParameterName.CAR_ID);
        String incidentDescription = request.getParameter(ParameterName.INCIDENT_DESCRIPTION);
        String userId = request.getParameter(ParameterName.USER_ID);

        Map<String, String> incidentData = new HashMap<>();
        incidentData.put(ParameterName.INCIDENT_TYPE_ID, incidentTypeId);
        incidentData.put(ParameterName.CAR_ID, carId);
        incidentData.put(ParameterName.INCIDENT_DESCRIPTION, incidentDescription);
        incidentData.put(ParameterName.USER_ID, userId);
        try {
            service.addNewIncident(incidentData);
        } catch (ServiceException e) {
            logger.error("Add incident to DB - error", e);
        }
        return PageName.INCIDENT_LIST_PAGE;
    }
}
