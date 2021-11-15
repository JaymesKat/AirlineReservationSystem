package edu.miu.ars.controller;

import edu.miu.ars.domain.Airline;
import edu.miu.ars.service.IAirline;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/airlines")
public class AirlineController {

    @Autowired
    IAirline airlineService;

    @GetMapping("/{id}")
    public Airline getAirline(@PathVariable int id){
        return airlineService.getAirline(id);
    }

    @GetMapping
    public List<Airline> getAirlines(){
        return airlineService.getAirlines();
    }

    @PostMapping
    public Airline addAirline(@RequestBody Airline airline){
        return airlineService.addAirline(airline);
    }

    @PutMapping("/{id}")
    public Airline updateAirline(@PathVariable long id, @RequestBody Airline airline){
        return airlineService.updateAirline(id, airline);
    }

    @DeleteMapping("/{id}")
    public String removeAirline(@PathVariable long id){
        return airlineService.removeAirline(id);
    }

}
