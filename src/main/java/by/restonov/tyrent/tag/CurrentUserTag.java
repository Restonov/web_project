package by.restonov.tyrent.tag;

import by.restonov.tyrent.model.entity.User;
import by.restonov.tyrent.manager.AttributeName;
import by.restonov.tyrent.manager.PageContentManager;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

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
                StringBuilder userInfo = new StringBuilder("<form method=\"POST\" action=\"controller\">");
                userInfo.append("<input class=\"nav-link\" type=\"submit\" value=\"")
                .append(currentUser).append(userLogin).append(" (").append(userRole).append(")")
                .append("\" style=\"outline: 0; border: 0; background: 0; margin-top: 20px; color: white\">")
                .append("<input type=\"hidden\" name=\"path\" value=\"user_profile_page\"/>")
                .append("<input type=\"hidden\" name=\"command\" value=\"forward\" /></form>");
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

