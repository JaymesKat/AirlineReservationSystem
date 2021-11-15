package edu.miu.ars.service.impl;

import edu.miu.ars.domain.Airline;
import edu.miu.ars.repository.AirlineRepository;
import edu.miu.ars.service.AirlineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AirlineServiceImpl implements AirlineService {

    private final AirlineRepository airlineRepository;

    @Autowired
    public AirlineServiceImpl(AirlineRepository airlineRepository) {
        this.airlineRepository = airlineRepository;
    }

    @Override
    public Airline save(Airline airline) {
        return null != airline ? airlineRepository.save(airline) : null;
    }

    @Override
    public List<Airline> findAll() {
        return airlineRepository.findAll();
    }

    @Override

    public Airline findById(Long id) {
        return airlineRepository.findById(id).orElse(null);
    }

    @Override
    public boolean update(Airline airline, Long id) {
        Airline airlineFromDB = findById(id);
        if (airlineFromDB != null) {
            airlineFromDB.setCode(airline.getCode());
            airlineFromDB.setName(airline.getName());
            airlineFromDB.setFlights(airline.getFlights());
            airlineFromDB.setHistory(airline.getHistory());
            save(airlineFromDB);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteById(Long id) {
        Airline airlineFromDB = findById(id);
        if (null != airlineFromDB) {
            airlineRepository.delete(airlineFromDB);
            return true;
        }
        return false;
    }

}
