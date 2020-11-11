package by.restonov.tyrent.util;

import by.restonov.tyrent.manager.ParameterName;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataValidator {
    private static final String LATIN_TEXT_PATTERN = "^[a-zA-Z]+$";
    private static final String PASSWORD_PATTERN = "^[A-Za-z0-9!@#$%_&\\-*()<>]{6,12}$";
    private static final String EMAIL_PATTERN = "^[\\w\\d.]+@[a-zA-Z.]+\\.[A-Za-z]{1,4}$";
    private static final String PHONE_PATTERN = "^[\\d]{12}$";

    private DataValidator() {}

    public static boolean validateData(Map<String, String> userData) {
        return validateLatinText(userData.get(ParameterName.USER_FIRST_NAME)) &&
                validateLatinText(userData.get(ParameterName.USER_LAST_NAME)) &&
                validateEmail(userData.get(ParameterName.USER_EMAIL)) &&
                validatePhone(userData.get(ParameterName.USER_PHONE)) &&
                validateLatinText(userData.get(ParameterName.USER_LOGIN));
    }

    public static boolean validateLatinText(String text) {
        Pattern pattern = Pattern.compile(LATIN_TEXT_PATTERN);
        Matcher matcher = pattern.matcher(text);
        return matcher.find();
    }

    public static boolean validatePassword(String password) {
        Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
        Matcher matcher = pattern.matcher(password);
        return matcher.find();
    }

    public static boolean validateEmail(String email) {
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.find();
    }

    public static boolean validatePhone(String phone) {
        Pattern pattern = Pattern.compile(PHONE_PATTERN);
        Matcher matcher = pattern.matcher(phone);
        return matcher.find();
    }
}
