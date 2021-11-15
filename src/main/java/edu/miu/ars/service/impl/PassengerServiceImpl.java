package edu.miu.ars.service.impl;

import edu.miu.ars.domain.Passenger;
import edu.miu.ars.repository.PassengerRepository;
import edu.miu.ars.service.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class PassengerServiceImpl implements PassengerService {

    private final PassengerRepository passengerRepository;
    @Autowired
    public PassengerServiceImpl(PassengerRepository passengerRepository) {
        this.passengerRepository = passengerRepository;}

    @Override
    public Passenger save(Passenger passenger) {
        return null != passenger ? passengerRepository.save(passenger) : null;
    }

    @Override
    public List<Passenger> findAll() {
        return passengerRepository.findAll();
    }

    @Override
    public Passenger findById(Long id) {
        return passengerRepository.findById(id).orElse(null);
    }

    @Override
    public boolean update(Passenger passenger, Long id) {
        Passenger passengerFromDB = findById(id);
        if (passengerFromDB != null) {
            passengerFromDB.setFirstName(passenger.getFirstName());
            passengerFromDB.setLastName(passenger.getLastName());
            passengerFromDB.setDateOfBirth(passenger.getDateOfBirth());
            passengerFromDB.setReservationList(passenger.getReservationList());
            save(passengerFromDB);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteById(Long id) {
        Passenger passengerFromDB = findById(id);
        if (null != passengerFromDB) {
            passengerRepository.delete(passengerFromDB);
            return true;
        }
        return false;
    }
}
