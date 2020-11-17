package util;

import by.restonov.tyrent.util.XssAttackInterceptor;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

public class XssAttackInterceptorTest extends Assert {

    @Test
    public void testCheckText() {
        String actual = XssAttackInterceptor.checkTextAndGet("<script type=\"xss\">alert</script>");
        String expected = "alert";
        AssertJUnit.assertEquals(expected, actual);
    }

}
