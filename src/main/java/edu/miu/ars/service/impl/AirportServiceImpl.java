package edu.miu.ars.service.impl;

import edu.miu.ars.domain.Address;
import edu.miu.ars.domain.Airline;
import edu.miu.ars.domain.Airport;
import edu.miu.ars.repository.AirlineRepository;
import edu.miu.ars.repository.AirportRepository;
import edu.miu.ars.service.IAirport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AirportServiceImpl implements IAirport {
    @Autowired
    private AirportRepository airportRepository;

    @Override
    public Airport addAirport(Airport airport) {
        return airportRepository.save(airport);
    }

    @Override
    public List<Airport> getAirports() {
        return airportRepository.findAll();
    }

    @Override
    public Airport getAirport(long id) {
        return airportRepository.getById(id);
    }

    @Override
    public Airport updateAirport(long id, Airport airport) {
        Airport updateAirport = airportRepository.getById(id);
        updateAirport.setCode(airport.getCode());
        updateAirport.setName(airport.getName());
        return airportRepository.save(updateAirport);
    }

    @Override
    public String removeAirport(long id) {
        Airport airport = airportRepository.getById(id);
        if(airport!=null) {
            airportRepository.delete(airport);
            return "Airport with id "+id+" has been deleted";
        }
        return "Airport not found";
    }

    @Override
    public Airport updateAddress(long id, Address address) {
        Airport airport = airportRepository.findById(id).orElse(null);
        if(airport != null){
            airport.setAddress(address);
            return airportRepository.save(airport);
        }
        return null;
    }
}
