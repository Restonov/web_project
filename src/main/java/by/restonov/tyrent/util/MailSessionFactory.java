package by.restonov.tyrent.util;

import by.restonov.tyrent.manager.ConfigurationManager;
import by.restonov.tyrent.manager.ParameterName;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.util.Properties;

class MailSessionFactory {
    private static final String userName = ConfigurationManager.getProperty("mail.user.name");
    private static final String userPassword = ConfigurationManager.getProperty("mail.user.password");
    private static final String smtpPort = ConfigurationManager.getProperty("mail.smtp.port");
    private static final String smtp = ConfigurationManager.getProperty("mail.transport.protocol");
    private static final String host = ConfigurationManager.getProperty("mail.host");
    private static final String auth = ConfigurationManager.getProperty("mail.smtp.auth");
    private static final String socketFactory = ConfigurationManager.getProperty("mail.smtp.socketFactory.class");
    private static final String socketFactoryFallback = ConfigurationManager.getProperty("mail.smtp.socketFactory.fallback");
    private static final String quitWait = ConfigurationManager.getProperty("mail.smtp.quitwait");
    private Properties properties;

    public MailSessionFactory() {
        properties = new Properties();
        properties.setProperty(ParameterName.MAIL_TRANSPORT_PROTOCOL, smtp);
        properties.setProperty(ParameterName.MAIL_HOST, host);
        properties.setProperty(ParameterName.MAIL_QUIT_WAIT, quitWait);
        properties.put(ParameterName.MAIL_PORT, smtpPort);
        properties.put(ParameterName.MAIL_AUTH, auth);
        properties.put(ParameterName.MAIL_SOCKET_CLASS, socketFactory);
        properties.put(ParameterName.MAIL_SOCKET_FALLBACK, socketFactoryFallback);
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
