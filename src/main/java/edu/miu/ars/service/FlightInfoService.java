package edu.miu.ars.service;

import edu.miu.ars.DTO.TicketDTO;
import edu.miu.ars.domain.Flight;
import edu.miu.ars.domain.FlightInfo;
import edu.miu.ars.domain.Reservation;

import java.util.Date;
import java.util.List;

public interface FlightInfoService extends GenericService<FlightInfo> {
    List<Flight> findFlightsForDate(String originCode, String destinationCode, Date date);
    FlightInfo createFromFlight(Flight flight, String dateString);
    List<TicketDTO> generateTickets(Reservation reservation);
}
