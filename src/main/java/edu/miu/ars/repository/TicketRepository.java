package edu.miu.ars.repository;

import edu.miu.ars.domain.Airline;
import edu.miu.ars.domain.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends JpaRepository<Ticket,Long> {
}
