package edu.miu.ars.repository;

import edu.miu.ars.domain.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {

    @Query("select distinct f from Flight f where f.airline.code=:code")
    List<Flight> findFlightsByAirportCode(String code);

    @Query("select distinct f from Flight f where f.origin.code= :originCode and f.destination.code= :destinationCode and f.departureDate= :date")
    List<Flight> findListOfFlightBetweenDepartureAndDestinationForDate(String originCode, String destinationCode, Date date);

}
