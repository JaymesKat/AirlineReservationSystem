package edu.miu.ars.repository;

import edu.miu.ars.domain.Flight;
import edu.miu.ars.domain.Reservation;
import edu.miu.ars.enums.ReservationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    @Query("select r from Reservation r where r.createdBy.id=:userId")
    List<Reservation> findReservationOfCurrentUser(Long userId);

    @Query("select r.flightList from Reservation r where r.createdBy.id= :loggedInUserId and r.id= :reservationId")
    List<Flight> findDetailOfReservation(Long loggedInUserId, Long reservationId);

    @Query("select r from Reservation r where r.emailSend=false and r.status= :reservationStatus")
    List<Reservation> findAllFromReservationStatusAndEmailIsNotSend(ReservationStatus reservationStatus);
}
