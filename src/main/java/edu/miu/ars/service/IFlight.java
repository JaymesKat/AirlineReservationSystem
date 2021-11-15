package edu.miu.ars.service;

import edu.miu.ars.domain.Airline;
import edu.miu.ars.domain.Flight;

import java.util.List;

public interface IFlight {
    Flight addFlight(Flight flight);
    List<Flight> getFlights();
    Flight getFlight(long id);
    Flight updateFlight(long id, Flight flight);
    String removeFlight(long id);
}
