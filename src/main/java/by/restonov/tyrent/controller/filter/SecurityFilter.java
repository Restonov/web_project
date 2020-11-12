package by.restonov.tyrent.controller.filter;

import by.restonov.tyrent.controller.command.CommandEnum;
import by.restonov.tyrent.manager.AttributeName;
import by.restonov.tyrent.manager.PageName;
import by.restonov.tyrent.manager.ParameterName;
import by.restonov.tyrent.model.entity.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.EnumMap;
import java.util.List;

//@WebFilter
public class SecurityFilter implements Filter {
    private static final EnumMap<CommandEnum, List<User.Role>> commandPermitions = new EnumMap<>(CommandEnum.class);

    @Override
    public void init(FilterConfig filterConfig) {
        List<User.Role> adminAndClient = List.of(User.Role.ADMINISTRATOR, User.Role.CLIENT);
        List<User.Role> admin = List.of(User.Role.ADMINISTRATOR);
        List<User.Role> client = List.of(User.Role.CLIENT);

        commandPermitions.put(CommandEnum.SHOW_CAR_LIST, admin);
        commandPermitions.put(CommandEnum.SHOW_ORDER_LIST, admin);
        commandPermitions.put(CommandEnum.SHOW_USER_LIST, admin);
        commandPermitions.put(CommandEnum.CHANGE_ROLE, admin);
        commandPermitions.put(CommandEnum.CHANGE_ORDER_STATE, admin);

        commandPermitions.put(CommandEnum.FORWARD, adminAndClient);
        commandPermitions.put(CommandEnum.MAKE_ORDER, adminAndClient);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(AttributeName.USER);
        String userRole = user.getRole().toString();
        String command = request.getParameter(ParameterName.COMMAND);
        List<User.Role> allowedRoles = null;
        if (command != null) {
            allowedRoles = commandPermitions.get(CommandEnum.valueOf(command.toUpperCase()));
        }
        if (userRole == null && allowedRoles != null) {
            response.sendRedirect(PageName.INDEX_PAGE);
        } else {
            if (allowedRoles == null || allowedRoles.contains(User.Role.valueOf(userRole.toString().toUpperCase()))) {
                chain.doFilter(request, response);
            } else {
                request.getRequestDispatcher(PageName.ERROR_500_PAGE).forward(request, response);
            }
        }
    }

    @Override
    public void destroy() {
    }
}
