package controller.command;

import by.restonov.tyrent.controller.command.ActionCommand;
import by.restonov.tyrent.controller.command.impl.RegisterCommand;
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

public class RegisterCommandTest extends Assert {
    ActionCommand command;
    HttpServletRequest request;
    HttpSession session;

    @BeforeClass
    public void setUp() {
        request = mock(HttpServletRequest.class);
        command = new RegisterCommand();
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
        when(request.getParameter(ParameterName.USER_FIRST_NAME)).thenReturn("Oleg");
        when(request.getParameter(ParameterName.USER_LAST_NAME)).thenReturn("Tabakov");
        when(request.getParameter(ParameterName.USER_EMAIL)).thenReturn("oleg@test.by");
        when(request.getParameter(ParameterName.USER_PHONE)).thenReturn("375256210334");
        when(request.getParameter(ParameterName.USER_LOGIN)).thenReturn("OlegT");
        when(request.getParameter(ParameterName.USER_PASSWORD)).thenReturn("Qwe123");
        when(request.getSession()).thenReturn(session);

        String expected = PageName.MAIN_PAGE;
        String actual = command.execute(request);
        AssertJUnit.assertEquals(expected, actual);
    }
}
