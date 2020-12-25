package by.restonov.tyrent.model.dao.impl;

import by.restonov.tyrent.model.dao.AbstractDao;
import by.restonov.tyrent.model.dao.builder.IncidentDaoBuilder;
import by.restonov.tyrent.model.entity.impl.Incident;
import by.restonov.tyrent.model.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class IncidentDaoImpl extends AbstractDao<Long, Incident> {
    private static final Logger logger = LogManager.getLogger();
    private static final String INSERT_NEW_INCIDENT = "INSERT INTO incidents (incident_description, car_id, user_id, incident_type_id, incident_timestamp) VALUES (?, ?, ?, ?, ?)";
    private static final String SELECT_ALL_INCIDENTS =
            "SELECT incident_id, incident_description, car_id, user_id, incidents.incident_type_id, incident_type_info, incident_timestamp FROM incidents INNER JOIN incident_type ON incidents.incident_type_id = incident_type.incident_type_id";
    private static final String DELETE_INCIDENT = "DELETE FROM incidents WHERE incident_id = ?";

    @Override
    public List<Incident> findAll() throws DaoException {
        List<Incident> incidentList = new ArrayList<>();
        IncidentDaoBuilder builder = new IncidentDaoBuilder();
        ResultSet resultSet = null;
        try (PreparedStatement statement = connection.prepareStatement(SELECT_ALL_INCIDENTS)){
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Incident incident = builder.build(resultSet);
                incidentList.add(incident);
            }
        } catch (SQLException e) {
            throw new DaoException("Error while finding all incidents", e);
        } finally {
            assert resultSet != null;
            closeResultSet(resultSet);
        }
        return incidentList;
    }

    @Override
    public Optional<Incident> findById(Long id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean add(Incident incident) throws DaoException {
        boolean result = false;
        if (incident != null) {
            try(PreparedStatement statement = connection.prepareStatement(INSERT_NEW_INCIDENT)){
                statement.setString(1, incident.getIncidentDescription());
                statement.setLong(2, incident.getCarId());
                statement.setLong(3, incident.getUserId());
                statement.setInt(4, incident.getIncidentTypeId());
                Timestamp timestamp = Timestamp.valueOf(incident.getDateTime());
                statement.setTimestamp(5, timestamp);
                statement.executeUpdate();
                result = true;
                logger.info("Incident was successfully added to database");
            } catch (SQLException e) {
                throw new DaoException("Error while adding new incident", e);
            }
        }
        return result;
    }

    @Override
    public boolean update(Incident entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean delete(Long id) throws DaoException {
        boolean result = false;
        if (id != null) {
            try(PreparedStatement statement = connection.prepareStatement(DELETE_INCIDENT)){
                statement.setLong(1, id);
                statement.executeUpdate();
                result = true;
                logger.info("Incident {} was successfully deleted from database", id);
            } catch (SQLException e) {
                throw new DaoException("Error while deleting incident", e);
            }
        }
        return result;
    }
}
