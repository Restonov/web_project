package by.restonov.tyrent.manager;

public class PagePathManager {

    public PagePathManager() {
    }

    public static String definePage(String pageName) {
        return switch (pageName) {
            case ParameterName.LOGIN_PAGE -> PageName.LOGIN_PAGE;
            case ParameterName.REGISTER_PAGE -> PageName.REGISTER_PAGE;
            case ParameterName.USER_PROFILE_PAGE -> PageName.USER_PROFILE_PAGE;
            default -> PageName.INDEX_PAGE;
        };
    }
}
