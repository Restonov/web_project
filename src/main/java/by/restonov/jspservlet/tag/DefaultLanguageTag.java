package by.restonov.jspservlet.tag;

import javax.servlet.jsp.tagext.TagSupport;

@SuppressWarnings("serial")
public class DefaultLanguageTag extends TagSupport {

    @Override
    public int doStartTag() {
        String language = (String) pageContext.getSession().getAttribute("locale");
        if (language == null) {
            pageContext.getSession().setAttribute("locale", "en");
        }
        return SKIP_BODY;
    }

    @Override
    public int doEndTag() {
        return EVAL_PAGE;
    }
}
