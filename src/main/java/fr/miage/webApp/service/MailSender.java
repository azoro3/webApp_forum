package fr.miage.webApp.service;

import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
public class MailSender {

    String author;
    final String SMTP_HOST = "smtp.laposte.net";
    final String LOGIN_SMTP = "webapp.projet@laposte.net";
    final String PASSWORD_SMTP = "webappProjet2017";

    /**
     * Method to send an email
     *
     * @param receiverMail Receiver
     * @param content      Content of the email
     * @param subject      Subject of the email
     */
    public void send(String receiverMail, String content, String subject) {
        /*
         * Création de la session
         */
        Properties properties = new Properties();
        properties.setProperty("mail.transport.protocol", "smtp");
        properties.setProperty("mail.smtp.starttls.enable", "true");
        properties.setProperty("mail.from", "webapp.projet@laposte.net");
        properties.setProperty("mail.smtp.host", SMTP_HOST);
        properties.setProperty("mail.smtp.user", LOGIN_SMTP);
        properties.setProperty("mail.smtp.port", "587");
        Session session = Session.getInstance(properties);
        /*
         * Création du message
         */
        MimeMessage message = new MimeMessage(session);
        try {
            message.setText(content);
            message.setSubject(subject);
            message.addRecipients(Message.RecipientType.TO, receiverMail);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        /*
         * Envoi du message
         */
        Transport transport;
        try {
            transport = session.getTransport("smtp");
            transport.connect(LOGIN_SMTP, PASSWORD_SMTP);
            transport.sendMessage(message, new Address[]{new InternetAddress(receiverMail)});
            transport.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
