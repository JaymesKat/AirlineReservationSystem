package edu.miu.ars.service.impl;

import edu.miu.ars.domain.Airline;
import edu.miu.ars.domain.Airport;
import edu.miu.ars.domain.Flight;
import edu.miu.ars.domain.FlightInfo;
import edu.miu.ars.repository.AirlineRepository;
import edu.miu.ars.repository.FlightInfoRepository;
import edu.miu.ars.service.FlightInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class FlightInfoServiceImpl implements FlightInfoService {

    private FlightInfoRepository flightInfoRepository;

    @Autowired
    public FlightInfoServiceImpl(FlightInfoRepository flightInfoRepository) {
        this.flightInfoRepository = flightInfoRepository;
    }
    @Override
    public FlightInfo save(FlightInfo flightInfo) {
        return null != flightInfo ? flightInfoRepository.save(flightInfo) : null;
    }

    @Override
    public List<FlightInfo> findAll() {
        return flightInfoRepository.findAll();
    }

    @Override
    public FlightInfo findById(Long id) {
        return flightInfoRepository.findById(id).orElse(null);
    }

    @Override
    public boolean update(FlightInfo flightInfo, Long id) {
        FlightInfo updateFlight = findById(id);
        if(updateFlight != null){
            updateFlight.setDepartureDate(flightInfo.getDepartureDate());
            updateFlight.setFlight(flightInfo.getFlight());
            updateFlight.setTickets(flightInfo.getTickets());
            save(updateFlight);
        }
        return false;
    }

    @Override
    public boolean deleteById(Long id) {
        FlightInfo flightInfo = findById(id);
        if (null != flightInfo) {
            flightInfoRepository.delete(flightInfo);
            return true;
        }
        return false;
    }

    @Override
    public List<Flight> findFlightsForDate(String originCode, String destinationCode, Date date) {
        return flightInfoRepository.findFlightsForDate(originCode, destinationCode, date);
        //return flightInfoRepository.findFlightsForDate(date);
    }
}
