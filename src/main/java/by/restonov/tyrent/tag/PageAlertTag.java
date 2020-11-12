package by.restonov.tyrent.tag;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;
import java.io.IOException;

/**
 * Shows related alert on current page
 */
public class PageAlertTag extends BodyTagSupport {
    private String type;
    private boolean hide;

    public void setType(String type) {
        this.type = type;
    }

    public void setHide(boolean flag) {
        this.hide = flag;
    }

    @Override
    public int doStartTag() {
            StringBuilder alert = new StringBuilder();
               if (hide) {
                   alert.append("<script> setTimeout(function() { $('#alert').slideUp('slow'); }, 5000); </script>");
               }
               alert.append("<div id=\"alert\" class=\"alert alert-dismissible fade show alert-")
                    .append(type).append("\" role=\"alert\" style=\"margin-bottom: 0; text-align: center\">")
                    .append("<button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-label=\"Close\">")
                    .append("<span aria-hidden=\"true\">×</span></button><strong>");
            try {
                JspWriter out = pageContext.getOut();
                out.write(alert.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        return EVAL_BODY_INCLUDE;
    }

    @Override
    public int doEndTag() {
            String closeBody = "</strong></div>";
            try {
                JspWriter out = pageContext.getOut();
                out.write(closeBody);
            } catch (IOException e) {
                e.printStackTrace();
            }
        return EVAL_PAGE;
    }
}

