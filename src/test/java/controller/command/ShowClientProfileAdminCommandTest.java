package controller.command;

import by.restonov.tyrent.controller.command.ActionCommand;
import by.restonov.tyrent.controller.command.impl.ShowClientProfileAdminCommand;
import by.restonov.tyrent.manager.PageName;
import by.restonov.tyrent.manager.ParameterName;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.servlet.http.HttpServletRequest;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ShowClientProfileAdminCommandTest extends Assert {
    ActionCommand command;
    HttpServletRequest request;

    @BeforeClass
    public void setUp() {
        request = mock(HttpServletRequest.class);
        command = new ShowClientProfileAdminCommand();
    }

    @AfterClass
    public void tierDown() {
        request = null;
        command = null;
    }

    @Test
    public void executeCommandTest() {
        when(request.getParameter(ParameterName.USER_ID)).thenReturn("5");

        String expected = PageName.CLIENT_PROFILE_PAGE;
        String actual = command.execute(request);
        AssertJUnit.assertEquals(expected, actual);
    }
}
