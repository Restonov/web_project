package by.restonov.tyrent.manager;

/**
 * Names of application pages
 */
public class PageName {

    /**
     * Common pages
     */
    public static final String INDEX_PAGE = "/index.jsp";
    public static final String LOGIN_PAGE = "/WEB-INF/jsp/login.jsp";
    public static final String REGISTER_PAGE = "/WEB-INF/jsp/register.jsp";
    public static final String USER_PROFILE_PAGE = "/WEB-INF/jsp/user_profile.jsp";
    public static final String MAIN_PAGE = "/WEB-INF/jsp/main.jsp";
    public static final String CAR_INFO_PAGE = "/WEB-INF/jsp/car_info.jsp";
    public static final String ERROR_500_PAGE = "/WEB-INF/jsp/error/error500.jsp";
    public static final String ERROR_404_PAGE = "/WEB-INF/jsp/error/error404.jsp";
    public static final String ERROR_404_PATH = "/404";
    public static final String ERROR_RUNTIME_PAGE = "/WEB-INF/jsp/error/error_runtime.jsp";

    /**
     * Administrator pages
     */
    public static final String USER_LIST_PAGE = "/WEB-INF/jsp/admin/user_list.jsp";
    public static final String CAR_LIST_PAGE = "/WEB-INF/jsp/admin/car_list.jsp";
    public static final String ORDER_LIST_PAGE = "/WEB-INF/jsp/admin/order_list.jsp";

    /**
     * Private constructor to prevent class object creation
     */
    private PageName() {
    }
}
