package edu.miu.ars.controller;

import edu.miu.ars.constant.ResponseConstant;
import edu.miu.ars.domain.*;
import edu.miu.ars.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {


    private final TicketService ticketService;
    @Autowired
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable long id){
        Ticket ticket = ticketService.findById(id);
        return ticket != null ? ResponseEntity.ok(ticket) :
                ResponseEntity.badRequest().body(ResponseConstant.NOT_FOUND);
    }

    @GetMapping
    public List<Ticket> findAll(){
        return ticketService.findAll();
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Ticket ticket){
        return ticketService.save(ticket) != null ? ResponseEntity.ok(ResponseConstant.SAVE_SUCCESS)
                : ResponseEntity.badRequest().body(ResponseConstant.SAVE_FAILED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id,@RequestBody Ticket ticket){
        if(id.equals(ticket.getId())){
            return ticketService.update(ticket, id) ? ResponseEntity.ok(ResponseConstant.UPDATE_SUCCESS)
                    : ResponseEntity.badRequest().body(ResponseConstant.UPDATE_FAILED);
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable long id){
        return ticketService.deleteById(id) ? ResponseEntity.ok(ResponseConstant.DELETE_SUCCESS)
                : ResponseEntity.badRequest().body(ResponseConstant.DELETE_FAILED);
    }




}
