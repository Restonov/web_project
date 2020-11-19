package by.restonov.tyrent.tag;

import by.restonov.tyrent.model.entity.impl.User;
import by.restonov.tyrent.model.entity.impl.UserOrder;
import by.restonov.tyrent.model.exception.ServiceException;
import by.restonov.tyrent.model.service.UserOrderService;
import by.restonov.tyrent.util.DataTimeFormatter;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

/**
 * Shows to current User his Orders
 */
public class CurrentUserOrderTag extends TagSupport {
    private User user;

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public int doStartTag() {
        UserOrderService orderService = new UserOrderService();
        Optional<List<UserOrder>> optionalOrderList = Optional.empty();
        try {
            optionalOrderList = orderService.findOrderListByUserId(user.getId());
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        if (optionalOrderList.isPresent()) {
            List<UserOrder> orderList = optionalOrderList.get();
            for (UserOrder userOrder : orderList) {
                printRow(userOrder);
            }
        }
        return SKIP_BODY;
    }

    @Override
    public int doEndTag() {
        return EVAL_PAGE;
    }

    private void printRow(UserOrder userOrder) {
        String orderDateTime = DataTimeFormatter.showFormattedDateTime(userOrder.getCreationDateTime());
        StringBuilder row = new StringBuilder();
            row.append("<tr>")
                    .append("<th scope=\"row\">").append(userOrder.getOrderId()).append("</th>")
                    .append("<td>").append(userOrder.getCarName()).append("</td>")
                    .append("<td>").append(userOrder.getCarCost()).append("</td>")
                    .append("<td>").append(orderDateTime).append("</td>")
                    .append("<td>").append(userOrder.getState()).append("</td>")
                    .append("</tr>");
        JspWriter out = pageContext.getOut();
        try {
            out.write(row.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
