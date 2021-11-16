package edu.miu.ars.controller;

import edu.miu.ars.constant.ResponseConstant;
import edu.miu.ars.domain.Agent;
import edu.miu.ars.domain.Airline;
import edu.miu.ars.domain.Passenger;
import edu.miu.ars.domain.Reservation;
import edu.miu.ars.service.AgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/agents")
public class AgentController {

    private final AgentService agentService;

    @Autowired
    public AgentController(AgentService agentService) {
        this.agentService = agentService;
    }

    @GetMapping
    public List<Agent> findAll() {
        return agentService.findAll();
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Agent agent) {
        return null != agentService.save(agent) ? ResponseEntity.ok(ResponseConstant.SAVE_SUCCESS) :
                ResponseEntity.badRequest().body(ResponseConstant.SAVE_FAILED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Agent agent = agentService.findById(id);
        return null != agent ? ResponseEntity.ok(agent) :
                ResponseEntity.badRequest().body(ResponseConstant.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Agent agent) {
        if (id.equals(agent.getId())) {
            return agentService.update(agent, id) ? ResponseEntity.ok(ResponseConstant.UPDATE_SUCCESS) :
                    ResponseEntity.badRequest().body(ResponseConstant.UPDATE_FAILED);
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        return agentService.deleteById(id)?ResponseEntity.ok(ResponseConstant.DELETE_SUCCESS):
                ResponseEntity.badRequest().body(ResponseConstant.DELETE_FAILED);

    }

    @GetMapping("/passangers-and-reservations-for")
    public ResponseEntity<?> findAllAirlineFlyingFromSpecificAirport(@RequestParam("agentid") Long id) {
        List<?> passangerReservationlist = new ArrayList<>();
        if (id != null)
            passangerReservationlist = agentService.findPassangerForAgent(id);
        return passangerReservationlist.isEmpty() ? ResponseEntity.badRequest().body(ResponseConstant.NO_AIRLINES_FOUND) :
                ResponseEntity.ok(passangerReservationlist);
    }

    @GetMapping("/reservation-details-for")
    public ResponseEntity<?> findReservationsForAgent(@RequestParam("agentid") Long id) {
        List<Reservation> reservationlist = new ArrayList<>();
        if (id != null)
            reservationlist = agentService.findReservationsForAgent(id);
        return reservationlist.isEmpty() ? ResponseEntity.badRequest().body(ResponseConstant.NO_AIRLINES_FOUND) :
                ResponseEntity.ok(reservationlist);
    }

}
