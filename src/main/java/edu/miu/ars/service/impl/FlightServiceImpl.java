package edu.miu.ars.service.impl;

import edu.miu.ars.domain.Flight;
import edu.miu.ars.repository.FlightRepository;
import edu.miu.ars.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class FlightServiceImpl implements FlightService {

    private final FlightRepository flightRepository;

    @Autowired
    public FlightServiceImpl(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    @Override
    public Flight save(Flight flight) {
        return null != flight ? flightRepository.save(flight) : null;
    }

    @Override
    public List<Flight> findAll() {
        return flightRepository.findAll();
    }

    @Override
    public Flight findById(Long id) {
        return flightRepository.findById(id).orElse(null);
    }

    @Override
    public boolean update(Flight flight, Long id) {
        Flight flightFromDB = findById(id);
        if (flightFromDB != null) {
            flightFromDB.setAirline(flight.getAirline());
            flightFromDB.setArrivalTime(flight.getArrivalTime());
            flightFromDB.setCapacity(flight.getCapacity());
            flightFromDB.setDestination(flight.getDestination());
            flightFromDB.setNumber(flight.getNumber());
            flightFromDB.setDepartureTime(flight.getDepartureTime());
            flightFromDB.setOrigin(flight.getOrigin());
            save(flightFromDB);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteById(Long id) {
        Flight flightFromDB = findById(id);
        if (null != flightFromDB) {
            flightRepository.delete(flightFromDB);
            return true;
        }
        return false;
    }

    @Override
    public List<Flight> findFlightsByAirportCode(String code) {
        return flightRepository.findFlightsByAirportCode(code);
    }

    @Override

    public List<Flight> findListOfFlightBetweenDepartureAndDestinationForDate(String originCode, String destinationCode, Date date) {
        return flightRepository.findListOfFlightBetweenDepartureAndDestinationForDate(originCode, destinationCode, date);
    }
    public Flight findByNumber(String number) {
        return flightRepository.findByNumber(number);

    }
}
