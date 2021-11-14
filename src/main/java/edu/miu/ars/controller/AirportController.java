package edu.miu.ars.controller;

import edu.miu.ars.domain.Airline;
import edu.miu.ars.domain.Airport;
import edu.miu.ars.service.IAirport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/airports")
public class AirportController {

    @Autowired
    IAirport iAirport;


    @GetMapping("/{id}")
    public Airport getAirport(@PathVariable int id){
        return iAirport.getAirport(id);
    }

    @GetMapping
    public List<Airport> getAirports(){
        return iAirport.getAirports();
    }

    @PostMapping
    public Airport addAirport(@RequestBody Airport airport){
        return iAirport.addAirport(airport);
    }

    @PutMapping("/{id}")
    public Airport updateAirport(@PathVariable long id, @RequestBody Airport airport){
        return iAirport.updateAirport(id, airport);
    }

    @DeleteMapping("/{id}")
    public String removeAirport(@PathVariable long id){
        return iAirport.removeAirport(id);
    }
}
