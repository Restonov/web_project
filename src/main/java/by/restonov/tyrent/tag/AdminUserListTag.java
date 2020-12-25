package by.restonov.tyrent.tag;

import by.restonov.tyrent.model.entity.impl.User;
import by.restonov.tyrent.model.exception.ServiceException;
import by.restonov.tyrent.model.service.UserService;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.List;

/**
 * List of the Users for Admin panel
 */
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
                   .append("<td>").append(showUserStateChooser(user)).append("</td>")
                   .append("<td>").append(showOpenProfileButton(user.getId())).append("</td>")
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

    private String showUserStateChooser(User user) {
        String currentState;
        if (user.getRole() != User.Role.ADMINISTRATOR) {
            StringBuilder userState = new StringBuilder();
            userState.append("<form action=\"controller\" method=\"post\">")
                    .append("<p style=\"float: left\">")
                    .append("<select name=\"user_state\">")
                    .append("<option hidden>").append(user.getState().toString().toLowerCase()).append("</option>")
                    .append("<option value=ACTIVATED>").append(User.State.ACTIVATED.toString().toLowerCase()).append("</option>")
                    .append("<option value=NOT_ACTIVATED>").append(User.State.NOT_ACTIVATED.toString().toLowerCase()).append("</option>")
                    .append("<option value=BLOCKED>").append(User.State.BLOCKED.toString().toLowerCase()).append("</option>")
                    .append("</select>")
                    .append("<input type=\"hidden\" name=\"command\" value=\"change_user_state\" />")
                    .append("<input type=\"hidden\" name=\"user_id\" value=\"").append(user.getId()).append("\" />")
                    .append("<input type=\"submit\" value=\"OK\">")
                    .append(" </form>");
            currentState = userState.toString();
        } else {
            currentState = user.getState().toString();
        }
        return currentState;
    }

    private String showOpenProfileButton(long chosenUserId) {
        StringBuilder openProfileButton = new StringBuilder("<form method=\"GET\" action=\"controller\">");
        openProfileButton.append("<input class=\"btn btn-primary\" type=\"submit\" value=\"")
                .append("Show profile")
                .append("\"/>")
                .append("<input type=\"hidden\" name=\"user_id\" value=\"")
                .append(chosenUserId)
                .append("\"/>")
                .append("<input type=\"hidden\" name=\"command\" value=\"show_client_profile\"/>")
                .append("</form>");
        return openProfileButton.toString();
    }
}
