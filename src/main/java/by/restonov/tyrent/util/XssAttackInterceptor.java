package by.restonov.tyrent.util;

public class XssAttackInterceptor {
    private static final String SCRIPT_TAG = "<[/]?script[\\s\\S]*?>";

    private XssAttackInterceptor() {
    }

    public static String checkTextAndGet(String text) {
        return text.replaceAll(SCRIPT_TAG, "");
    }
}
