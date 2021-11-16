package edu.miu.ars.repository;

import edu.miu.ars.domain.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Repository
public interface PassengerRepository extends JpaRepository<Passenger,Long> {
    @Query(value = "select r.code as RESERVATION_CODE, r.status as RESERVATION_STATUS from reservation r  where r.passenger_id = :pid", nativeQuery = true)
    List<Map<String,Object>> viewListOfReservations(@Param("pid") Long id);

    @Query(value = "select r.code, f2.number, f1.flight_id, f1.departure_date, f2.departure_time, f2.arrival_time from reservation r left join flight_info f1 on r.id = f1.reservation_id left join flight f2 on f2.id = f1.flight_id where r.passenger_id =:pid" , nativeQuery = true)
    List<Map<String,Object>> viewReservationDetails(@Param("pid") Long id);

  /*  @Modifying
    @Query(value = "insert into reservation(passenger_id, code, status) values (:pid,:code,:status)" , nativeQuery = true)
    @Transactional
    Integer makeReservation(@Param("pid") Long pid,@Param("code") String code,@Param("status") String status);*/

}
