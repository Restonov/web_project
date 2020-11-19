package controller.command;

import by.restonov.tyrent.controller.command.ActionCommand;
import by.restonov.tyrent.controller.command.impl.MakeOrderCommand;
import by.restonov.tyrent.manager.AttributeName;
import by.restonov.tyrent.manager.PageName;
import by.restonov.tyrent.manager.ParameterName;
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

public class MakeOrderCommandTest extends Assert {
    ActionCommand command;
    HttpServletRequest request;
    HttpSession session;
    User user;

    @BeforeClass
    public void setUp() {
        request = mock(HttpServletRequest.class);
        command = new MakeOrderCommand();
        session = mock(HttpSession.class);
        user = new User();
        user.setId(101);
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
        when(request.getParameter(ParameterName.CHOSEN_CAR_ID)).thenReturn("1");
        when(session.getAttribute(AttributeName.USER)).thenReturn(user);

        String expected = PageName.USER_PROFILE_PAGE;
        String actual = command.execute(request);
        AssertJUnit.assertEquals(expected, actual);
    }
}
