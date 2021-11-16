package edu.miu.ars.controller;

import edu.miu.ars.constant.ResponseConstant;
import edu.miu.ars.domain.Agent;
import edu.miu.ars.domain.Airline;
import edu.miu.ars.domain.Passenger;
import edu.miu.ars.service.AgentService;
import org.apache.commons.lang3.StringUtils;
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
    @GetMapping("/agent-passengers")
    public ResponseEntity<?> findPassengerList(@RequestParam("agentId") Long id) {
        List<Passenger> passengerList = new ArrayList<>();
        if (id!=null)
            passengerList = agentService.findPassengerList(id);
        return passengerList.isEmpty() ? ResponseEntity.badRequest().body(ResponseConstant.NO_PASSENGERS_FOUND) :
                ResponseEntity.ok(passengerList);
    }
}
