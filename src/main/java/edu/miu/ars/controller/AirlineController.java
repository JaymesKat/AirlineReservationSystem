package edu.miu.ars.controller;

import edu.miu.ars.constant.AppConstant;
import edu.miu.ars.constant.ResponseConstant;
import edu.miu.ars.domain.Airline;
import edu.miu.ars.service.AirlineService;
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

import edu.miu.ars.domain.Airline;
import edu.miu.ars.service.IAirline;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/airlines")
public class AirlineController {

    private final AirlineService airlineService;

    @Autowired
    public AirlineController(AirlineService airlineService) {
        this.airlineService = airlineService;
    }

    @GetMapping
    public List<Airline> findAll() {
        return airlineService.findAll();
    }

    @PostMapping
    @PreAuthorize("hasAuthority(" + AppConstant.ROLE_ADMIN + ")")
    public ResponseEntity<?> save(@RequestBody Airline airline) {
        return null != airlineService.save(airline) ? ResponseEntity.ok(ResponseConstant.SAVE_SUCCESS) :
                ResponseEntity.badRequest().body(ResponseConstant.SAVE_FAILED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Airline airline = airlineService.findById(id);
        return null != airline ? ResponseEntity.ok(airline) :
                ResponseEntity.badRequest().body(ResponseConstant.NOT_FOUND);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority(" + AppConstant.ROLE_ADMIN + ")")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Airline airline) {
        if (id.equals(airline.getId())) {
            return airlineService.update(airline, id) ? ResponseEntity.ok(ResponseConstant.UPDATE_SUCCESS) :
                    ResponseEntity.badRequest().body(ResponseConstant.UPDATE_FAILED);
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority(" + AppConstant.ROLE_ADMIN + ")")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        return airlineService.deleteById(id) ? ResponseEntity.ok(ResponseConstant.DELETE_SUCCESS) :
                ResponseEntity.badRequest().body(ResponseConstant.DELETE_FAILED);

    }

}
