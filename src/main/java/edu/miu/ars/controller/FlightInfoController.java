package edu.miu.ars.controller;

import edu.miu.ars.constant.ResponseConstant;
import edu.miu.ars.domain.Airline;
import edu.miu.ars.domain.Airport;
import edu.miu.ars.domain.Flight;
import edu.miu.ars.domain.FlightInfo;
import edu.miu.ars.service.FlightInfoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/flightInfo")
public class FlightInfoController {

    private final FlightInfoService flightInfoService;

    @Autowired
    public FlightInfoController(FlightInfoService flightInfoService) {
        this.flightInfoService = flightInfoService;
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
    @GetMapping("/flights-on-date")
    public ResponseEntity<?> findFlightsForDate(@RequestParam("originCode") String originCode,
                                                @RequestParam("destinationCode") String destinationCode,
                                                @RequestParam("date") String date) throws ParseException {
        List<Flight> flightList = new ArrayList<>();
        Date date1= new SimpleDateFormat("yyyy-MM-dd").parse(date);
        if (StringUtils.isNotEmpty(originCode)&&StringUtils.isNotEmpty(destinationCode)&&date!=null)
            flightList = flightInfoService.findFlightsForDate(originCode, destinationCode, date1);
        return flightList.isEmpty() ? ResponseEntity.badRequest().body(ResponseConstant.NO_FLIGHTS_FOUND_FOR_THIS_DATE) :
                ResponseEntity.ok(flightList);
    }

}
