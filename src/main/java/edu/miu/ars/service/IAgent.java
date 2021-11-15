package edu.miu.ars.service;

import edu.miu.ars.domain.Agent;
import edu.miu.ars.domain.Airline;

import java.util.List;

public interface IAgent {
    Agent addAgent(Agent agent);
    List<Agent> getAgents();
    Agent getAgent(long id);
    Agent updateAgent(long id, Agent agent);
    String removeAgent(long id);
}
