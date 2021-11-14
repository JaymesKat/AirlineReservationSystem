package edu.miu.ars.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Agent extends AppUser {
//    @OneToMany
//    private List<Passenger> passengerList = new ArrayList<>();
    @OneToMany
    private List<Reservation> reservationList = new ArrayList<>();

    public void addReservation(Reservation reservation) {
        if (reservation != null)
            reservationList.add(reservation);
    }

    public boolean removeReservation(Reservation reservation){
        boolean result = false;

        if (reservation != null)
           result = reservationList.add(reservation);
        return result;
    }
}
