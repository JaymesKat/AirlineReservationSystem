package edu.miu.ars.service.impl;
import edu.miu.ars.domain.Flight;
import edu.miu.ars.domain.FlightInfo;
import edu.miu.ars.domain.Reservation;
import edu.miu.ars.domain.ReservationState;
import edu.miu.ars.domain.Ticket;
import edu.miu.ars.repository.ReservationRepository;
import edu.miu.ars.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ReservationServiceImpl implements ReservationService {
    private final ReservationRepository reservationRepository;
    @Autowired
    public ReservationServiceImpl(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }


    @Override
    public Reservation save(Reservation reservation) {
        return reservation != null ? reservationRepository.save(reservation): null;
    }

    @Override
    public List<Reservation> findAll() {
        System.out.println("Reached at this point: "+reservationRepository.findById(1L));
        return reservationRepository.findAll();
    }

    @Override
    public Reservation findById(Long id) {
        return reservationRepository.findById(id).orElse(null);
    }
    @Override
    public Reservation findByCode(String reservationCode) {

        return reservationRepository.findByCode(reservationCode);

    }
    @Override
    public boolean update(Reservation reservation, Long id) {
        Reservation reservationFromDB = findById(id);
        if(reservationFromDB != null){
            reservationFromDB.setCode(reservation.getCode());
            reservationFromDB.setStatus(reservation.getStatus());
          //  reservationFromDB.setAgent(reservation.getAgent());
          //  reservationFromDB.setPassenger(reservation.getPassenger());
          //  reservationFromDB.setTicketList(reservation.getTicketList());
            reservationFromDB.setFlightInfos(reservation.getFlightInfos());
            save(reservationFromDB);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteById(Long id) {
        Reservation reservation = findById(id);
        if(reservation != null){
            reservationRepository.delete(reservation);
            return true;
        }
        return false;
    }
    @Override
    public ReservationState deleteByCode(String reservationCode) {
        Reservation reservation = findByCode(reservationCode);
        if(reservation != null)
            reservationRepository.delete(reservation);
        return ReservationState.CANCELLED;
    }
    @Override
    public List<Reservation> findReservationById() {
        return reservationRepository.findFlightsByAirportCode();
    }

    @Override

    public boolean cancelReservation(String reservationCode) {
        Reservation reservation = findByCode(reservationCode);
        if (reservation != null) {
            reservation.setStatus(ReservationState.CANCELLED);
            return true;
        }
        return false;
    }

    //public Reservation findByCode(String reservationCode) {
    //    return reservationRepository.findByCode(reservationCode);}

}
