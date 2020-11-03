package by.restonov.jspservlet.tag;

import by.restonov.jspservlet.entity.User;
import by.restonov.jspservlet.manager.AttributeName;
import by.restonov.jspservlet.manager.PageContentManager;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

@SuppressWarnings("serial")
public class BookCarButtonTag extends TagSupport {

    @Override
    public int doStartTag() throws JspException {
        User user = (User) pageContext.getSession().getAttribute(AttributeName.USER);
        String language = (String) pageContext.getSession().getAttribute(AttributeName.LOCALE);
        String textBookCar = PageContentManager.findProperty("button.book", language);
        StringBuilder button = new StringBuilder("<input class=\"btn btn-outline-secondary\" type=\"submit\" value=\"");
                button.append(textBookCar).append("\"/>");
        if (user != null) {
            button.append("<input type=\"hidden\" name=\"command\" value=\"make_order\"/>");
            try {
                JspWriter out = pageContext.getOut();
                out.write(button.toString());
            } catch (IOException e) {
                throw new JspException(e.getMessage());
            }
        } else {
            button.append("<input type=\"hidden\" name=\"path\" value=\"login_page\"/>")
                  .append("<input type=\"hidden\" name=\"command\" value=\"forward\"/>");
            try {
                JspWriter out = pageContext.getOut();
                out.write(button.toString());
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
