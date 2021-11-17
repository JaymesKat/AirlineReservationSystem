package edu.miu.ars.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Agent extends User {
    @OneToMany
    //@JsonIgnore
    private List<Passenger> passengerList = new ArrayList<>();


    public void addPassenger(Passenger passenger) {
        if (passenger != null)
            passengerList.add(passenger);
    }

//    public boolean removeReservation(Reservation reservation){
//        boolean result = false;
//
//        if (reservation != null)
//           result = reservationList.add(reservation);
//        return result;
//    }
}
