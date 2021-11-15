package edu.miu.ars.controller;

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
}
