package by.restonov.tyrent.controller.filter;

import by.restonov.tyrent.manager.AttributeName;
import by.restonov.tyrent.model.entity.impl.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


/**
 * Filter that checks if User in the session
 * is Administrator
 * and activate Admin panel
 */
@WebFilter
public class AdministratorFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();

        User user = (User) session.getAttribute(AttributeName.USER);
        if (user != null && user.getRole() == User.Role.ADMINISTRATOR) {
            session.setAttribute(AttributeName.ACTIVATE_ADMIN_PANEL, true);
        }
        chain.doFilter(request, response);
    }
}
