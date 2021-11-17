package edu.miu.ars.service.impl;

import edu.miu.ars.domain.Ticket;
import edu.miu.ars.repository.TicketRepository;
import edu.miu.ars.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
@Service
@Transactional
public class TicketServiceImpl implements TicketService {
    private final TicketRepository ticketRepository;

    @Autowired
    public TicketServiceImpl(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }


    @Override
    public Ticket save(Ticket ticket) {
        return ticket != null ? ticketRepository.save(ticket) : null;
    }

    @Override
    public List<Ticket> findAll() {
        return ticketRepository.findAll();
    }

    @Override
    public Ticket findById(Long id) {
        return ticketRepository.findById(id).orElse(null);
    }

    @Override
    public boolean update(Ticket ticket, Long id) {
        Ticket ticketFromDB = findById(id);
        if(ticketFromDB != null){
            ticketFromDB.setNumber(ticket.getNumber());
            ticketFromDB.setFlightDate(ticket.getFlightDate());
            ticketFromDB.setReservation(ticket.getReservation());
            save(ticketFromDB);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteById(Long id) {
        Ticket ticket = findById(id);
        if(ticket != null){
            ticketRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
