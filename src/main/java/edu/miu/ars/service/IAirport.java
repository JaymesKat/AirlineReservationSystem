package edu.miu.ars.service;

import edu.miu.ars.domain.Address;
import edu.miu.ars.domain.Airline;
import edu.miu.ars.domain.Airport;

import java.util.List;

public interface IAirport {
    Airport addAirport(Airport airport);
    List<Airport> getAirports();
    Airport getAirport(long id);
    Airport updateAirport(long id, Airport airport);
    String removeAirport(long id);
    Airport updateAddress(long id, Address address);
}
