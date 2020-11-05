package by.restonov.tyrent.util;

import by.restonov.tyrent.manager.ParameterName;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailSender {
    private MimeMessage message;
    private String sendToEmail = ParameterName.SEND_TO_EMAIL;
    private String mailSubject = ParameterName.MAIL_SUBJECT;
    private String mailText = ParameterName.MAIL_BODY;

    public void send() {
        try {
            initMessage();
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    private void initMessage() throws MessagingException {
        MailSessionFactory factory = new MailSessionFactory();
        Session mailSession = factory.createSession();
        mailSession.setDebug(true);
        message = new MimeMessage(mailSession);
            message.setSubject(mailSubject);
            message.setContent(mailText, "text/html");
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(sendToEmail));
    }
}
