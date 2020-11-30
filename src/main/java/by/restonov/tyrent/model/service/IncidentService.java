package by.restonov.tyrent.model.service;

import by.restonov.tyrent.manager.ParameterName;
import by.restonov.tyrent.model.dao.EntityTransaction;
import by.restonov.tyrent.model.dao.impl.IncidentDaoImpl;
import by.restonov.tyrent.model.entity.impl.Incident;
import by.restonov.tyrent.model.exception.DaoException;
import by.restonov.tyrent.model.exception.ServiceException;
import by.restonov.tyrent.model.service.builder.IncidentBuilder;
import by.restonov.tyrent.util.XssAttackInterceptor;

import java.util.List;
import java.util.Map;

/**
 * Service, that works with incident data
 */
public class IncidentService {
    private IncidentDaoImpl dao;
    private EntityTransaction transaction;
    private IncidentBuilder builder;

    public IncidentService() {
        dao = new IncidentDaoImpl();
        transaction = new EntityTransaction();
        builder = IncidentBuilder.INSTANCE;
    }

    public IncidentService(IncidentDaoImpl dao, EntityTransaction transaction, IncidentBuilder builder) {
        this.dao = dao;
        this.transaction = transaction;
        this.builder = builder;
    }

    /**
     * Adding new Incident to DB
     *
     * @param incidentData Incident data from jsp form
     * @return operation result
     * @throws ServiceException default exception of service layer
     */
    public boolean addNewIncident(Map<String, String> incidentData) throws ServiceException {
        boolean result;
        String xssUncheckedText = incidentData.get(ParameterName.INCIDENT_DESCRIPTION);
        String xssCheckedText = XssAttackInterceptor.checkTextAndGet(xssUncheckedText);
        incidentData.put(ParameterName.INCIDENT_DESCRIPTION, xssCheckedText);
        Incident incident = builder.build(incidentData);
        try {
            transaction.initSingleQuery(dao);
            result = dao.add(incident);
        } catch (DaoException e) {
            throw new ServiceException("Add new incident - error", e);
        } finally {
            transaction.endSingleQuery();
        }
        return result;
    }

    /**
     * Find list of the incidents
     *
     * @return List of the incidents
     * @throws ServiceException default exception of service layer
     */
    public List<Incident> findIncidentList() throws ServiceException {
        List<Incident> incidentList;
        try {
            transaction.initSingleQuery(dao);
            incidentList = dao.findAll();
        } catch (DaoException e) {
            throw new ServiceException("Error while finding incident list", e);
        } finally {
            transaction.endSingleQuery();
        }
        return incidentList;
    }

    /**
     * Delete Incident from DB
     *
     * @param incidentId ID of the Incident
     * @return operation result
     * @throws ServiceException - default service layer exception
     */
    public boolean deleteIncident(long incidentId) throws ServiceException {
        boolean result;
        try {
            transaction.initSingleQuery(dao);
            result = dao.delete(incidentId);
        } catch (DaoException e) {
            throw new ServiceException("Error while deleting incident", e);
        } finally {
            transaction.endSingleQuery();
        }
        return result;
    }
}

