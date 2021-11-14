package edu.miu.ars.service.impl;

import edu.miu.ars.domain.AppUser;
import edu.miu.ars.domain.Passenger;
import edu.miu.ars.domain.Reservation;
import edu.miu.ars.repository.PassengerRepository;
import edu.miu.ars.service.IPassenger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class PassengerServiceImpl implements IPassenger {

    @Autowired
    private PassengerRepository passengerRepository;
    @Autowired
    private AppUserServiceImpl appUserService;
    @Override
    public Passenger addPassenger(Passenger passenger) {
        return passengerRepository.save(passenger);
    }

    @Override
    public List<Passenger> getPassengers() {
        return passengerRepository.findAll();
    }

    @Override
    public Passenger getPassenger(long id) {
        return passengerRepository.getById(id);
    }

    @Override
    public Passenger updatePassenger(long id, Passenger passenger) {

        return (Passenger)appUserService.updateAppUser(id ,passenger);
    }

    @Override
    public String removePassenger(long id) {
        Passenger passenger = passengerRepository.getById(id);
        if(passenger!=null) {
            passengerRepository.delete(passenger);
            return "Passenger with id "+id+" has been deleted";
        }
        return "Passenger not found";
    }
}
