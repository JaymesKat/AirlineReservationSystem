package edu.miu.ars.service;

import edu.miu.ars.domain.Reservation;
import org.springframework.data.jpa.repository.Query;


import java.util.List;

public interface ReservationService extends GenericService<Reservation>{
    List<Reservation> findReservationById();

    Reservation findByCode(String reservationCode);
}
