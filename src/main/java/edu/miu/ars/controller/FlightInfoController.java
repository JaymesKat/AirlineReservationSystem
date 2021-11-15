package edu.miu.ars.controller;

import edu.miu.ars.constant.ResponseConstant;
import edu.miu.ars.domain.Airline;
import edu.miu.ars.domain.FlightInfo;
import edu.miu.ars.service.AirlineService;
import edu.miu.ars.service.FlightInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/flightInfo")
public class FlightInfoController {

    private final FlightInfoService flightInfoService;

    @Autowired
    public FlightInfoController(FlightInfoService iFlightInfo) {
        this.flightInfoService = iFlightInfo;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        FlightInfo flightInfo = flightInfoService.findById(id);
        return null != flightInfo ? ResponseEntity.ok(flightInfo) :
                ResponseEntity.badRequest().body(ResponseConstant.NOT_FOUND);
    }

    @GetMapping
    public List<FlightInfo> findAll() {
        return flightInfoService.findAll();
    }
    @PostMapping
    public ResponseEntity<?> save(@RequestBody FlightInfo flightInfo) {
        return null != flightInfoService.save(flightInfo) ? ResponseEntity.ok(ResponseConstant.SAVE_SUCCESS) :
                ResponseEntity.badRequest().body(ResponseConstant.SAVE_FAILED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody FlightInfo flightInfo) {
        if (id.equals(flightInfo.getId())) {
            return flightInfoService.update(flightInfo, id) ? ResponseEntity.ok(ResponseConstant.UPDATE_SUCCESS) :
                    ResponseEntity.badRequest().body(ResponseConstant.UPDATE_FAILED);
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        return flightInfoService.deleteById(id)?ResponseEntity.ok(ResponseConstant.DELETE_SUCCESS):
                ResponseEntity.badRequest().body(ResponseConstant.DELETE_FAILED);

    }

}
