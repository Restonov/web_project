package by.restonov.tyrent.manager;

/**
 * Names of application parameters
 */
public class ParameterName {

    /**
     * Page content parameters
     */
    public static final String PATH = "path";
    public static final String COMMAND = "command";
    public static final String PAGE_LANGUAGE = "page_language";
    public static final String LOGIN_PAGE ="login_page";
    public static final String REGISTER_PAGE ="register_page";
    public static final String USER_PROFILE_PAGE = "user_profile_page";

    /**
     * User parameters
     */
    public static final String USER_ID = "user_id";
    public static final String USER_FIRST_NAME = "user_first_name";
    public static final String USER_LAST_NAME = "user_last_name";
    public static final String USER_LOGIN = "user_login";
    public static final String USER_PASSWORD = "user_password";
    public static final String USER_EMAIL = "user_email";
    public static final String USER_PHONE = "user_phone";
    public static final String USER_STATE = "user_state";
    public static final String USER_ROLE = "user_role";
    public static final String USER_DATA = "data";

    /**
     * Car parameters
     */
    public static final String CAR_ID = "car_id";
    public static final String CAR_STATE = "car_state";
    public static final String CAR_NAME = "car_name";
    public static final String CAR_COST = "car_cost";
    public static final String DESCRIPTION_ENG = "car_description_eng";
    public static final String DESCRIPTION_RUS = "car_description_rus";
    public static final String IMAGE_BIG = "car_image_big";
    public static final String IMAGE_MINI = "car_image_mini";
    public static final String IMAGE_CABIN = "car_image_cabin";
    public static final String CHOSEN_CAR_ID = "chosen_car_id";

    /**
     * User order parameters
     */
    public static final String ORDER_ID = "order_id";
    public static final String ORDER_STATE = "order_state";
    public static final String ORDER_TIMESTAMP = "order_timestamp";

    /**
     * Email parameters
     */
    public static final String SEND_TO_EMAIL = "maxplyushko@gmail.com";
    public static final String MAIL_SUBJECT = "TyRent user verification";
    public static final String MAIL_TRANSPORT_PROTOCOL = "mail.transport.protocol";
    public static final String MAIL_HOST = "mail.host";
    public static final String MAIL_QUIT_WAIT = "mail.smtp.quitwait";
    public static final String MAIL_PORT = "mail.smtp.port";
    public static final String MAIL_AUTH = "mail.smtp.auth";
    public static final String MAIL_SOCKET_CLASS = "mail.smtp.socketFactory.class";
    public static final String MAIL_SOCKET_FALLBACK = "mail.smtp.socketFactory.fallback";

    /**
     * Incident parameters
     */
    public static final String INCIDENT_ID = "incident_id";
    public static final String INCIDENT_DESCRIPTION = "incident_description";
    public static final String INCIDENT_TIMESTAMP = "incident_timestamp";
    public static final String INCIDENT_TYPE_ID = "incident_type_id";
    public static final String INCIDENT_TYPE_INFO = "incident_type_info";

    /**
     * Private constructor to prevent class object creation
     */
    private ParameterName() {
    }
}
