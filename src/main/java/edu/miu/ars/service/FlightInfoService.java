package edu.miu.ars.service;

import edu.miu.ars.domain.Airline;
import edu.miu.ars.domain.Flight;
import edu.miu.ars.domain.FlightInfo;

import java.util.List;

public interface FlightInfoService extends GenericService<FlightInfo> {

    FlightInfo createFromFlight(Flight flight, String dateString);
}
