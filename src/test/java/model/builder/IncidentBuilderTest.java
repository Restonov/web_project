package model.builder;

import by.restonov.tyrent.model.entity.impl.Incident;
import by.restonov.tyrent.model.service.builder.IncidentBuilder;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Map;

public class IncidentBuilderTest extends Assert {
    IncidentBuilder builder;
    Incident expectedIncident;
    Map<String, String> incidentData;

    @BeforeClass
    public void setUp() {
        builder = IncidentBuilder.INSTANCE;
        expectedIncident = new Incident();
    }

    @AfterClass
    public void tierDown() {
        builder = null;
        expectedIncident = null;
    }

    @Test
    public void test() {
        Incident actual = builder.build(incidentData);
        Incident expected = expectedIncident;
        AssertJUnit.assertEquals(expected, actual);


    }
}
