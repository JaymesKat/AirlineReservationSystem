package edu.miu.ars.service;

import edu.miu.ars.domain.Reservation;
import edu.miu.ars.domain.ReservationState;
import org.springframework.data.jpa.repository.Query;


import java.util.List;

public interface ReservationService extends GenericService<Reservation>{
    ReservationState deleteByCode(String code);
    List<Reservation> findReservationById();
    boolean cancelReservation(String code);
    Reservation findByCode(String code);
}
