package by.restonov.tyrent.tag;

import by.restonov.tyrent.manager.ParameterName;
import by.restonov.tyrent.model.entity.UserOrder;
import by.restonov.tyrent.model.exception.ServiceException;
import by.restonov.tyrent.model.service.UserOrderService;
import by.restonov.tyrent.util.DataTimeFormatter;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

/**
 * List of the Orders for Admin panel
 */
public class AdminOrderListTag extends TagSupport {

    @Override
    public int doStartTag() {
        UserOrderService service = new UserOrderService();
        List<UserOrder> orderList;
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

    private void printTable(List<UserOrder> orderList) {
        StringBuilder table = new StringBuilder();
        for (UserOrder order : orderList) {
            String orderDateTime = DataTimeFormatter.showFormattedDateTime(order.getCreationDateTime());
            table.append("<tr>")
                    .append("<th scope=\"row\">").append(order.getOrderId()).append("</th>")
                    .append("<td>").append(order.getCarName()).append("</td>")
                    .append("<td>").append(order.getUserId()).append("</td>")
                    .append("<td>").append(orderDateTime).append("</td>")
                    .append("<td>").append(showOrderStateChooser(order)).append("</td>")
                    .append("</tr>");
        }
        JspWriter out = pageContext.getOut();
        try {
            out.write(table.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String showOrderStateChooser(UserOrder order) {
        StringBuilder orderState = new StringBuilder();
        orderState.append("<form action=\"controller\" method=\"post\">")
                .append("<p style=\"float: left\">")
                .append("<select name=\"order_state\">")
                .append("<option hidden>").append(order.getState().toString().toLowerCase()).append("</option>")
                .append("<option value=PROCESSED>").append(UserOrder.State.PROCESSED.toString().toLowerCase()).append("</option>")
                .append("<option value=CANCELED>").append(UserOrder.State.CANCELED.toString().toLowerCase()).append("</option>")
                .append("<option value=COMPLETED>").append(UserOrder.State.COMPLETED.toString().toLowerCase()).append("</option>")
                .append("</select>")
                .append("<input type=\"hidden\" name=\"command\" value=\"change_order_state\" />")
                .append("<input type=\"hidden\" name=\"order_id\" value=\"").append(order.getOrderId()).append("\" />")
                .append("<input type=\"submit\" value=\"OK\">")
                .append(" </form>");
        return orderState.toString();
    }
}
