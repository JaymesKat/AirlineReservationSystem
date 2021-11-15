package edu.miu.ars.service;

import edu.miu.ars.domain.Flight;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FlightService extends GenericService<Flight> {
    @Query("select f from Flight f where f.origin.code= :code")
    List<Flight> findFlightsByAirportCode(String code);


}
