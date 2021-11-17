package edu.miu.ars.service;


import edu.miu.ars.domain.Airline;
import edu.miu.ars.domain.Flight;

import edu.miu.ars.DTO.ReservationDTO;
import edu.miu.ars.domain.Passenger;
import edu.miu.ars.domain.Reservation;

import java.util.List;

public interface PassengerService extends GenericService<Passenger>{

    List<?> viewListOfReservations(Long id);
    List<?> viewReservationDetails(Long id);
    Reservation makeReservation(Long pid,ReservationDTO dto);

}
