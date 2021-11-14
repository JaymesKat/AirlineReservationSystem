package edu.miu.ars.controller;

import edu.miu.ars.domain.Airline;
import edu.miu.ars.repository.AirlineRepository;
import edu.miu.ars.service.IAirline;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/airlines")
public class AirlineController {

    @Autowired
    IAirline iAirline;

    @GetMapping("/{id}")
    public Airline getAirline(@PathVariable int id){
        return iAirline.getAirline(id);
    }

    @GetMapping
    public List<Airline> getAirlines(){
        return iAirline.getAirlines();
    }

    @PostMapping
    public Airline addAirline(@RequestBody Airline airline){
        return iAirline.addAirline(airline);
    }

    @PutMapping("/{id}")
    public Airline updateAirline(@PathVariable long id, @RequestBody Airline airline){
        return iAirline.updateAirline(id, airline);
    }

    @DeleteMapping("/{id}")
    public String removeAirline(@PathVariable long id){
        return iAirline.removeAirline(id);
    }

}
