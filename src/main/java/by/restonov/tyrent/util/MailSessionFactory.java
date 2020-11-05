package by.restonov.tyrent.util;

import by.restonov.tyrent.manager.ConfigurationManager;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.util.Properties;

class MailSessionFactory {
    private String userName = ConfigurationManager.getProperty("mail.user.name");
    private String userPassword = ConfigurationManager.getProperty("mail.user.password");
    private String smtpPort = ConfigurationManager.getProperty("mail.smtp.port");
    private String smtp = ConfigurationManager.getProperty("mail.transport.protocol");
    private String host = ConfigurationManager.getProperty("mail.host");
    private String auth = ConfigurationManager.getProperty("mail.smtp.auth");
    private String socketFactory = ConfigurationManager.getProperty("mail.smtp.socketFactory.class");
    private String socketFactoryFallback = ConfigurationManager.getProperty("mail.smtp.socketFactory.fallback");
    private String quitWait = ConfigurationManager.getProperty("mail.smtp.quitwait");
    private Properties properties;

    public MailSessionFactory() {
        properties = new Properties();
        properties.setProperty("mail.transport.protocol", smtp);
        properties.setProperty("mail.host", host);
        properties.setProperty("mail.smtp.quitwait", quitWait);
        properties.put("mail.smtp.port", smtpPort);
        properties.put("mail.smtp.auth", auth);
        properties.put("mail.smtp.socketFactory.class", socketFactory);
        properties.put("mail.smtp.socketFactory.fallback", socketFactoryFallback);
    }

    public Session createSession() {
        return Session.getDefaultInstance(properties, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(userName, userPassword);
            }
        });
    }
}
