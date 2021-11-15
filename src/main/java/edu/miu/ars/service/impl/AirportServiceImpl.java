package edu.miu.ars.service.impl;

import edu.miu.ars.domain.Airline;
import edu.miu.ars.domain.Airport;
import edu.miu.ars.domain.Flight;
import edu.miu.ars.repository.AirportRepository;
import edu.miu.ars.service.AirportService;
import edu.miu.ars.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
<<<<<<< HEAD
=======
import org.springframework.transaction.annotation.Transactional;
>>>>>>> 49ec4e79ecd4c292458daaea9f936893da26e894

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class AirportServiceImpl implements AirportService {

    private final AirportRepository airportRepository;
    private final FlightService flightService;

<<<<<<< HEAD
@Service
public class AirportServiceImpl implements IAirport {
=======
>>>>>>> 49ec4e79ecd4c292458daaea9f936893da26e894
    @Autowired
    public AirportServiceImpl(AirportRepository airportRepository, FlightService flightService) {
        this.airportRepository = airportRepository;
        this.flightService = flightService;
    }

    @Override
    public Airport save(Airport airport) {
        return null != airport ? airportRepository.save(airport) : null;
    }

    @Override
    public List<Airport> findAll() {
        return airportRepository.findAll();
    }

    @Override
    public Airport findById(Long id) {
        return airportRepository.findById(id).orElse(null);
    }

    @Override
    public boolean update(Airport airport, Long id) {
        Airport airportFromDB = findById(id);
        if (airportFromDB != null) {
            airportFromDB.setCode(airport.getCode());
            airportFromDB.setName(airport.getName());
            airportFromDB.setAddress(airport.getAddress());
            airportFromDB.setArrivals(airport.getArrivals());
            airportFromDB.setDepartures(airport.getDepartures());
            save(airportFromDB);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteById(Long id) {
        Airport airportFromDB = findById(id);
        if (null != airportFromDB) {
            airportRepository.delete(airportFromDB);
            return true;
        }
        return false;
    }

    @Override
    public List<Airline> findAllAirlineFlyingFromSpecificAirport(String airportCode) {
        List<Flight> flightList = flightService.findFlightsByAirportCode(airportCode);
        return flightList.stream().map(Flight::getAirline).distinct().collect(Collectors.toList());
    }
}
