package ua.alekseytsev.LibraryApp.web.util;


import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Provides work with GMail mail service
 */
public final class MailService {
    public static final String MSG_REGISTRATION = "Thanks for registration in Library:Online booking";
    public static final String MSG_FINE = "You have got a fines";
    private static final Logger LOG = LogManager.getLogger(MailService.class);
    private static final String USERNAME = "epamTrainee@gmail.com";
    private static final String PASSWORD = "epam2015";

    private MailService() {
    }

    public static void sendMail(final String mail, final String subject, final String message) {
        new Thread() {
            @Override
            public void run() {
                try {
                    LOG.debug("Sending mail to ===>" + mail);
                    Message msg = new MimeMessage(getSession());
                    msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mail));
                    msg.setSubject(subject);
                    msg.setText(message);
                    Transport.send(msg);
                    LOG.debug("Success");
                } catch (MessagingException e) {
                    LOG.error("Cannot send notification");
                }
            }
        }.start();
    }

    private static Session getSession() {
        Session session = Session.getDefaultInstance(getProperties(),
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(USERNAME, PASSWORD);
                    }
                });
        return session;
    }

    private static Properties getProperties() {
        Properties properties = new Properties();
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.socketFactory.port", "587");
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.port", "587");
        return properties;
    }
}