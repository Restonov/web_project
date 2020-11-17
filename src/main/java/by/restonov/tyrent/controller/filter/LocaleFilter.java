package by.restonov.tyrent.controller.filter;

import by.restonov.tyrent.manager.AttributeName;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Filter that sets default
 * ENG locale to the app
 */
@WebFilter
public class LocaleFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        if (session.getAttribute(AttributeName.LOCALE) == null) {
            session.setAttribute(AttributeName.LOCALE, AttributeName.EN);
        }
        chain.doFilter(request, response);
    }
}
