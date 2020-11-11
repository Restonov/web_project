package util;

import by.restonov.tyrent.util.DataEncryption;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class DataEncryptionTest extends Assert {
    DataEncryption encryption;

    @BeforeClass
    public void setUp() {
        encryption = new DataEncryption();

    }

    @AfterClass
    public void tierDown() {
        encryption = null;

    }

    @Test
    public void testHash() {
        String actual = encryption.encrypt("Boris".toCharArray());;
        String expected = "test";
        AssertJUnit.assertEquals(expected, actual);
    }
}
