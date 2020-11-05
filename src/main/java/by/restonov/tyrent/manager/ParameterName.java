package by.restonov.tyrent.manager;

public class ParameterName {
    //page content
    public static final String PATH = "path";
    public static final String COMMAND = "command";
    public static final String PAGE_LANGUAGE = "page_language";

    //common
    public static final String STATE = "state";

    //user
    public static final String USER_ID = "user_id";
    public static final String FIRST_NAME = "first_name";
    public static final String LAST_NAME = "last_name";
    public static final String LOGIN = "login";
    public static final String PASSWORD = "password";
    public static final String EMAIL = "email";
    public static final String ROLE_ID = "role_id";
    public static final String ORDER_ID_LIST = "order_id_list";

    //car
    //TODO name -> car_name in DB and etc.
    public static final String CAR_ID = "car_id";
    public static final String CAR_STATE = "car_state";
    public static final String NAME = "name";
    public static final String COST = "cost";
    public static final String TYPE = "type";
    public static final String DESCRIPTION_ENG = "description_eng";
    public static final String DESCRIPTION_RUS = "description_rus";
    public static final String IMAGE_BACKGROUND = "image_background";
    public static final String IMAGE_MINI = "image_mini";
    public static final String IMAGE_CABIN = "image_cabin";
    public static final String CHOSEN_CAR_ID = "chosen_car_id";


    //order
    public static final String ORDER_ID = "order_id";
    public static final String ORDER_STATE = "order_state";
    public static final String ORDER_TIMESTAMP = "order_timestamp";

    //pages
    public static final String LOGIN_PAGE ="login_page";
    public static final String USER_PROFILE_PAGE = "user_profile";

    //email
    public static final String SEND_TO_EMAIL = "maxplyushko@gmail.com";
    public static final String MAIL_SUBJECT = "TyRent user verification";
    public static final String MAIL_BODY = "To complete registration please tap to the link below";

    private ParameterName() {
    }
}
