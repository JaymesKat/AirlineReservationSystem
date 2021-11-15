package edu.miu.ars.service;

import edu.miu.ars.domain.Airline;
import edu.miu.ars.domain.FlightInfo;

import java.util.List;

public interface IFlightInfo {
    FlightInfo addAFlightInfo(FlightInfo flightInfo);
    List<FlightInfo> getFlightInfos();
    FlightInfo getFlightInfo(long id);
    FlightInfo updateFlightInfo(long id, FlightInfo flightInfo);
    String removeFlightInfo(long id);
}
