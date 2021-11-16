package edu.miu.ars.service;
import edu.miu.ars.domain.Airline;
import edu.miu.ars.domain.Airport;
import edu.miu.ars.domain.Flight;
import edu.miu.ars.domain.FlightInfo;

import java.util.Date;
import java.util.List;
import edu.miu.ars.domain.FlightInfo;



public interface FlightInfoService extends GenericService<FlightInfo> {
    List<Flight> findFlightsForDate(String originCode, String destinationCode, Date date);
}
