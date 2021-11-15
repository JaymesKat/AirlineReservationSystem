package edu.miu.ars.service.impl;

import edu.miu.ars.domain.Flight;
import edu.miu.ars.domain.FlightInfo;
import edu.miu.ars.repository.FlightInfoRepository;
import edu.miu.ars.service.IFlightInfo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class FlightInfoServiceImpl implements IFlightInfo {
    @Autowired
    private FlightInfoRepository flightInfoRepository;
    @Override
    public FlightInfo addAFlightInfo(FlightInfo flightInfo) {
        return flightInfoRepository.save(flightInfo);
    }

    @Override
    public List<FlightInfo> getFlightInfos() {
        return flightInfoRepository.findAll();
    }

    @Override
    public FlightInfo getFlightInfo(long id) {
        return flightInfoRepository.getById(id);
    }

    @Override
    public FlightInfo updateFlightInfo(long id, FlightInfo flightInfo) {
        FlightInfo updateFlight = flightInfoRepository.getById(id);
        if(updateFlight != null){
            updateFlight.setDepartureDate(flightInfo.getDepartureDate());
            updateFlight.setFlight(flightInfo.getFlight());
            return flightInfoRepository.save(updateFlight);
        }
        return null;
    }

    @Override
    public String removeFlightInfo(long id) {
        FlightInfo flight = flightInfoRepository.getById(id);
        if(flight!=null) {
            flightInfoRepository.delete(flight);
            return "Flight Info with id "+id+" has been deleted";
        }
        return "Flight info not found";
    }
}
