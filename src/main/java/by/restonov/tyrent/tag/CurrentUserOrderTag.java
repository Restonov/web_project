package by.restonov.tyrent.tag;

import by.restonov.tyrent.entity.User;
import by.restonov.tyrent.exception.ServiceException;
import by.restonov.tyrent.manager.AttributeName;
import by.restonov.tyrent.manager.ParameterName;
import by.restonov.tyrent.model.service.UserOrderService;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class CurrentUserOrderTag extends TagSupport {

    @Override
    public int doStartTag() {
        User user = (User) pageContext.getSession().getAttribute(AttributeName.USER);
        List<Integer> ordersId = user.getOrdersId();
        if (!ordersId.isEmpty()) {
            UserOrderService orderService = new UserOrderService();
            for (Integer orderId : ordersId) {
                Map<String, Object> orderInfo;
                try {
                    orderInfo = orderService.findOrderInfo(orderId);
                    printRow(orderInfo);
                } catch (ServiceException e) {
                    e.printStackTrace();
                }
            }
        } else {
            JspWriter out = pageContext.getOut();
            try {
                //TODO localization
                out.write("NO ORDERS YET");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return SKIP_BODY;
    }

    @Override
    public int doEndTag() {
        return EVAL_PAGE;
    }

    //TODO JSP localization
    private void printRow(Map<String, Object> orderInfo) {
        StringBuilder row = new StringBuilder();
            row.append("<tr>")
                    .append("<th scope=\"row\">").append(orderInfo.get(ParameterName.ORDER_ID)).append("</th>")
                    .append("<td>").append(orderInfo.get(ParameterName.NAME)).append("</td>")
                    .append("<td>").append(orderInfo.get(ParameterName.COST)).append("</td>")
                    .append("<td>").append(orderInfo.get(ParameterName.ORDER_TIMESTAMP)).append("</td>")
                    .append("<td>").append(orderInfo.get(ParameterName.STATE)).append("</td>")
                    .append("</tr>");
        JspWriter out = pageContext.getOut();
        try {
            out.write(row.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
