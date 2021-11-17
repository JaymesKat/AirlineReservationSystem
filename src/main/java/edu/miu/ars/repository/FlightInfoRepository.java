package edu.miu.ars.repository;

import edu.miu.ars.domain.Airline;
import edu.miu.ars.domain.Airport;
import edu.miu.ars.domain.Flight;
import edu.miu.ars.domain.FlightInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface FlightInfoRepository extends JpaRepository<FlightInfo,Long> {

   @Query("select fInfo.flight from FlightInfo fInfo where fInfo.departureDate=:date and fInfo.flight.origin.code=:originCode and fInfo.flight.destination.code=:destinationCode")
   // @Query(value = "select f.* from flight as f join flight_info as fi on f.id=fi.flight_id where fi.departure_date='2021-11-14' ", nativeQuery = true)
    List<Flight> findFlightsForDate( String originCode, String destinationCode, Date date);

}

