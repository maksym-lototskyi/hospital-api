package org.hospital_api.notification_service.service;
import org.hospital_api.notification_service.model.RecipientDetails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service
public class EmailSender implements MessageSender {
    private final JavaMailSender mailSender;

    private String serverEmail;

    public EmailSender(JavaMailSender mailSender, @Value("${spring.mail.username}")String serverEmail) {
        this.mailSender = mailSender;
        this.serverEmail = serverEmail;
    }

    @Override
    public void sendMessage(String subject, RecipientDetails recipientDetails, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(recipientDetails.getContactInformation());
        message.setSubject(subject);
        message.setText(content);
        message.setFrom(serverEmail);

        mailSender.send(message);
        System.out.println("Message sent to "  + message.getTo()[0]);
    }
}
