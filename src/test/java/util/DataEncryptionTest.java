package util;

import by.restonov.tyrent.util.DataEncryptor;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class DataEncryptionTest extends Assert {
    DataEncryptor encryption;

    @BeforeClass
    public void setUp() {
        encryption = new DataEncryptor();
    }

    @AfterClass
    public void tearDown() {
        encryption = null;
    }

    @Test
    public void testEncrypt() {
        String actual = encryption.encrypt("Restonov".toCharArray());
        String expected = "some salted hash";
        AssertJUnit.assertNotSame(expected, actual);
    }

    @Test
    public void testDecrypt() {
        boolean actual = encryption.decrypt("123456".toCharArray(), "$31$16$VRYgLx4EdEFvcAlo6duK0JA8Th9pkjS4a5TxIiBj2fY");
        AssertJUnit.assertTrue(actual);
    }
}
