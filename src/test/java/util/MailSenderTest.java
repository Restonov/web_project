package util;

import by.restonov.tyrent.util.MailSender;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.mail.MessagingException;

public class MailSenderTest extends Assert {
    MailSender sender;

    @BeforeClass
    public void setUp() {
        sender = new MailSender();

    }

    @AfterClass
    public void tierDown() {
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
