package edu.miu.ars.repository;

import edu.miu.ars.domain.Airport;
import edu.miu.ars.domain.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {
}
