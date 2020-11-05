package by.restonov.tyrent.tag;

import by.restonov.tyrent.entity.UserOrder;
import by.restonov.tyrent.exception.ServiceException;
import by.restonov.tyrent.model.service.UserOrderService;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.List;

public class AdminOrderListTag extends TagSupport {

    @Override
    public int doStartTag() {
        UserOrderService service = new UserOrderService();
        List<UserOrder> orderList = null;
        try {
            orderList = service.findOrderList();
            printTable(orderList);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return SKIP_BODY;
    }

    @Override
    public int doEndTag() {
        return EVAL_PAGE;
    }
    //TODO JSP localization
    private void printTable(List<UserOrder> orderList) {
        StringBuilder table = new StringBuilder();
        for (UserOrder order : orderList) {
            table.append("<tr>")
                    .append("<th scope=\"row\">").append(order.getOrderId()).append("</th>")
                    .append("<td>").append(order.getCarId()).append("</td>")
                    .append("<td>").append(order.getCreationDateTime()).append("</td>")
                    .append("<td>").append(order.getState()).append("</td>")
                    .append("</tr>");
        }
        JspWriter out = pageContext.getOut();
        try {
            out.write(table.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
