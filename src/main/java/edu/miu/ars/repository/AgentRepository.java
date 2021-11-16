package edu.miu.ars.repository;

import edu.miu.ars.domain.Agent;
import edu.miu.ars.domain.Airline;
import edu.miu.ars.domain.Flight;
import edu.miu.ars.domain.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AgentRepository extends JpaRepository<Agent,Long> {
    @Query("select a.passengerList from Agent a where a.id=:id")
    List<Passenger> findPassengerList(long id);
}
