package by.restonov.tyrent.model.dao.builder;

import by.restonov.tyrent.manager.ParameterName;
import by.restonov.tyrent.model.entity.impl.Incident;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

/**
 * Incident builder
 */
public class IncidentDaoBuilder {

    /**
     * Build Incident from ResultSet data
     *
     * @param set ResultSet
     * @return Incident instance
     * @throws SQLException DB access error
     */
    public Incident build(ResultSet set) throws SQLException {
        long incidentId = set.getLong(ParameterName.INCIDENT_ID);
        String description = set.getString(ParameterName.INCIDENT_DESCRIPTION);
        long carId = set.getLong(ParameterName.CAR_ID);
        long userId = set.getLong(ParameterName.USER_ID);
        String typeInfo = set.getString(ParameterName.INCIDENT_TYPE_INFO);
        LocalDateTime dateTime = set.getTimestamp(ParameterName.INCIDENT_TIMESTAMP).toLocalDateTime();

        Incident incident = new Incident();
        incident.setIncidentId(incidentId);
        incident.setIncidentDescription(description);
        incident.setCarId(carId);
        incident.setUserId(userId);
        incident.setIncidentTypeInfo(typeInfo);
        incident.setDateTime(dateTime);
        return incident;
    }
}
