package by.restonov.tyrent.controller.command;

import by.restonov.tyrent.controller.command.impl.*;
import by.restonov.tyrent.controller.command.impl.ChangeRoleAdminCommand;
import by.restonov.tyrent.manager.PageName;
import by.restonov.tyrent.manager.PagePathManager;
import by.restonov.tyrent.manager.ParameterName;

/**
 * All available commands of the application
 */
public enum CommandEnum {
    LOGIN (new LoginCommand()),
    LOGOUT (new LogoutCommand()),
    CHANGE_LANGUAGE (new ChangeLanguageCommand()),
    FORWARD(request -> PagePathManager.definePage(request.getParameter(ParameterName.PATH))),
    REGISTER (new RegisterCommand()),
    SHOW_CAR_INFO (new ShowCarInfoCommand()),
    SHOW_USER_LIST (request -> PageName.USER_LIST_PAGE),
    SHOW_CAR_LIST (request -> PageName.CAR_LIST_PAGE),
    SHOW_ORDER_LIST (request -> PageName.ORDER_LIST_PAGE),
    CHANGE_ROLE (new ChangeRoleAdminCommand()),
    MAKE_ORDER (new MakeOrderCommand()),
    CHANGE_ORDER_STATE (new ChangeOrderStateAdminCommand()),
    ACTIVATE_CLIENT(new ActivateClientCommand());

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
    CommandEnum(ActionCommand command) {
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
