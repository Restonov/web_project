package controller.command;

import by.restonov.tyrent.controller.command.ActionCommand;
import by.restonov.tyrent.controller.command.impl.ShowCarInfoCommand;
import by.restonov.tyrent.manager.AttributeName;
import by.restonov.tyrent.manager.PageName;
import by.restonov.tyrent.manager.ParameterName;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ShowCarInfoCommandTest extends Assert {
    ActionCommand command;
    HttpServletRequest request;
    HttpSession session;

    @BeforeClass
    public void setUp() {
        request = mock(HttpServletRequest.class);
        command = new ShowCarInfoCommand();
        session = mock(HttpSession.class);
    }

    @AfterClass
    public void tierDown() {
        request = null;
        command = null;
        session = null;
    }

    @Test
    public void executeCommandTest() {
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute(AttributeName.LOCALE)).thenReturn("en");
        when(request.getParameter(ParameterName.CHOSEN_CAR_ID)).thenReturn("1");

        String expected = PageName.CAR_INFO_PAGE;
        String actual = command.execute(request);
        AssertJUnit.assertEquals(expected, actual);
    }
}
