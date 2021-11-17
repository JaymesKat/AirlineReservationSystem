package edu.miu.ars.service.email.sender;

import edu.miu.ars.service.email.models.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.concurrent.CompletableFuture;

@Component
public class EmailSender {

    private final JavaMailSender mailSender;

    @Autowired
    EmailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendEmail(Email email) {
        MimeMessage mimeMailMessage = buildMimeMessage(email.getTo(), email.getSubject(), email.getBody());
        sendEmailAsynchronously(mimeMailMessage);
    }

    private MimeMessage buildMimeMessage(String userEmail, String subject, String bodyMessage) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            mimeMessage.setText(bodyMessage);
            mimeMessage.setRecipients(MimeMessage.RecipientType.TO, InternetAddress.parse(userEmail));
            mimeMessage.setSubject(subject);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return mimeMessage;
    }

    private void sendEmailAsynchronously(MimeMessage mimeMessage) {
        CompletableFuture.runAsync(() -> {
            try {
                mailSender.send(mimeMessage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
