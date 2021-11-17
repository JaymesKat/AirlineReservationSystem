package edu.miu.ars.repository;

import edu.miu.ars.domain.FlightNotification;
import edu.miu.ars.domain.Reservation;
import edu.miu.ars.domain.ReservationState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation,Long> {
    @Query("from Reservation")
    List<Reservation> findFlightsByAirportCode();

   // @Query("select r.ticketList from Reservation r where r.code:code")
    //List<Reservation> cancelReservation();

    //Reservation findByCode(String reservationCode);

    @Query("select r from Reservation r where r.status=:status and r.flightNotification=:flightNotification")
    List<Reservation> getNotNotifiedConfirmedReservations(ReservationState status, FlightNotification flightNotification);

    Reservation findByCode(String reservationCode);
}

