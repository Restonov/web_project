package by.restonov.tyrent.tag;

import by.restonov.tyrent.model.entity.impl.User;
import by.restonov.tyrent.manager.AttributeName;
import by.restonov.tyrent.manager.PageContentManager;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

/**
 * Current User profile button that shows
 * User login and Role
 */
@SuppressWarnings("serial")
public class CurrentUserTag extends TagSupport{

        @Override
        public int doStartTag() throws JspException {
            HttpSession session = pageContext.getSession();
            User user = (User) session.getAttribute(AttributeName.USER);
            if (user != null) {
                String language = (String) session.getAttribute(AttributeName.LOCALE);
                String currentUser = PageContentManager.findProperty("header.current", language);
                String userRole = user.getRole().toString().toLowerCase();
                String userLogin = user.getLogin();
                StringBuilder userInfo = new StringBuilder("<form method=\"GET\" action=\"controller\">");
                userInfo.append("<input class=\"nav-link\" type=\"submit\" value=\"")
                .append(currentUser).append(userLogin).append(" (").append(userRole).append(")")
                .append("\" style=\"outline: 0; border: 0; background: 0; margin-top: 20px; color: white\">")
                .append("<input type=\"hidden\" name=\"command\" value=\"show_user_profile\" /></form>");
                try {
                    JspWriter out = pageContext.getOut();
                    out.write(userInfo.toString());
                } catch (IOException e) {
                    throw new JspException(e.getMessage());
                }
            }
            return SKIP_BODY;
        }

        @Override
        public int doEndTag() {
            return EVAL_PAGE;
        }
}

