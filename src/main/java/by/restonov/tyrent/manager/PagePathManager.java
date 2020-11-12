package by.restonov.tyrent.manager;

/**
 * Manager, that helps with defining page paths
 */
public class PagePathManager {

    /**
     * Private constructor to prevent class object creation
     */
    private PagePathManager() {
    }

    /**
     * Define page by it's name
     *
     * @param pageName name of the Page
     * @return Page path
     */
    public static String definePage(String pageName) {
        return switch (pageName) {
            case ParameterName.LOGIN_PAGE -> PageName.LOGIN_PAGE;
            case ParameterName.REGISTER_PAGE -> PageName.REGISTER_PAGE;
            case ParameterName.USER_PROFILE_PAGE -> PageName.USER_PROFILE_PAGE;
            default -> PageName.INDEX_PAGE;
        };
    }
}
