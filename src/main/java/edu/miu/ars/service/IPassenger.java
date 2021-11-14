package edu.miu.ars.service;

import edu.miu.ars.domain.Airline;
import edu.miu.ars.domain.Passenger;

import java.util.List;

public interface IPassenger {
    Passenger addPassenger(Passenger passenger);
    List<Passenger> getPassengers();
    Passenger getPassenger(long id);
    Passenger updatePassenger(long id, Passenger passenger);
    String removePassenger(long id);
}
