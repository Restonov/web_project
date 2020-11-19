package controller.command;

import by.restonov.tyrent.controller.command.ActionCommand;
import by.restonov.tyrent.controller.command.impl.LogoutCommand;
import by.restonov.tyrent.manager.AttributeName;
import by.restonov.tyrent.manager.PageName;
import by.restonov.tyrent.model.entity.impl.User;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class LogoutCommandTest extends Assert {
    ActionCommand command;
    HttpServletRequest request;
    HttpSession session;
    User user;

    @BeforeClass
    public void setUp() {
        request = mock(HttpServletRequest.class);
        command = new LogoutCommand();
        session = mock(HttpSession.class);
        user = new User();
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
        when(session.getAttribute(AttributeName.USER)).thenReturn(user);

        String expected = PageName.INDEX_PAGE;
        String actual = command.execute(request);
        AssertJUnit.assertEquals(expected, actual);
    }
}