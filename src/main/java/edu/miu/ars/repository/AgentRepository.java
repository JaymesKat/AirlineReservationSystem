package edu.miu.ars.repository;

import edu.miu.ars.domain.Agent;
import edu.miu.ars.domain.Airline;
import edu.miu.ars.domain.Passenger;
import edu.miu.ars.domain.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface AgentRepository extends JpaRepository<Agent,Long> {
//    @Query(value ="Select a.passengerList from Agent a where a.id =:id" , nativeQuery = true)
//    List<Passenger> findPassangerForAgent(long id);
    @Query(value ="Select u.id as passengerId,u.first_name,u.last_name,r.code as reservationCode,r.status as reservationStatus from flight_reservation.agent_passenger_list pl join  passenger p on pl.passenger_list_id = p.id join user u on p.id = u.id join reservation r on p.id = r.passenger_id where agent_id = :id" , nativeQuery = true)
    List<Map<String,Object>> findPassangerForAgent(@Param("id") long id);

    @Query("Select passengers.reservationList from Agent a join a.passengerList passengers where a.id =:id")
    List<Reservation> findReservationsForAgent(long id);
}
