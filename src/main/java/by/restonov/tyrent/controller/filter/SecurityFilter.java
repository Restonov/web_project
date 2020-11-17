package by.restonov.tyrent.controller.filter;

import by.restonov.tyrent.controller.command.CommandType;
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
import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;

/**
 * Filter that checks
 * if performed command corresponds to
 * User role
 *
 */
@WebFilter
public class SecurityFilter implements Filter {
    private static final EnumMap<CommandType, List<User.Role>> commandPermissions = new EnumMap<>(CommandType.class);

    @Override
    public void init(FilterConfig filterConfig) {
        List<User.Role> adminClientAndGuest = List.of(User.Role.ADMINISTRATOR, User.Role.CLIENT, User.Role.GUEST);
        List<User.Role> adminAndClient = List.of(User.Role.ADMINISTRATOR, User.Role.CLIENT);
        List<User.Role> admin = List.of(User.Role.ADMINISTRATOR);
        List<User.Role> client = List.of(User.Role.CLIENT);
        List<User.Role> guest = List.of(User.Role.GUEST);

        commandPermissions.put(CommandType.ACTIVATE_ACCOUNT, adminClientAndGuest);
        commandPermissions.put(CommandType.SHOW_CAR_INFO, adminClientAndGuest);
        commandPermissions.put(CommandType.FORWARD, adminClientAndGuest);
        commandPermissions.put(CommandType.CHANGE_LANGUAGE, adminClientAndGuest);

        commandPermissions.put(CommandType.MAKE_ORDER, adminAndClient);
        commandPermissions.put(CommandType.LOGOUT, adminAndClient);
        commandPermissions.put(CommandType.SHOW_USER_PROFILE, adminAndClient);

        commandPermissions.put(CommandType.SHOW_CAR_LIST, admin);
        commandPermissions.put(CommandType.SHOW_ORDER_LIST, admin);
        commandPermissions.put(CommandType.SHOW_USER_LIST, admin);
        commandPermissions.put(CommandType.SHOW_INCIDENT_LIST, admin);
        commandPermissions.put(CommandType.SHOW_CLIENT_PROFILE, admin);
        commandPermissions.put(CommandType.CHANGE_ORDER_STATE, admin);
        commandPermissions.put(CommandType.CHANGE_USER_STATE, admin);
        commandPermissions.put(CommandType.REPORT_INCIDENT, admin);
        commandPermissions.put(CommandType.DELETE_INCIDENT, admin);

        commandPermissions.put(CommandType.FREEZE_ACCOUNT, client);

        commandPermissions.put(CommandType.LOGIN, guest);
        commandPermissions.put(CommandType.REGISTER, guest);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(AttributeName.USER);
        User.Role userRole;
        String command = request.getParameter(ParameterName.COMMAND);

        List<User.Role> allowedRoles;
        if (command != null && Arrays.stream(CommandType.values()).anyMatch(commandType -> command.equalsIgnoreCase(commandType.name()))) {
            allowedRoles = commandPermissions.get(CommandType.valueOf(command.toUpperCase()));
            userRole = (user != null) ? user.getRole() : User.Role.GUEST;
            if (allowedRoles.stream().anyMatch(userRole::equals)) {
                chain.doFilter(request, response);
            } else {
                response.sendRedirect(request.getContextPath() + PageName.ERROR_ACCESS_DENIED_PAGE);
            }
        } else {
            chain.doFilter(request, response);
        }
    }
}