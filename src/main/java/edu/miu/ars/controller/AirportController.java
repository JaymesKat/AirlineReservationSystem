package edu.miu.ars.controller;

<<<<<<< HEAD
import edu.miu.ars.domain.Airport;
import edu.miu.ars.service.IAirport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/airports")
public class AirportController {

    @Autowired
    IAirport airportService;


    @GetMapping("/{id}")
    public Airport getAirport(@PathVariable int id){
        return airportService.getAirport(id);
    }

    @GetMapping
    public List<Airport> getAirports(){
        return airportService.getAirports();
    }

    @PostMapping
    public Airport addAirport(@RequestBody Airport airport){
        return airportService.addAirport(airport);
    }

    @PutMapping("/{id}")
    public Airport updateAirport(@PathVariable long id, @RequestBody Airport airport){
        return airportService.updateAirport(id, airport);
    }

    @DeleteMapping("/{id}")
    public String removeAirport(@PathVariable long id){
        return airportService.removeAirport(id);
    }
=======
import edu.miu.ars.constant.AppConstant;
import edu.miu.ars.constant.ResponseConstant;
import edu.miu.ars.domain.Airline;
import edu.miu.ars.domain.Airport;
import edu.miu.ars.service.AirportService;
import org.apache.commons.lang3.StringUtils;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/airports")
public class AirportController {
    private final AirportService airportService;

    @Autowired
    public AirportController(AirportService airportService) {
        this.airportService = airportService;
    }

    @GetMapping
    public List<Airport> findAll() {
        return airportService.findAll();
    }

    @PostMapping
    @PreAuthorize("hasAuthority(" + AppConstant.ROLE_ADMIN + ")")
    public ResponseEntity<?> save(@RequestBody Airport airline) {
        return null != airportService.save(airline) ? ResponseEntity.ok(ResponseConstant.SAVE_SUCCESS) :
                ResponseEntity.badRequest().body(ResponseConstant.SAVE_FAILED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Airport airport = airportService.findById(id);
        return null != airport ? ResponseEntity.ok(airport) :
                ResponseEntity.badRequest().body(ResponseConstant.NOT_FOUND);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority(" + AppConstant.ROLE_ADMIN + ")")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Airport airport) {
        if (id.equals(airport.getId())) {
            return airportService.update(airport, id) ? ResponseEntity.ok(ResponseConstant.UPDATE_SUCCESS) :
                    ResponseEntity.badRequest().body(ResponseConstant.UPDATE_FAILED);
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority(" + AppConstant.ROLE_ADMIN + ")")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        return airportService.deleteById(id) ? ResponseEntity.ok(ResponseConstant.DELETE_SUCCESS) :
                ResponseEntity.badRequest().body(ResponseConstant.DELETE_FAILED);
    }

    @GetMapping("/airlines-flying-from")
    public ResponseEntity<?> findAllAirlineFlyingFromSpecificAirport(@RequestParam("airportCode") String airportCode) {
        List<Airline> airlineList = new ArrayList<>();
        if (StringUtils.isNotEmpty(airportCode))
            airlineList = airportService.findAllAirlineFlyingFromSpecificAirport(airportCode);
        return airlineList.isEmpty() ? ResponseEntity.badRequest().body(ResponseConstant.NO_AIRLINES_FOUND) :
                ResponseEntity.ok(airlineList);
    }

>>>>>>> 49ec4e79ecd4c292458daaea9f936893da26e894
}
