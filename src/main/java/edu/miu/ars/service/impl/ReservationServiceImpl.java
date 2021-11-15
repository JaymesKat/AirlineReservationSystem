package edu.miu.ars.service.impl;

import edu.miu.ars.domain.Reservation;
import edu.miu.ars.repository.ReservationRepository;
import edu.miu.ars.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ReservationServiceImpl implements ReservationService {
    private final ReservationRepository reservationRepository;
    @Autowired
    public ReservationServiceImpl(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }


    @Override
    public Reservation save(Reservation reservation) {
        return reservation != null ? reservationRepository.save(reservation): null;
    }

    @Override
    public List<Reservation> findAll() {
        return reservationRepository.findAll();
    }

    @Override
    public Reservation findById(Long id) {
        return reservationRepository.findById(id).orElse(null);
    }

    @Override
    public boolean update(Reservation reservation, Long id) {
        Reservation reservationFromDB = findById(id);
        if(reservationFromDB != null){
            reservationFromDB.setCode(reservation.getCode());
          //  reservationFromDB.setStatus(reservation.getStatus());
          //  reservationFromDB.setAgent(reservation.getAgent());
          //  reservationFromDB.setPassenger(reservation.getPassenger());
            reservationFromDB.setTicketList(reservation.getTicketList());
            save(reservationFromDB);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteById(Long id) {
        Reservation reservation = findById(id);
        if(reservation != null){
            reservationRepository.delete(reservation);
            return true;
        }
        return false;
    }
}
