package edu.miu.ars.service.impl;

import edu.miu.ars.DTO.FlightDTO;
import edu.miu.ars.config.exceptions.BadClientException;
import edu.miu.ars.domain.Airline;
import edu.miu.ars.domain.Airport;
import edu.miu.ars.domain.Flight;
import edu.miu.ars.repository.FlightRepository;
import edu.miu.ars.service.AirlineService;
import edu.miu.ars.service.AirportService;
import edu.miu.ars.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class FlightServiceImpl implements FlightService {

    private final FlightRepository flightRepository;
    private final AirlineService airlineService;
    private final AirportService airportService;

    @Autowired
    public FlightServiceImpl(FlightRepository flightRepository, @Lazy AirlineService airlineService, @Lazy AirportService airportService) {
        this.flightRepository = flightRepository;
        this.airlineService = airlineService;
        this.airportService = airportService;
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

    @Override
    public String saveFlight(FlightDTO flightDTO) {

        Airport origin = airportService.findById(flightDTO.getOriginAirportId());
        Airport destination = airportService.findById(flightDTO.getDestinationAirportId());
        Airline airline = airlineService.findById(flightDTO.getAirlineId());

        if (null == origin || null == destination || null == airline) {
            throw new BadClientException("Bad Details of airport or airline");
        }

        Flight flight = new Flight();
        flight.setAirline(airline);
        flight.setOrigin(origin);
        flight.setDestination(destination);
        flight.setNumber(flightDTO.getNumber());
        flight.setCapacity(flightDTO.getCapacity());
        flight.setDepartureTime(flightDTO.getDepartureTime());
        flight.setArrivalTime(flightDTO.getArrivalTime());
        flight.setDepartureDate(flightDTO.getDepartureDate());
        flight.setArrivalDate(flightDTO.getArrivalDate());
        Flight insertedFlight = flightRepository.save(flight);
        return "Flight Successfully added flight " + insertedFlight.getNumber();
    }
}
