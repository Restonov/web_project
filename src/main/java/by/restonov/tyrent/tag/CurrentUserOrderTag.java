package by.restonov.tyrent.tag;

import by.restonov.tyrent.model.entity.User;
import by.restonov.tyrent.model.exception.ServiceException;
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
        UserOrderService orderService = new UserOrderService();
        List<Map<String, Object>> orderList = null;
        try {
            orderList = orderService.findOrderListByUserId(user.getId());
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        if (orderList != null) {
            for (Map<String, Object> order : orderList) {
                printRow(order);
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
                    .append("<td>").append(orderInfo.get(ParameterName.CAR_NAME)).append("</td>")
                    .append("<td>").append(orderInfo.get(ParameterName.CAR_COST)).append("</td>")
                    .append("<td>").append(orderInfo.get(ParameterName.ORDER_TIMESTAMP)).append("</td>")
                    .append("<td>").append(orderInfo.get(ParameterName.ORDER_STATE)).append("</td>")
                    .append("</tr>");
        JspWriter out = pageContext.getOut();
        try {
            out.write(row.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
