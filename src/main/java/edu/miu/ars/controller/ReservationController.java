package edu.miu.ars.controller;

import edu.miu.ars.domain.Reservation;
import edu.miu.ars.service.IReservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reservations")
public class ReservationController {

    @Autowired
    IReservation reservationService;

    @GetMapping("/{id}")
    public Reservation getReservation(@PathVariable long id){
        return reservationService.getReservation(id);
    }

    @GetMapping
    public List<Reservation> getReservations(){
        return reservationService.getReservations();
    }

    @PostMapping
    public Reservation addReservation(@RequestBody Reservation reservation){
        return reservationService.addReservation(reservation);
    }

    @PutMapping("/{id}")
    public Reservation updateReservation(@PathVariable long id,@RequestBody Reservation reservation){
        return reservationService.updateReservation(id, reservation);
    }

    @DeleteMapping("/{id}")
    public String removeReservation(@PathVariable long id){
        return reservationService.removeReservation(id);
    }
}
