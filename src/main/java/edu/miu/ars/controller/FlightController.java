package edu.miu.ars.controller;

import edu.miu.ars.constant.AppConstant;
import edu.miu.ars.constant.ResponseConstant;
import edu.miu.ars.domain.Address;
import edu.miu.ars.domain.Airline;
import edu.miu.ars.domain.Airport;
import edu.miu.ars.domain.Flight;
import edu.miu.ars.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/flights")
public class FlightController {
    private final FlightService flightService;

    @Autowired
    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping
    public List<Flight> findAll() {
        return flightService.findAll();
    }

    @PostMapping
    @PreAuthorize("hasAuthority(" + AppConstant.ROLE_ADMIN + ")")
    public ResponseEntity<?> save(@RequestBody Flight airline) {
        return null != flightService.save(airline) ? ResponseEntity.ok(ResponseConstant.SAVE_SUCCESS) :
                ResponseEntity.badRequest().body(ResponseConstant.SAVE_FAILED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Flight flight = flightService.findById(id);
        return null != flight ? ResponseEntity.ok(flight) :
                ResponseEntity.badRequest().body(ResponseConstant.NOT_FOUND);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority(" + AppConstant.ROLE_ADMIN + ")")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Flight flight) {
        if (id.equals(flight.getId())) {
            return flightService.update(flight, id) ? ResponseEntity.ok(ResponseConstant.UPDATE_SUCCESS) :
                    ResponseEntity.badRequest().body(ResponseConstant.UPDATE_FAILED);
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority(" + AppConstant.ROLE_ADMIN + ")")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        return flightService.deleteById(id) ? ResponseEntity.ok(ResponseConstant.DELETE_SUCCESS) :
                ResponseEntity.badRequest().body(ResponseConstant.DELETE_FAILED);
    }

    @PostConstruct
    public void saveDummyData() {
        Flight f1 = new Flight("A76", 100, new Date(), new Date());
        Airport a1= new Airport("CDR","Cader Rapid", new Address("Street","City","Zip","State"));
        Airport a2= new Airport("ORD","Chicago", new Address("Street1","City1","Zip1","State1"));
        Airline airline= new Airline("CD","name","history dshfkjsd");
        f1.setOrigin(a1);
        f1.setDestination(a2);
        f1.setAirline(airline);

        flightService.save(f1);


    }
}
