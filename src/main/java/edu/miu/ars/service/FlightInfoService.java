package edu.miu.ars.service;

import edu.miu.ars.DTO.TicketDTO;
import edu.miu.ars.domain.Airline;
import edu.miu.ars.domain.Flight;
import edu.miu.ars.domain.FlightInfo;
import edu.miu.ars.domain.Reservation;

import java.util.List;

public interface FlightInfoService extends GenericService<FlightInfo> {

    FlightInfo createFromFlight(Flight flight, String dateString);

    List<TicketDTO> generateTickets(Reservation reservation);
}
