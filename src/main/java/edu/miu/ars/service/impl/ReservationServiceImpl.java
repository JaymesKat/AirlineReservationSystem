package edu.miu.ars.service.impl;

import edu.miu.ars.domain.Airline;
import edu.miu.ars.domain.Reservation;
import edu.miu.ars.repository.AirlineRepository;
import edu.miu.ars.repository.ReservationRepository;
import edu.miu.ars.service.IAirline;
import edu.miu.ars.service.IReservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationServiceImpl implements IReservation {
    @Autowired
    private ReservationRepository reservationRepository;

    @Override
    public Reservation addReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    @Override
    public List<Reservation> getReservations() {
        return reservationRepository.findAll();
    }

    @Override
    public Reservation getReservation(long id) {

        return reservationRepository.getById(id);
    }

    @Override
    public Reservation updateReservation(long id, Reservation reservation) {
        // Don't use setFlights to override Flights

        Reservation updateReservation = reservationRepository.getById(id);
        updateReservation.setCode(reservation.getCode());
        updateReservation.setStatus(reservation.getStatus());
        return reservationRepository.save(updateReservation);
    }

    @Override
    public String removeReservation(long id) {
        Reservation reservation = reservationRepository.getById(id);
        if(reservation!=null) {
            reservationRepository.delete(reservation);
            return "Reservation with id "+id+" has been deleted";
        }
        return "Reservation not found";
    }
}
