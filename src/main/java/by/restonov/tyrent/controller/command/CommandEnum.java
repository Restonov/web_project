package by.restonov.tyrent.controller.command;

import by.restonov.tyrent.controller.command.impl.*;
import by.restonov.tyrent.controller.command.impl.admin.ChangeRoleCommand;
import by.restonov.tyrent.controller.command.impl.admin.ShowCarListCommand;
import by.restonov.tyrent.controller.command.impl.admin.ShowOrderListCommand;
import by.restonov.tyrent.controller.command.impl.admin.ShowUserListCommand;

public enum CommandEnum {
    //TODO add GET or POST to names
    LOGIN (new LoginCommand()),
    LOGOUT (new LogoutCommand()),
    CHANGE_LANGUAGE (new ChangeLanguageCommand()),
    FORWARD(new ForwardCommand()),
    REGISTER (new RegisterCommand()),
    SHOW_CAR_INFO (new ShowCarInfoCommand()),
    SHOW_USER_LIST (new ShowUserListCommand()),
    SHOW_CAR_LIST (new ShowCarListCommand()),
    //TODO lambda where simple command
    SHOW_ORDER_LIST (new ShowOrderListCommand()),
    CHANGE_ROLE (new ChangeRoleCommand()),
    MAKE_ORDER (new MakeOrderCommand());
    //TODO change car and order state from admin panel
    

    private final ActionCommand command;

    CommandEnum(ActionCommand command) {
        this.command = command;
    }

    public ActionCommand getCurrentCommand() {
        return command;
    }
}
