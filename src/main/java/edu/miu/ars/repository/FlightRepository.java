package edu.miu.ars.repository;

import edu.miu.ars.domain.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {

    @Query("select distinct f from Flight f where f.airline.code=:code")
    List<Flight> findFlightsByAirportCode(String code);

}
