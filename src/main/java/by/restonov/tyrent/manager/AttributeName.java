package by.restonov.tyrent.manager;

/**
 * Names of application attributes
 */
public class AttributeName {

    /**
     * Common attributes
     */
    public static final String USER = "user";
    public static final String LOCALE = "locale";
    public static final String RU = "ru";
    public static final String EN = "en";

    /**
     * Administrator attributes
     */
    public static final String ACTIVATE_ADMIN_PANEL = "activate_admin_panel";

    /**
     * Car attributes
     */
    public static final String CAR = "car";
    public static final String CHOSEN_CAR = "chosen_car";
    public static final String CAR_DESCRIPTION = "car_description";

    /**
     * Error pages and alerts attributes
     */
    public static final String LOGIN_PASSWORD_ERROR = "login_pass_message_error";
    public static final  String USER_EXISTS_ERROR = "user_already_exists";
    public static final  String DATA_VALIDATION_ERROR = "data_validation_error";
    public static final  String REGISTRATION_ERROR = "registration_error";
    public static final  String WELCOME_MESSAGE = "welcome_message";
    public static final  String CAR_NOT_AVAILABLE_ALERT = "car_not_available_alert";
    public static final  String SUCCESSFUL_ORDER_MESSAGE = "successful_order";
    public static final  String USER_ACTIVATED_MESSAGE = "user_activated";
    public static final  String ERROR_404 = "error_404";

    /**
     * Private constructor to prevent class object creation
     */
    private AttributeName() {
    }
}
