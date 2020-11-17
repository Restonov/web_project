package controller;

import by.restonov.tyrent.controller.Controller;
import static org.mockito.Mockito.*;

import by.restonov.tyrent.controller.command.CommandType;
import by.restonov.tyrent.manager.PageName;
import by.restonov.tyrent.manager.ParameterName;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ControllerTest extends Assert {
        Controller controller;
        HttpServletRequest request;
        HttpServletResponse response;
        RequestDispatcher dispatcher;

    @BeforeClass
    public void setUp() {
        controller = new Controller();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        dispatcher = mock(RequestDispatcher.class);
    }

    @AfterClass
    public void tierDown() {
        controller = null;
        request = null;
        response = null;
        dispatcher = null;
    }

    @Test
    public void processRequestForwardCommandTest() throws ServletException, IOException {
        when(request.getParameter(ParameterName.COMMAND)).thenReturn(CommandType.FORWARD.toString());
        when(request.getContextPath()).thenReturn(ParameterName.PATH);
        when(request.getParameter(ParameterName.PATH)).thenReturn(PageName.LOGIN_PAGE);
        doThrow(new Exception()).doNothing().when(dispatcher).forward(request, response);

        controller.processRequest(request, response);

        verify(request, times(1)).getParameter(ParameterName.COMMAND);
        verify(dispatcher, times(1)).forward(request, response);
    }

    @Test
    public void processRequestEmptyCommandTest() throws ServletException, IOException {
        when(request.getContextPath()).thenReturn(ParameterName.PATH);

        controller.processRequest(request, response);

        verify(request, times(1)).getParameter(ParameterName.COMMAND);
        verify(request, times(1)).getContextPath();
        verify(response).sendRedirect(ParameterName.PATH + PageName.ERROR_404_PATH);
    }
}
