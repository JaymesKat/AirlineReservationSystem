package edu.miu.ars.service;

import edu.miu.ars.domain.Agent;
import edu.miu.ars.domain.Airline;
import edu.miu.ars.domain.Passenger;

import java.util.List;

public interface AgentService extends GenericService<Agent>{
    List<Passenger> findPassengerList(long id);
}
