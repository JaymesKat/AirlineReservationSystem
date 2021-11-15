package edu.miu.ars.repository;

import edu.miu.ars.domain.Airline;
import edu.miu.ars.domain.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface PassengerRepository extends JpaRepository<Passenger,Long> {
}
