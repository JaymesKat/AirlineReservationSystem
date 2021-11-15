package edu.miu.ars.repository;

import edu.miu.ars.domain.Airline;
import edu.miu.ars.domain.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation,Long> {
}
