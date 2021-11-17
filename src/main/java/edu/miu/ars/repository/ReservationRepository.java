package edu.miu.ars.repository;

import edu.miu.ars.domain.Reservation;
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


    Reservation findByCode(String reservationCode);
}

