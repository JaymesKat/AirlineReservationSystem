package edu.miu.ars.service.email.receiver;

import edu.miu.ars.service.email.constant.JmsConstant;
import edu.miu.ars.service.email.models.Email;
import edu.miu.ars.service.email.sender.EmailSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class MessageListener {
    private static final Logger log= LoggerFactory.getLogger(MessageListener.class);
    private  final EmailSender emailSender;

    @Autowired
    public MessageListener(EmailSender emailSender) {
        this.emailSender = emailSender;
    }

    @JmsListener(destination = JmsConstant.EMAIL_QUEUE)
    public void messageListener(Email email){
        emailSender.sendEmail(email);
        log.info("Message Received");
    }

}
