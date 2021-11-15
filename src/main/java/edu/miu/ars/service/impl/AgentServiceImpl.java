package edu.miu.ars.service.impl;

import edu.miu.ars.domain.Agent;
import edu.miu.ars.domain.AppUser;
import edu.miu.ars.repository.AgentRepository;
import edu.miu.ars.repository.TicketRepository;
import edu.miu.ars.service.IAgent;
import edu.miu.ars.service.IAppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AgentServiceImpl implements IAgent {
    @Autowired
    private IAppUser appUserService;
    @Autowired
    private AgentRepository agentRepository;
    @Override
    public Agent addAgent(Agent agent) {
        return agentRepository.save(agent);
    }

    @Override
    public List<Agent> getAgents() {
        return agentRepository.findAll();
    }

    @Override
    public Agent getAgent(long id) {
        return agentRepository.getById(id);
    }

    @Override
    public Agent updateAgent(long id, Agent agent) {
//        AppUser newAgent= agentRepository.getById(id);
        return (Agent)appUserService.updateAppUser(id, agent);
    }

    @Override
    public String removeAgent(long id) {
        return appUserService.removeAppUser(id);
    }
}