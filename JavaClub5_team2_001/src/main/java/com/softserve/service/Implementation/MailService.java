package com.softserve.service.Implementation;

import com.sun.mail.smtp.SMTPTransport;
import com.sun.mail.util.BASE64EncoderStream;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class MailService {

    public void sendEmail(String[] recipients, String subject, String msg) throws MessagingException {

        Session newSession = null;
        MimeMessage mimeMessage = null;
        // тута пошту
        String fromUser = "javaclub5team2@gmail.com";
        //тута пароль
        String fromUserPassword = "qwerty=*951";
        String emailHost = "smtp.gmail.com";
        Properties properties = System.getProperties();
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        newSession = Session.getDefaultInstance(properties, null);
        mimeMessage = new MimeMessage(newSession);

        for (String recipient : recipients) {
            mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
        }

        mimeMessage.setSubject(subject);
        mimeMessage.setText(msg);
        Transport transport = newSession.getTransport("smtp");
        transport.connect(emailHost, fromUser, fromUserPassword);
        transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
        transport.close();
        System.out.println("Email successfully sent!!!");
    }

}
