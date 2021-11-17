package edu.miu.ars.service.impl;

import edu.miu.ars.DTO.AddressDTO;
import edu.miu.ars.DTO.PassengerDTO;
import edu.miu.ars.DTO.ReservationDTO;
import edu.miu.ars.config.exceptions.BadClientException;
import edu.miu.ars.constant.ResponseConstant;
import edu.miu.ars.domain.Address;
import edu.miu.ars.domain.Agent;
import edu.miu.ars.domain.Flight;
import edu.miu.ars.domain.Passenger;
import edu.miu.ars.domain.Reservation;
import edu.miu.ars.domain.Ticket;
import edu.miu.ars.domain.User;
import edu.miu.ars.enums.ReservationStatus;
import edu.miu.ars.repository.ReservationRepository;
import edu.miu.ars.service.FlightService;
import edu.miu.ars.service.ReservationService;
import edu.miu.ars.service.email.constant.JmsConstant;
import edu.miu.ars.service.email.models.Email;
import edu.miu.ars.util.AppUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ReservationServiceImpl implements ReservationService {
    private final ReservationRepository reservationRepository;
    private final AppUtil appUtil;
    private final FlightService flightService;
    private final JmsTemplate jmsTemplate;

    @Autowired
    public ReservationServiceImpl(ReservationRepository reservationRepository, AppUtil appUtil, FlightService flightService, JmsTemplate jmsTemplate) {
        this.reservationRepository = reservationRepository;
        this.appUtil = appUtil;
        this.flightService = flightService;
        this.jmsTemplate = jmsTemplate;
    }


    @Override
    public Reservation save(Reservation reservation) {
        return reservation != null ? reservationRepository.save(reservation) : null;
    }

    @Override
    public List<Reservation> findAll() {
        return reservationRepository.findAll();
    }

    @Override
    public Reservation findById(Long id) {
        return reservationRepository.findById(id).orElse(null);
    }

    @Override
    public boolean update(Reservation reservation, Long id) {
        Reservation reservationFromDB = findById(id);
        if (reservationFromDB != null) {
            reservationFromDB.setCode(reservation.getCode());
            reservationFromDB.setStatus(reservation.getStatus());
            reservationFromDB.setAgent(reservation.getAgent());
            reservationFromDB.setPassenger(reservation.getPassenger());
            reservationFromDB.setTicketList(reservation.getTicketList());
            save(reservationFromDB);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteById(Long id) {
        Reservation reservation = findById(id);
        if (reservation != null) {
            reservationRepository.delete(reservation);
            return true;
        }
        return false;
    }

    @Override
    public String saveReservation(ReservationDTO reservationDTO, boolean isAgent) {

        List<Flight> flightList = new ArrayList<>();

        for (Long flightId : reservationDTO.getFlightId()) {
            Flight flight = flightService.findById(flightId);
            if (null == flight) {
                return ResponseConstant.NO_FLIGHT_FOUND;
            }
            flightList.add(flight);
        }

        Reservation reservation = new Reservation();
        if (isAgent) {
            Agent agent = new Agent();
            agent.setId(reservationDTO.getAgentId());
            reservation.setAgent(agent);
        }

        User user = new User();
        user.setId(reservationDTO.getUserId());
        reservation.setCreatedBy(user);
        reservation.setCode(appUtil.generateReservationCode());
        reservation.setPassenger(copyPassengerDTO(reservationDTO.getPassengerDTO()));
        reservation.setFlightList(flightList);
        reservation.setStatus(ReservationStatus.NEW);
        reservation.setEmailSend(false);
        reservationRepository.save(reservation);
        return ResponseConstant.RESERVATION_CONFIRMED + "Your Reservation code is" + reservation.getCode();
    }

    @Override
    public String confirmByAgent(Long agentId, Long id) {
        Reservation reservation = findById(id);
        if (null == reservation || !reservation.getAgent().getId().equals(agentId))
            throw new BadClientException("Invalid Reservation");
        return confirmReservation(reservation);
    }

    private String confirmReservation(Reservation reservation) {
        if (reservation.getStatus().equals(ReservationStatus.CONFIRMED))
            throw new BadClientException("Your Reservation already confirmed");
        if (reservation.getStatus().equals(ReservationStatus.CANCEL))
            throw new BadClientException("Please Make Reservation first before confirm");

        List<Ticket> tickets = new ArrayList<>();

        reservation.getFlightList().forEach(flight -> {
            Ticket ticket = new Ticket();
            ticket.setReservation(reservation);
            ticket.setFlightDate(flight.getDepartureDate());
            ticket.setNumber(appUtil.generateTicketNumber());
            tickets.add(ticket);
        });
        reservation.setTicketList(tickets);
        reservation.setStatus(ReservationStatus.CONFIRMED);
        reservationRepository.save(reservation);

        return "You reservation has been confirmed and your tickets are " + tickets.stream().map(Ticket::getNumber).reduce("", (a, b) -> a + " , " + b);
    }


    @Override
    public String confirmByPassenger(Long passengerId, Long id) {
        Reservation reservation = findById(id);
        if (reservation == null || !reservation.getCreatedBy().getId().equals(passengerId))
            throw new BadClientException("Invalid Reservation");
        return confirmReservation(reservation);
    }

    @Override
    public String cancelledReservation(Long loggedInPassengerId, Long reservationId) {
        Reservation reservation = findById(reservationId);

        if (reservation == null || !reservation.getCreatedBy().getId().equals(loggedInPassengerId))
            throw new BadClientException("Invalid Reservation");
        if (reservation.getStatus().equals(ReservationStatus.CANCEL))
            throw new BadClientException("You already cancelled the reservation");

        reservation.setStatus(ReservationStatus.CANCEL);
        reservation.setFlightList(new ArrayList<>());
        reservation.setTicketList(new ArrayList<>());
        reservationRepository.save(reservation);
        return "Your Reservation Successfully Cancelled";
    }

    @Override
    public List<Reservation> findListOfReservationOfCurrentLoggedInUser(Long userId) {
        return reservationRepository.findReservationOfCurrentUser(userId);
    }

    @Override
    public List<Flight> findDetailOfReservation(Long loggedInUserId, Long reservationId) {
        return reservationRepository.findDetailOfReservation(loggedInUserId, reservationId);
    }


    private Passenger copyPassengerDTO(PassengerDTO passengerDTO) {
        Passenger passenger = new Passenger();
        passenger.setAddress(copyAddressData(passengerDTO.getAddress()));
        passenger.setEmail(passengerDTO.getEmail());
        passenger.setDateOfBirth(passengerDTO.getDateOfBirth());
        passenger.setFirstName(passengerDTO.getFirstName());
        passenger.setLastName(passengerDTO.getLastName());
        return passenger;
    }

    private Address copyAddressData(AddressDTO addressDTO) {
        Address address = new Address();
        address.setCity(addressDTO.getCity());
        address.setState(addressDTO.getState());
        address.setZip(addressDTO.getZip());
        address.setStreet(addressDTO.getStreet());
        return address;
    }

    @Scheduled(cron = "0/60 * * * * *")
    public void sendEmailBefore24Hour() {
        System.out.println("==============sheduler start=================== ");
        List<Reservation> reservationList = reservationRepository.findAllFromReservationStatusAndEmailIsNotSend(ReservationStatus.CONFIRMED);
        for (Reservation reservation : reservationList) {
            for (Ticket ticket : reservation.getTicketList()) {
                if (appUtil.dateDifferenceInDay(ticket.getFlightDate(), new Date()) <= 1) {
                    sendEmailAndUpdateReservation(reservation, ticket);
                    break;
                }
            }
        }
    }

    private void sendEmailAndUpdateReservation(Reservation reservation, Ticket ticket) {
        Email email = new Email();
        email.setTo(reservation.getPassenger().getEmail());
        email.setSubject("Flight Alert");
        email.setBody("Your ticket number " + ticket.getNumber() + " has 24 hours to boarding. Please be on time");
        jmsTemplate.convertAndSend(JmsConstant.EMAIL_QUEUE, email);
        System.out.println("Email Send to "+ email.getTo());
        reservation.setEmailSend(true);
        reservationRepository.save(reservation);
    }

}
