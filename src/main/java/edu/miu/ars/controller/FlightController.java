package edu.miu.ars.controller;

import edu.miu.ars.domain.Airport;
import edu.miu.ars.domain.Flight;
import edu.miu.ars.service.IFlight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/flights")
public class FlightController {

    @Autowired
    IFlight iFlight;


    @GetMapping("/{id}")
    public Flight getFlight(@PathVariable int id){
        return iFlight.getFlight(id);
    }

    @GetMapping
    public List<Flight> getFlights(){
        return iFlight.getFlights();
    }

    @PostMapping
    public Flight addAirport(@RequestBody Flight flight){
        return iFlight.addFlight(flight);
    }

    @PutMapping("/{id}")
    public Flight updateAirport(@PathVariable long id, @RequestBody Flight flight){
        return iFlight.updateFlight(id, flight);
    }

    @DeleteMapping("/{id}")
    public String removeAirport(@PathVariable long id){
        return iFlight.removeFlight(id);
    }
}
