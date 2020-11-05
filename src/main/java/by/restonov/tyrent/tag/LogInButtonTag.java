package by.restonov.tyrent.tag;

import by.restonov.tyrent.entity.User;
import by.restonov.tyrent.manager.AttributeName;
import by.restonov.tyrent.manager.PageContentManager;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

@SuppressWarnings("serial")
public class LogInButtonTag extends TagSupport {

    @Override
    public int doStartTag() throws JspException {
        User user = (User) pageContext.getSession().getAttribute(AttributeName.USER);
        String language = (String) pageContext.getSession().getAttribute(AttributeName.LOCALE);
        String logout = PageContentManager.findProperty("button.label.logout", language);
        String login = PageContentManager.findProperty("button.label.login", language);

        if (user != null) {
            String button = "<input class=\"btn btn-primary\" type=\"submit\" value=\"" + logout + "\"/>";
            String hidden = "<input type=\"hidden\" name=\"command\" value=\"logout\"/>";
            try {
                JspWriter out = pageContext.getOut();
                out.write(button);
                out.write(hidden);
            } catch (IOException e) {
                throw new JspException(e.getMessage());
            }
        } else {
            String button = "<button class=\"btn btn-primary\" type=\"submit\" name=\"path\" value=\"login_page\">" + login + "</button>";
            String hidden = "<input type=\"hidden\" name=\"command\" value=\"forward\"/>";
            try {
                JspWriter out = pageContext.getOut();
                out.write(button);
                out.write(hidden);
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
}