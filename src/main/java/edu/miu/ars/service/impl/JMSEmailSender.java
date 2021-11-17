package edu.miu.ars.service.impl;

import edu.miu.ars.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Arrays;
@Component
@Transactional
public class JMSEmailSender {

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private ReservationRepository reservationRepository;

    @JmsListener(destination = "mailbox", containerFactory = "myFactory")
    public void receiveMessage(String text) throws InterruptedException {
        String [] strings = text.split("#");
        System.out.println(Arrays.toString(strings));
        notificationService.sendMail(strings[0], strings[1], strings[2]);
        System.out.println("Finished sending the email");
    }

    @JmsListener(destination = "reminder", containerFactory = "myFactory")
    public void receiveReminder(String message) throws InterruptedException {
        System.out.println("I received a reminder send alert");
        notificationService.sendFlightTimeReminder();

    }


}
