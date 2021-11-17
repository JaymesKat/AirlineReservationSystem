package edu.miu.ars.service;

import edu.miu.ars.DTO.ReservationDTO;
import edu.miu.ars.domain.AppUser;
import edu.miu.ars.domain.Flight;
import edu.miu.ars.domain.Reservation;
import edu.miu.ars.domain.Ticket;

import java.util.List;

public interface ReservationService extends GenericService<Reservation> {
    String saveReservation(ReservationDTO reservationDTO, boolean isAgent);

    String confirmByAgent(Long agentId, Long id);

    String confirmByPassenger(Long passengerId, Long id);

    String cancelledReservation(Long loggedInUserId, Long reservationId);

    List<Reservation> findListOfReservationOfCurrentLoggedInUser(Long id);

    List<Flight> findDetailOfReservation(Long loggedInUserId, Long id);

    List<Ticket> getAllTicketsOfReservation(Long id, Long id1);
}
