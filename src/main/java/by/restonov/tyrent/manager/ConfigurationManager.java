package by.restonov.tyrent.manager;

import java.util.ResourceBundle;

/**
 * Manager, that helps with ConnectionCreator
 * and MailSessionFactory configuration
 */
public class ConfigurationManager {
    private static final ResourceBundle resourceBundle = ResourceBundle.getBundle("config");

    /**
     * Private constructor to prevent class object creation
     */
    private ConfigurationManager() {}

    /**
     * Find config by it's key
     *
     * @param key - key to appropriate property
     * @return result string
     */
    public static String getProperty (String key) {
        return resourceBundle.getString(key);
    }
}
