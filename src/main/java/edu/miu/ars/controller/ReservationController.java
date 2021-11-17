package edu.miu.ars.controller;

import edu.miu.ars.DTO.ConfirmedReservationDTO;
import edu.miu.ars.constant.ResponseConstant;
import edu.miu.ars.domain.Reservation;
import edu.miu.ars.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping
    public List<Reservation> findAll(){
        return reservationService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable long id){
        Reservation reservation = reservationService.findById(id);
        return reservation != null ? ResponseEntity.ok(reservation) :
                ResponseEntity.badRequest().body(ResponseConstant.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Reservation reservation){
        return reservationService.save(reservation) != null ? ResponseEntity.ok(ResponseConstant.SAVE_SUCCESS)
                : ResponseEntity.badRequest().body(ResponseConstant.SAVE_FAILED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id,@RequestBody Reservation reservation){
        if(id.equals(reservation.getId())){
            return reservationService.update(reservation, id) ? ResponseEntity.ok(ResponseConstant.UPDATE_SUCCESS)
                    : ResponseEntity.badRequest().body(ResponseConstant.UPDATE_FAILED);
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable long id){
       return reservationService.deleteById(id) ? ResponseEntity.ok(ResponseConstant.DELETE_SUCCESS)
               : ResponseEntity.badRequest().body(ResponseConstant.DELETE_FAILED);
    }
    @PatchMapping("/{code}/confirm")
    public ResponseEntity<?> confirmReservation(@PathVariable String code) {
        Reservation reservation = reservationService.findByCode(code);

        if (reservation != null) {
            ConfirmedReservationDTO confirmedReservation = null;
            confirmedReservation = reservationService.confirmReservation(reservation);
            return confirmedReservation != null ? ResponseEntity.ok().body(confirmedReservation)
                    : ResponseEntity.badRequest().body(ResponseConstant.RESERVATION_CONFIRMATION_FAILED);
        }
        return ResponseEntity.badRequest().body(ResponseConstant.RESERVATION_NOT_FOUND);
    }

    @PatchMapping ("/{reservationCode}/cancel")
    public ResponseEntity<?> cancelReservation(@PathVariable String reservationCode){

       return reservationService.cancelReservation(reservationCode)?ResponseEntity.ok(ResponseConstant.CANCEL_SUCCESS)
               : ResponseEntity.badRequest().body(ResponseConstant.CANCEL_FAILED);
    }
}
