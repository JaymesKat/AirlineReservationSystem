package edu.miu.ars.controller;

import edu.miu.ars.constant.ResponseConstant;
import edu.miu.ars.domain.Ticket;
import edu.miu.ars.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tickets")
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

}
