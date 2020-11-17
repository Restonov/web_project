package util;

import by.restonov.tyrent.util.DataValidator;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

public class DataValidatorTest extends Assert {

    @Test
    public void validateLatinTextTest() {
        boolean result = DataValidator.validateLatinText("BorisIvanov");
        AssertJUnit.assertTrue(result);
    }

    @Test
    public void validatePasswordTest() {
        boolean result = DataValidator.validatePassword("123456pass");
        AssertJUnit.assertTrue(result);
    }

    @Test
    public void validatePhoneTest() {
        boolean result = DataValidator.validatePhone("375256200333");
        AssertJUnit.assertTrue(result);
    }

    @Test
    public void validateEmailTest() {
        boolean result = DataValidator.validateEmail("test@email.ru");
        AssertJUnit.assertTrue(result);
    }
}
