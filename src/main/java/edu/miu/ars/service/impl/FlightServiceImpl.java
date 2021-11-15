package edu.miu.ars.service.impl;

import edu.miu.ars.domain.Flight;
import edu.miu.ars.repository.FlightRepository;
import edu.miu.ars.service.IFlight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlightServiceImpl implements IFlight {
    @Autowired
    private FlightRepository flightRepository;

    @Override
    public Flight addFlight(Flight flight) {
        return flightRepository.save(flight);
    }

    @Override
    public List<Flight> getFlights() { //pagination
        return flightRepository.findAll();
    }

    @Override
    public Flight getFlight(long id) {
        return flightRepository.findById(id).orElse(null);
    }

    @Override
    public Flight updateFlight(long id, Flight f) {
        Flight flight = flightRepository.getById(id);
        flight.setNumber(f.getNumber());
        flight.setCapacity(f.getCapacity());
        flight.setDepartureTime(f.getDepartureTime());
        flight.setArrivalTime(f.getArrivalTime());

        flight.setAirline(f.getAirline());
        flight.setOrigin(f.getOrigin());
        flight.setDestination(f.getDestination());

        return flightRepository.save(flight);
    }

    @Override
    public String removeFlight(long id) {
        Flight flight = flightRepository.getById(id);
        if(flight!=null) {
            flightRepository.delete(flight);
            return "Flight with id "+id+" has been deleted";
        }
        return "Flight not found";
    }
}
