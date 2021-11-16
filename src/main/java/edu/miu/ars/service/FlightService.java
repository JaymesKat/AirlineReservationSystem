package edu.miu.ars.service;

import edu.miu.ars.domain.Flight;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FlightService extends GenericService<Flight> {
    List<Flight> findFlightsByAirportCode(String code);

    Flight findByNumber(String number);

}
