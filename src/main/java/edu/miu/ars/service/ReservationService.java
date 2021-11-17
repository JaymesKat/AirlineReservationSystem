package edu.miu.ars.service;

import edu.miu.ars.DTO.ConfirmedReservationDTO;
import edu.miu.ars.DTO.TicketDTO;
import edu.miu.ars.domain.Reservation;


import java.util.List;

public interface ReservationService extends GenericService<Reservation>{
    List<Reservation> findReservationById();

    Reservation findByCode(String reservationCode);
    ConfirmedReservationDTO confirmReservation(Reservation reservation);
}
