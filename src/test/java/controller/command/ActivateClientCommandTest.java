package controller.command;

import by.restonov.tyrent.controller.command.ActionCommand;
import by.restonov.tyrent.controller.command.impl.ActivateAccountCommand;
import by.restonov.tyrent.manager.AttributeName;
import by.restonov.tyrent.manager.PageName;
import by.restonov.tyrent.manager.ParameterName;
import by.restonov.tyrent.model.entity.User;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.mockito.Mockito.*;

public class ActivateClientCommandTest extends Assert {
    ActionCommand command;
    HttpServletRequest request;
    User user;
    HttpSession session;

    @BeforeClass
    public void setUp() {
        request = mock(HttpServletRequest.class);
        command = new ActivateAccountCommand();
        session = mock(HttpSession.class);
        user = new User("Restonov");
    }

    @AfterClass
    public void tierDown() {
        request = null;
        command = null;
        session = null;
        user = null;
    }

    @Test
    public void executeCommandTest() {
        when(request.getParameter(ParameterName.USER_DATA)).thenReturn("$31$16$C0fcSqlHxSVsjhP33ToR5rsu6b3VYWvmvphhhliswk0");
        when(request.getParameter(ParameterName.USER_EMAIL)).thenReturn("maxplyushko@gmail.com");
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute(AttributeName.USER)).thenReturn(user);

        String expected = PageName.USER_PROFILE_PAGE;
        String actual = command.execute(request);
        AssertJUnit.assertEquals(expected, actual);
    }
}
