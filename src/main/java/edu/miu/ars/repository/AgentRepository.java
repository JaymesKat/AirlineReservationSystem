package edu.miu.ars.repository;

import edu.miu.ars.domain.Agent;
import edu.miu.ars.domain.Airline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgentRepository extends JpaRepository<Agent,Long> {
}
