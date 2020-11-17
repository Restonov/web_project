package controller.factory;

import by.restonov.tyrent.controller.command.ActionCommand;
import by.restonov.tyrent.controller.command.CommandType;
import by.restonov.tyrent.controller.factory.ActionFactory;
import by.restonov.tyrent.manager.ParameterName;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.servlet.http.HttpServletRequest;

import java.util.Optional;

import static org.mockito.Mockito.*;

public class ActionFactoryTest extends Assert {
    HttpServletRequest request;
    Optional<ActionCommand> loginCommand;

    @BeforeClass
    public void setUp() {
        request = mock(HttpServletRequest.class);
        loginCommand = Optional.of(CommandType.LOGIN.getCurrentCommand());
    }

    @AfterClass
    public void tierDown() {
        request = null;
        loginCommand = Optional.empty();
    }

    @Test
    public void executeCommandPositiveTest() {
        when(request.getParameter(ParameterName.COMMAND)).thenReturn(CommandType.LOGIN.toString().toLowerCase());
        Optional<ActionCommand> expected = loginCommand;
        Optional<ActionCommand> actual = ActionFactory.defineCommand(request);
        AssertJUnit.assertEquals(expected, actual);
    }

    @Test
    public void executeCommandNegativeTest() {
        when(request.getParameter(ParameterName.COMMAND)).thenReturn(" ");
        Optional<ActionCommand> expected = loginCommand;
        Optional<ActionCommand> actual = ActionFactory.defineCommand(request);
        AssertJUnit.assertNotSame(expected, actual);
    }
}
