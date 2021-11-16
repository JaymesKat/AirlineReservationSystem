package edu.miu.ars.service;

import edu.miu.ars.domain.Flight;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.List;

public interface FlightService extends GenericService<Flight> {

    List<Flight> findFlightsByAirportCode(String code);
    List<Flight> findListOfFlightBetweenDepartureAndDestinationForDate(String originCode, String destinationCode, Date parseDate);

}
