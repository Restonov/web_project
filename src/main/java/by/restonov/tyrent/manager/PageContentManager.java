package by.restonov.tyrent.manager;

import java.util.ResourceBundle;

/**
 * Manager, that helps with tags localization
 */
public class PageContentManager {
    private static final ResourceBundle resourceBundle = ResourceBundle.getBundle("pagecontent");
    private static final ResourceBundle resourceBundleRu = ResourceBundle.getBundle("pagecontent_ru");

    /**
     * Private constructor to prevent class object creation
     */
    private PageContentManager() {}

    /**
     * Find text by it's key
     * and app lang
     *
     * @param key - key to appropriate property
     * @param lang - language of the application
     * @return result string
     */
    public static String findProperty (String key, String lang) {
        String prop = resourceBundle.getString(key);
        if (lang != null && lang.equals(AttributeName.RU)) {
                prop = resourceBundleRu.getString(key);
            }
        return prop;
    }
}
