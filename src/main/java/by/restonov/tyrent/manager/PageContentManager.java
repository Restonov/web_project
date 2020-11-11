package by.restonov.tyrent.manager;

import java.util.ResourceBundle;

public class PageContentManager {
    private static final ResourceBundle resourceBundle = ResourceBundle.getBundle("pagecontent");
    private static final ResourceBundle resourceBundleRu = ResourceBundle.getBundle("pagecontent_ru");

    private PageContentManager() {}

    public static String findProperty (String key, String lang) {
        String prop = resourceBundle.getString(key);
        if (lang != null && lang.equals(AttributeName.RU)) {
                prop = resourceBundleRu.getString(key);
            }
        return prop;
    }
}
