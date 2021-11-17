package edu.miu.ars.service.impl;

import edu.miu.ars.domain.*;
import edu.miu.ars.repository.ReservationRepository;
import edu.miu.ars.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;


@Service
@EnableScheduling
public class NotificationService {

    @Autowired
    JavaMailSender javaMailSender;

    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private ReservationRepository reservationRepository;

    public void sendSaveEmail(Reservation reservation) {
        try {
            String text = toEmailTextSave(reservation);
            jmsTemplate.convertAndSend("mailbox", text);
        }catch (Exception e) {
            System.out.println("JMS exception"+e);
        }
    }

    public void sendPayEmail(Reservation reservation) {
        try {
            String text = toEmailTextPay(reservation);
            jmsTemplate.convertAndSend("mailbox", text);
        }catch (Exception e) {
            System.out.println("JMS exception");
        }
    }

    public String sendMail(String email, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("passenger1@gmail.com");
        message.setTo(email);
        message.setSubject(subject);
        message.setText(text);

        javaMailSender.send(message);
        return "email successful";
    }


    public String toEmailTextPay(Reservation reservation) {
        Passenger passenger = reservation.getPassenger();
        String subject = "Sending Tickets for your Confirmed Flights -- Reservation Code is==>"+ reservation.getCode();
        String text = "Dear "+passenger.getFirstName()+"\n"+"Your Flight are :";
        for(FlightInfo f:reservation.getFlightInfos()) {
            text+="Flight from "+f.getFlight().getOrigin().getAddress().getCity()+" to "
                    +f.getFlight().getDestination().getAddress().getCity()+
                    " on date "+f.getFlight().getDepartureTime()+"\n";
        }
        List<Ticket> tickets = reservation.getFlightInfos().stream().map(FlightInfo::getTicket).distinct().collect(Collectors.toList());
        for(Ticket t:tickets) {
            text+="\n\nTicket Number==> "+t.getNumber() +"   Reservation code==>"+ reservation.getCode()+"\n";
        }

        return reservation.getPassenger().getEmail()+"#"+subject+"#"+text;
    }

    public String toEmailTextSave(Reservation reservation) {
        Passenger passenger = reservation.getPassenger();
        String subject = "Saving your Reserved Flights==>Reservation Code is"+ reservation.getCode();
        String text = "Dear "+passenger.getFirstName()+"\n"+"Your Flight are :";
        for(FlightInfo f:reservation.getFlightInfos()) {
            text+="Flight from "+f.getFlight().getOrigin().getAddress().getCity()+" to "
                    +f.getFlight().getDestination().getAddress().getCity()+
                    " on date "+f.getFlight().getDepartureTime()+"\n";
        }
        return reservation.getPassenger().getEmail()+"#"+subject+"#"+text;
    }

    public void sendFlightTimeReminder() {
        List<Reservation> notnotifiedReservations = reservationRepository.getNotNotifiedConfirmedReservations(ReservationState.CONFIRMED, FlightNotification.NOTSENT);
        if(notnotifiedReservations!=null) {
            for (Reservation reservation: notnotifiedReservations) {
                    reservation.setFlightNotification(FlightNotification.SENT);
                    Passenger passenger = reservation.getPassenger();
                    String subject = "Reminding your confirmed Flights";
                    String text = "Dear "+passenger.getFirstName()+",\n"+"Your Flight are :\n";
                    for(FlightInfo f:reservation.getFlightInfos()) {
                        text += "Flight from " + f.getFlight().getOrigin().getAddress().getCity() + " to "
                                + f.getFlight().getDestination().getAddress().getCity() +
                                " on date " + f.getFlight().getDepartureTime() + "\n";
                    }
                    List<Ticket> tickets = reservation.getFlightInfos().stream().map(FlightInfo::getTicket).distinct().collect(Collectors.toList());
                    for(Ticket t:tickets) {
                        text+="\n\nTicket Number==> "+t.getNumber() +"   Reservation code==>"+ reservation.getCode()+"\n";
                    }
                    sendMail(reservation.getPassenger().getEmail(),subject, text);
                    reservationRepository.save(reservation);
            }
        }

    }

    @Scheduled(fixedDelay = 1000*30, initialDelay = 3000)
    public void startReminderJMS() {
        System.out.println("====================checking if there is a reminder to send=================================");
        try {
            jmsTemplate.convertAndSend("reminder", "message");
        }catch (Exception e) {
            System.out.println("JMS exception for the reminder------"+e);
        }
    }

}