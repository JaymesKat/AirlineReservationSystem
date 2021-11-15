package edu.miu.ars.service;

import edu.miu.ars.domain.Airline;

import java.util.List;

public interface IAirline {
    Airline addAirline(Airline airline);
    List<Airline> getAirlines();
    Airline getAirline(long id);
    Airline updateAirline(long id, Airline airline);
    String removeAirline(long id);
}
