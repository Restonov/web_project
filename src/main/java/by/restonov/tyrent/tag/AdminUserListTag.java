package by.restonov.tyrent.tag;

import by.restonov.tyrent.model.entity.User;
import by.restonov.tyrent.model.exception.ServiceException;
import by.restonov.tyrent.model.service.UserService;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.List;

public class AdminUserListTag extends TagSupport {

    @Override
    public int doStartTag() {
        UserService service = new UserService();
        List<User> userList = null;
        try {
            userList = service.findUserList();
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        StringBuilder showProfileButton = new StringBuilder("<form method=\"POST\" action=\"controller\">");
        showProfileButton.append("<button class=\"btn btn-primary\" type=\"submit\" name=\"path\"")
                .append(" value=\"user_profile_page\">")
                .append("Show profile")
                .append("</button>")
                .append("<input type=\"hidden\" name=\"command\" value=\"forward\"/>")
                .append("</form>");
        StringBuilder table = new StringBuilder();
        assert userList != null;
        for (User user : userList) {
           table.append("<tr>")
                   .append("<th scope=\"row\">").append(user.getId()).append("</th>")
                   .append("<td>").append(user.getFirstName()).append("</td>")
                   .append("<td>").append(user.getLastName()).append("</td>")
                   .append("<td>").append(user.getLogin()).append("</td>")
                   .append("<td>").append(user.getEmail()).append("</td>")
                   .append("<td>").append(user.getPhone()).append("</td>")
                   .append("<td>").append(user.getRole().toString()).append("</td>")
                   .append("<td>").append(user.getState().toString()).append("</td>")
                   .append("<td>").append(showProfileButton.toString()).append("</td>")
                   .append("</tr>");
        }
        JspWriter out = pageContext.getOut();
        try {
            out.write(table.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return SKIP_BODY;
    }

    @Override
    public int doEndTag() {
        return EVAL_PAGE;
    }
}
