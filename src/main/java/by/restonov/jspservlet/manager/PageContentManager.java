package by.restonov.jspservlet.manager;

import java.util.ResourceBundle;

public class PageContentManager {
    private static final ResourceBundle resourceBundle = ResourceBundle.getBundle("pagecontent");
    private static final ResourceBundle resourceBundleRu = ResourceBundle.getBundle("pagecontent_ru");

    private PageContentManager() {}
    public static String findProperty (String key, String lang) {
        String resultString = resourceBundle.getString(key);
        if (lang.equals("ru")) {
            resultString = resourceBundleRu.getString(key);
        }
        return resultString;
    }
}
