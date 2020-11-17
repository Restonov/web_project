package by.restonov.tyrent.controller.command;

import by.restonov.tyrent.controller.command.impl.*;
import by.restonov.tyrent.controller.filter.SecurityFilter;
import by.restonov.tyrent.manager.PageName;
import by.restonov.tyrent.manager.PagePathManager;
import by.restonov.tyrent.manager.ParameterName;

/**
 * All available commands of the application
 * @see SecurityFilter
 *
 */
public enum CommandType {
    LOGIN (new LoginCommand()),
    LOGOUT (new LogoutCommand()),
    CHANGE_LANGUAGE (new ChangeLanguageCommand()),
    FORWARD (request -> PagePathManager.definePage(request.getParameter(ParameterName.PATH))),
    REGISTER (new RegisterCommand()),
    SHOW_CAR_INFO (new ShowCarInfoCommand()),
    MAKE_ORDER (new MakeOrderCommand()),
    ACTIVATE_ACCOUNT (new ActivateAccountCommand()),
    SHOW_USER_PROFILE (request -> PageName.USER_PROFILE_PAGE),
    FREEZE_ACCOUNT (new FreezeAccountAdminCommand()),

    SHOW_USER_LIST (request -> PageName.USER_LIST_PAGE),
    SHOW_CAR_LIST (request -> PageName.CAR_LIST_PAGE),
    SHOW_ORDER_LIST (request -> PageName.ORDER_LIST_PAGE),
    SHOW_INCIDENT_LIST (request -> PageName.INCIDENT_LIST_PAGE),
    CHANGE_ORDER_STATE (new ChangeOrderStateAdminCommand()),
    CHANGE_USER_STATE (new ChangeUserStateAdminCommand()),
    SHOW_CLIENT_PROFILE (new ShowClientProfileAdminCommand()),
    REPORT_INCIDENT (new ReportIncidentAdminCommand()),
    DELETE_INCIDENT (new DeleteIncidentAdminCommand());

    /**
     * Current command
     *
     */
    private final ActionCommand command;

    /**
     * Package access constructor
     *
     * @param command {@link ActionCommand}
     */
    CommandType(ActionCommand command) {
        this.command = command;
    }

    /**
     * Gets current command
     *
     * @return current command
     */
    public ActionCommand getCurrentCommand() {
        return command;
    }
}
