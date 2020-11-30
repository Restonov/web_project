package by.restonov.tyrent.model.service.builder;

import by.restonov.tyrent.manager.ParameterName;
import by.restonov.tyrent.model.entity.impl.Incident;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * Reported Incident builder
 */
public enum IncidentBuilder{

    /**
     * Thread-safe singleton instance
     */
    INSTANCE;

    /**
     * Build new Incident
     *
     * @param incidentData Incident data from jsp form
     * @return new Incident
     */
    public Incident build(Map<String, String> incidentData) {
        String incidentTypeId = incidentData.get(ParameterName.INCIDENT_TYPE_ID);
        String carId = incidentData.get(ParameterName.CAR_ID);
        String incidentDescription = incidentData.get(ParameterName.INCIDENT_DESCRIPTION);
        String incidentTypeInfo = incidentData.get(ParameterName.INCIDENT_TYPE_INFO);
        String userId = incidentData.get(ParameterName.USER_ID);
        LocalDateTime dateTime = LocalDateTime.now();

        Incident incident = new Incident();
        incident.setIncidentTypeId(Integer.parseInt(incidentTypeId));
        incident.setCarId(Long.parseLong(carId));
        incident.setIncidentDescription(incidentDescription);
        incident.setUserId(Long.parseLong(userId));
        incident.setDateTime(dateTime);
        incident.setIncidentTypeInfo(incidentTypeInfo);
        return incident;
    }
}
