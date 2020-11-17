package controller.command;

import by.restonov.tyrent.controller.command.ActionCommand;
import by.restonov.tyrent.controller.command.impl.ChangeOrderStateAdminCommand;
import by.restonov.tyrent.manager.ParameterName;
import by.restonov.tyrent.model.entity.UserOrder;
import org.testng.Assert;
import org.testng.annotations.*;

import javax.servlet.http.HttpServletRequest;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ChangeOrderStateAdminCommandTest extends Assert {
    ActionCommand command;
    HttpServletRequest request;
    UserOrder.State orderState;

    @BeforeMethod
    public void setUp() {
        request = mock(HttpServletRequest.class);
        command = new ChangeOrderStateAdminCommand();
        orderState = UserOrder.State.COMPLETED;
    }

    @AfterMethod
    public void tierDown() {
        request = null;
        command = null;
        orderState = null;
    }

    @Test
    public void executeCommandTest() {
        String orderState = "COMPLETED";
        String orderId = "57866373400";

        when(request.getParameter(ParameterName.ORDER_STATE)).thenReturn(orderState);
        when(request.getParameter(ParameterName.ORDER_ID)).thenReturn(orderId);
        command.execute(request);
    }
}
