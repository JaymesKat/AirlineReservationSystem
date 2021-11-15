package edu.miu.ars.service;

import edu.miu.ars.domain.Airline;
import edu.miu.ars.domain.Airport;

import java.util.List;

public interface AirportService extends GenericService<Airport>{
    List<Airline> findAllAirlineFlyingFromSpecificAirport(String airportCode);

}
