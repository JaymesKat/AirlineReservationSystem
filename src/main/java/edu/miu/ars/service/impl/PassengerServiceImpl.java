package edu.miu.ars.service.impl;

import edu.miu.ars.DTO.ReservationDTO;
import edu.miu.ars.domain.*;
import edu.miu.ars.repository.PassengerRepository;
import edu.miu.ars.service.FlightInfoService;
import edu.miu.ars.service.FlightService;
import edu.miu.ars.service.PassengerService;
import edu.miu.ars.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PassengerServiceImpl implements PassengerService {

    private final PassengerRepository passengerRepository;
    private final ReservationService reservationService;
    private final FlightService flightService;

    @Autowired
    private FlightInfoService flightInfoService;

    @Autowired
    public PassengerServiceImpl(
            PassengerRepository passengerRepository,
            ReservationService reservationService,
            FlightService flightService) {
        this.passengerRepository = passengerRepository;
        this.reservationService = reservationService;
        this.flightService = flightService;
    }

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

    @Override
    public List<?> viewListOfReservations(Long id) {
        return passengerRepository.viewListOfReservations(id);
    }

    @Override
    public List<?> viewReservationDetails(Long id) {
        return passengerRepository.viewReservationDetails(id);
    }


    @Override
    public Reservation makeReservation(Long pid, ReservationDTO dto) {
       Reservation reservation = new Reservation();
       reservation.setStatus(ReservationState.PENDING);
       String reservationCode = dto.generateCode();
       reservation.setCode(reservationCode);

       Passenger passenger = findById(pid);

       List<Flight> flights = dto.getFlightNumbers()
               .stream()
               .map(flightNumber -> flightService.findByNumber(flightNumber))
               .collect(Collectors.toList());

       List<FlightInfo> flightInfos = flights.stream()
               .map(flight -> flightInfoService.createFromFlight(flight, dto.getDepartureDate()))
               .collect(Collectors.toList());

       for(FlightInfo flightInfo: flightInfos)
           reservation.addFlightInfo(flightInfo);

       passenger.addReservation(reservation);
       passengerRepository.save(passenger);

        Reservation newReservation = reservationService.findByCode(reservationCode);
        return newReservation;
    }
}
