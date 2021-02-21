package com.fiap.kafka.consumer.adapter.gateway.repository;

import com.fiap.kafka.consumer.adapter.constants.AlertConstants;
import com.fiap.kafka.consumer.domain.domain.WheaterAlert;
import lombok.SneakyThrows;
import org.springframework.stereotype.Repository;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;

@Repository
public class EmailRepository {

    private Session session;

    EmailRepository(){
        session = getEmailSession();
    }

    @SneakyThrows
    public void sendEmail(Message message){

        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        mimeBodyPart.setContent(message, "text/json");

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(mimeBodyPart);

        message.setContent(multipart);

        Transport.send(message);

    }

    public Session getEmailSession() {

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        return Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("ealert661@gmail.com", "*20022021");
            }
        });

    }

    @SneakyThrows
    public void sendEmail(String destiny, String contentType, String content) {

        Message message = new MimeMessage(session);

        message.setFrom(new InternetAddress(AlertConstants.ALERT_MAIL_ADDRESS));
        message.setRecipients(
                Message.RecipientType.TO, InternetAddress.parse(destiny));
        message.setSubject(AlertConstants.ALERT_EMAIL_SUBJECT);

        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        mimeBodyPart.setContent(content, contentType);

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(mimeBodyPart);

        message.setContent(multipart);

        Transport.send(message);

    }
}
