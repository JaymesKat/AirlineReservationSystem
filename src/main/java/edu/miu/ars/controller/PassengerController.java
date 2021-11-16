package edu.miu.ars.controller;

import edu.miu.ars.DTO.ReservationDTO;
import edu.miu.ars.constant.ResponseConstant;
import edu.miu.ars.domain.Passenger;
import edu.miu.ars.domain.Reservation;
import edu.miu.ars.service.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/passengers")
public class PassengerController {
    private final PassengerService passengerService;
    @Autowired
    public PassengerController(PassengerService passengerService) {
        this.passengerService = passengerService;
    }
    @GetMapping
    public List<Passenger> findAll() {
        return passengerService.findAll();
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Passenger passenger) {
        return null != passengerService.save(passenger) ? ResponseEntity.ok(ResponseConstant.SAVE_SUCCESS) :
                ResponseEntity.badRequest().body(ResponseConstant.SAVE_FAILED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Passenger passenger = passengerService.findById(id);
        return null != passenger ? ResponseEntity.ok(passenger) :
                ResponseEntity.badRequest().body(ResponseConstant.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Passenger passenger) {
        if (id.equals(passenger.getId())) {
            return passengerService.update(passenger, id) ? ResponseEntity.ok(ResponseConstant.UPDATE_SUCCESS) :
                    ResponseEntity.badRequest().body(ResponseConstant.UPDATE_FAILED);
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        return passengerService.deleteById(id)?ResponseEntity.ok(ResponseConstant.DELETE_SUCCESS):
                ResponseEntity.badRequest().body(ResponseConstant.DELETE_FAILED);

    }

    @GetMapping("/{pid}/reservations")
    public List<?> reservations(@PathVariable Long pid ){
        return passengerService.viewListOfReservations(pid);
    }

    //one reservation (underdevelopment)
    @GetMapping("/{pid}/reservations/{id}")
    public List<?> reservationDetails(@PathVariable Long pid){
        return passengerService.viewReservationDetails(pid);
    }

    //make-reservation
    @PostMapping("/{pid}/reservations")
    public Reservation makeReservation(@PathVariable Long pid, @RequestBody ReservationDTO dto){
      return  passengerService.makeReservation(pid,dto);
    }
}
