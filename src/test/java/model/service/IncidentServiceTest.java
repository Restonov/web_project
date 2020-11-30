package model.service;

import by.restonov.tyrent.manager.ParameterName;
import by.restonov.tyrent.model.dao.EntityTransaction;
import by.restonov.tyrent.model.dao.impl.IncidentDaoImpl;
import by.restonov.tyrent.model.entity.impl.Incident;
import by.restonov.tyrent.model.exception.DaoException;
import by.restonov.tyrent.model.exception.ServiceException;
import by.restonov.tyrent.model.service.IncidentService;
import by.restonov.tyrent.model.service.builder.IncidentBuilder;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.modules.testng.PowerMockTestCase;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.*;

@RunWith(PowerMockRunner.class)
@PowerMockIgnore({"com.sun.org.apache.xerces.*", "javax.xml.*", "javax.crypto.*", "org.xml.*", "org.w3c.*",
        "com.sun.org.apache.xalan.*" })
@PrepareForTest(IncidentBuilder.class)
public class IncidentServiceTest extends PowerMockTestCase {
    IncidentService service;
    EntityTransaction transaction;
    IncidentDaoImpl dao;
    IncidentBuilder builder;
    Incident incident;
    Map<String, String> incidentData;

    @BeforeClass
    public void setUp() {
        dao = mock(IncidentDaoImpl.class);
        transaction = mock(EntityTransaction.class);
        builder = mock(IncidentBuilder.class);
        service = new IncidentService(dao, transaction, builder);

        incidentData = new HashMap<>();
        incidentData.put(ParameterName.INCIDENT_TYPE_ID, "101");
        incidentData.put(ParameterName.CAR_ID, "5");
        incidentData.put(ParameterName.INCIDENT_DESCRIPTION, "agreement");
        incidentData.put(ParameterName.USER_ID, "4");
        incident = new Incident();
    }

    @AfterClass
    public void tearDown() {
        dao = null;
        transaction = null;
        builder = null;
        service = null;
        incidentData = null;
        incident = null;
    }

    @Test
    public void addNewIncidentTest() throws DaoException, ServiceException {
        when(builder.build(incidentData)).thenReturn(incident);
        doNothing().when(transaction).initSingleQuery(dao);
        when(dao.add(incident)).thenReturn(true);
        doNothing().when(transaction).endSingleQuery();

        boolean result = service.addNewIncident(incidentData);
        verify(transaction).initSingleQuery(dao);
        verify(dao).add(incident);
        verify(transaction).endSingleQuery();

        Assert.assertTrue(result);
    }

    @Test
    public void findIncidentListTest() throws DaoException, ServiceException {
        List<Incident> incidentList = List.of(incident, incident);
        doNothing().when(transaction).initSingleQuery(dao);
        when(dao.findAll()).thenReturn(incidentList);
        doNothing().when(transaction).endSingleQuery();

        List<Incident> resultList = service.findIncidentList();
        verify(transaction).initSingleQuery(dao);
        verify(dao).findAll();
        verify(transaction).endSingleQuery();

        Assert.assertFalse(resultList.isEmpty());
    }

    @Test
    public void deleteIncidentTest() throws DaoException, ServiceException {
        long incidentId = 5;
        doNothing().when(transaction).initSingleQuery(dao);
        when(dao.delete(incidentId)).thenReturn(true);
        doNothing().when(transaction).endSingleQuery();

        boolean result = service.deleteIncident(incidentId);
        verify(transaction).initSingleQuery(dao);
        verify(dao).delete(incidentId);
        verify(transaction).endSingleQuery();

        Assert.assertTrue(result);
    }


}
