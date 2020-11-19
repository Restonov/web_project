package by.restonov.tyrent.tag;

import by.restonov.tyrent.model.entity.impl.Incident;
import by.restonov.tyrent.model.exception.ServiceException;
import by.restonov.tyrent.model.service.IncidentService;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.List;

/**
 * List of the Incidents for Admin panel
 */
public class AdminIncidentListTag extends TagSupport {

    @Override
    public int doStartTag() {
        IncidentService service = new IncidentService();
        List<Incident> incidentList = null;
        try {
            incidentList = service.findIncidentList();
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        StringBuilder table = new StringBuilder();
        assert incidentList != null;
        for (Incident incident : incidentList) {
           table.append("<tr>")
                   .append("<th scope=\"row\">").append(incident.getIncidentId()).append("</th>")
                   .append("<td>").append(incident.getCarId()).append("</td>")
                   .append("<td>").append(incident.getUserId()).append("</td>")
                   .append("<td>").append(incident.getIncidentTypeInfo()).append("</td>")
                   .append("<td>").append(incident.getIncidentDescription()).append("</td>")
                   .append("<td>").append(showDeleteIncidentButton(incident.getIncidentId())).append("</td>")
                   .append("</tr>");
        }
        JspWriter out = pageContext.getOut();
        try {
            out.write(table.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return SKIP_BODY;
    }

    @Override
    public int doEndTag() {
        return EVAL_PAGE;
    }

    private String showDeleteIncidentButton(long incidentId) {
        StringBuilder button = new StringBuilder("<form method=\"GET\" action=\"controller\">");
        button.append("<input class=\"btn btn-primary\" type=\"submit\" value=\"")
                .append("DELETE")
                .append("\"/>")
                .append("<input type=\"hidden\" name=\"incident_id\" value=\"")
                .append(incidentId)
                .append("\"/>")
                .append("<input type=\"hidden\" name=\"command\" value=\"delete_incident\"/>")
                .append("</form>");
        return button.toString();
    }
}
