package by.restonov.jspservlet.manager;

public class PageName {
    //common
    public static final String INDEX_PAGE = "/index.jsp";
    public static final String LOGIN_PAGE = "/WEB-INF/jsp/login.jsp";
    public static final String REGISTER_PAGE = "/WEB-INF/jsp/register.jsp";
    public static final String MAIN_PAGE = "/WEB-INF/jsp/main.jsp";
    public static final String CAR_INFO_PAGE = "/WEB-INF/jsp/car_info.jsp";

    //admin & moderator
    public static final String USER_LIST_PAGE = "/WEB-INF/jsp/admin/user_list.jsp";
    public static final String CAR_LIST_PAGE = "/WEB-INF/jsp/admin/car_list.jsp";

    //user
    public static final String USER_PROFILE_PAGE = "/WEB-INF/jsp/user_profile.jsp";

    private PageName() {
    }

    public static String findPagePath(String pageName) {
        String page;
        switch (pageName) {
            case ParameterName.LOGIN_PAGE : page = LOGIN_PAGE;
            break;
            case ParameterName.USER_PROFILE_PAGE : page = USER_PROFILE_PAGE;
            break;
            default : page = INDEX_PAGE;
        }
        return page;
    }
}
