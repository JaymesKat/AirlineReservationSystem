package edu.miu.ars.service.impl;

import edu.miu.ars.domain.Airline;
import edu.miu.ars.domain.Ticket;
import edu.miu.ars.repository.AirlineRepository;
import edu.miu.ars.repository.TicketRepository;
import edu.miu.ars.service.ITicket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TicketServiceImpl implements ITicket {
    @Autowired
    private TicketRepository ticketRepository;

    @Override
    public Ticket addATicket(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    @Override
    public List<Ticket> getTickets() {
        return ticketRepository.findAll();
    }

    @Override
    public Ticket getTicket(long id) {
        return ticketRepository.getById(id);
    }

    @Override
    public Ticket updateTicket(long id, Ticket ticket) {
        Ticket updateTicket = ticketRepository.getById(id);
        updateTicket.setNumber(ticket.getNumber());
        updateTicket.setFlightDate(ticket.getFlightDate());
        return ticketRepository.save(updateTicket);
    }

    @Override
    public String removeTicket(long id) {
        Ticket ticket = ticketRepository.getById(id);
        if(ticket!=null) {
            ticketRepository.delete(ticket);
            return "Ticket with id "+id+" has been deleted";
        }
        return "Ticket not found";
    }
}
