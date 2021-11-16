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


    @PostConstruct
    private void dummyData(){
        //Reservation only has a code and list of tickets
        Reservation reservation = new Reservation();
        reservation.setCode("ABC123"); //6 character

        Ticket t1 = new Ticket("12345678911234567890",new Date());
        Ticket t2 = new Ticket("74345678911234567892",new Date());
        Ticket t3 = new Ticket("98645678911234567893",new Date());

        reservation.setTicketList(Arrays.asList(t1,t2,t3));


        Ticket t4 = new Ticket("10045678911234567890",new Date());
        Ticket t5 = new Ticket("74345228911234567892",new Date());


        Reservation reservation2 = new Reservation();
        reservation2.setCode("DEF456"); //6 character

        reservation2.setTicketList(Arrays.asList(t4,t5));

        //flight details
        Flight f1 = new Flight("A82", 300, new Date(), new Date());
        FlightInfo flightInfo = new FlightInfo(f1,new Date());

        Airport a1= new Airport("CDR","Cader Rapid", new Address("Street","City","Zip","State"));
        Airport a2= new Airport("ORD","Chicago", new Address("Street1","City1","Zip1","State1"));


        Airline airline= new Airline("CD","name","history dshfkjsd");
        f1.setOrigin(a1);
        f1.setDestination(a2);
        f1.setAirline(airline);



    }

}
