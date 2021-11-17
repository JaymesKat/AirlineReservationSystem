package edu.miu.ars.service.impl;

import edu.miu.ars.domain.Agent;
import edu.miu.ars.domain.Airline;
import edu.miu.ars.repository.AgentRepository;
import edu.miu.ars.repository.AirlineRepository;
import edu.miu.ars.service.AgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AgentServiceImpl implements AgentService {

    private AgentRepository agentRepository;

    @Autowired
    public AgentServiceImpl(AgentRepository agentRepository) {
        this.agentRepository = agentRepository;
    }

    @Override
    public Agent save(Agent agent) {
        return null != agent ? agentRepository.save(agent) : null;
    }

    @Override
    public List<Agent> findAll() {
        return agentRepository.findAll();
    }

    @Override
    public Agent findById(Long id) {
        return agentRepository.findById(id).orElse(null);
    }

    @Override
    public boolean update(Agent agent, Long id) {
        Agent agentFromDB = findById(id);
        if (agentFromDB != null) {
            agentFromDB.setFirstName(agent.getFirstName());
            agentFromDB.setLastName(agent.getLastName());
            agentFromDB.setDateOfBirth(agent.getDateOfBirth());
            agentFromDB.setEmail(agent.getEmail());
            agentFromDB.setAddress(agent.getAddress());
            save(agentFromDB);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteById(Long id) {
        Agent agent = findById(id);
        if (null != agent) {
            agentRepository.delete(agent);
            return true;
        }
        return false;
    }
}
