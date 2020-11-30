package util;

import by.restonov.tyrent.util.MailSender;
import org.testng.Assert;
import org.testng.annotations.*;

import javax.mail.MessagingException;

public class MailSenderTest extends Assert {
    MailSender sender;

    @BeforeMethod
    public void setUp() {
        sender = new MailSender();
    }

    @AfterMethod
    public void tearDown() {
        sender = null;
    }

    @Test
    public void sendTest() {
        try {
            sender.send("email@test.by", "saltedLogin");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
