package by.restonov.tyrent.util;

import by.restonov.tyrent.manager.ParameterName;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailSender {
    private static final String BODY_HEADLINE = "Please push the link to activate your account";
    private static final String BODY_START = "http://localhost:8080/tyrent_war_exploded/controller?user_email=";
    private static final String BODY_DATA = "&data=";
    private static final String BODY_COMMAND = "&command=activate_account";
    private static final String CONTENT = "text/html";
    private MimeMessage message;
    private String sendToEmail = ParameterName.SEND_TO_EMAIL;
    private String mailSubject = ParameterName.MAIL_SUBJECT;
    private String mailBody;

    public void send(String userEmail, String encryptedLogin) throws MessagingException {
        makeBody(userEmail, encryptedLogin);
        initMessage();
        Transport.send(message);
    }

    private void makeBody(String userEmail, String encryptedLogin) {
        StringBuilder body = new StringBuilder(BODY_HEADLINE);
        body.append("\n").append(BODY_START).append(userEmail).append(BODY_DATA).append(encryptedLogin).append(BODY_COMMAND);
        this.mailBody = body.toString();
    }

    private void initMessage() throws MessagingException {
        MailSessionFactory factory = new MailSessionFactory();
        Session mailSession = factory.createSession();
        mailSession.setDebug(true);
        message = new MimeMessage(mailSession);
            message.setSubject(mailSubject);
            message.setContent(mailBody, CONTENT);
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(sendToEmail));
    }
}
