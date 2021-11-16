package edu.miu.ars.service;

import edu.miu.ars.domain.Agent;
import edu.miu.ars.domain.Airline;
import edu.miu.ars.domain.Passenger;
import edu.miu.ars.domain.Reservation;

import java.util.List;

public interface AgentService extends GenericService<Agent>{
    List<?> findPassangerForAgent(long id);
    List<Reservation> findReservationsForAgent(long id);
}
