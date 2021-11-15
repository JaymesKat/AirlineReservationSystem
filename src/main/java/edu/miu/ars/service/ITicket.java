package edu.miu.ars.service;

import edu.miu.ars.domain.Agent;
import edu.miu.ars.domain.Airline;
import edu.miu.ars.domain.Ticket;

import java.util.List;

public interface ITicket {
    Ticket addATicket(Ticket ticket);
    List<Ticket> getTickets();
    Ticket getTicket(long id);
    Ticket updateTicket(long id, Ticket ticket);
    String removeTicket(long id);
}
