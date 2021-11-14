package edu.miu.ars.service.impl;

import edu.miu.ars.domain.Airline;
import edu.miu.ars.domain.Flight;
import edu.miu.ars.repository.AirlineRepository;
import edu.miu.ars.repository.FlightRepository;
import edu.miu.ars.service.IAirline;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AirlineServiceImpl implements IAirline {
    @Autowired
    private AirlineRepository airlineRepository;

    @Override
    public Airline addAirline(Airline airline) {
        return airlineRepository.save(airline);
    }

    @Override
    public List<Airline> getAirlines() {
        return airlineRepository.findAll();
    }

    @Override
    public Airline getAirline(long id) {
        return airlineRepository.getById(id);
    }

    @Override
    public Airline updateAirline(long id, Airline airline) {
        // Don't use setFlights to override Flights
        Airline updateAirline = airlineRepository.getById(id);
        updateAirline.setCode(airline.getCode());
        updateAirline.setHistory(airline.getHistory());
        updateAirline.setName(airline.getName());
        return airlineRepository.save(updateAirline);
    }

    @Override
    public String removeAirline(long id) {
        Airline airline = airlineRepository.getById(id);
        if(airline!=null) {
            airlineRepository.delete(airline);
            return "Airline with id "+id+" has been deleted";
        }
        return "Airline not found";
    }
}
