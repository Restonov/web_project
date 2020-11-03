package by.restonov.jspservlet.controller.command;

import by.restonov.jspservlet.controller.command.impl.*;
import by.restonov.jspservlet.controller.command.impl.admin.ChangeRoleCommand;
import by.restonov.jspservlet.controller.command.impl.admin.ShowCarListCommand;
import by.restonov.jspservlet.controller.command.impl.admin.ShowUserListCommand;

public enum CommandEnum {
    LOGIN (new LoginCommand()),
    LOGOUT (new LogoutCommand()),
    CHANGE_LANGUAGE (new ChangeLanguageCommand()),
    FORWARD(new ForwardCommand()),
    REGISTER (new RegisterCommand()),
    SHOW_CAR_INFO (new ShowCarInfoCommand()),
    SHOW_USER_LIST (new ShowUserListCommand()),
    SHOW_CAR_LIST (new ShowCarListCommand()),
    CHANGE_ROLE (new ChangeRoleCommand()),
    MAKE_ORDER (new MakeOrderCommand());

    private final ActionCommand command;

    CommandEnum(ActionCommand command) {
        this.command = command;
    }

    public ActionCommand getCurrentCommand() {
        return command;
    }
}
