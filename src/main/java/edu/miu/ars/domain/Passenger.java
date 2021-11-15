package edu.miu.ars.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Passenger extends User {
    @OneToMany
    @JoinColumn(name = "passenger_id")
    private List<Reservation> reservationList = new ArrayList<>();

    public void addReservation(Reservation reservation) {
        if (reservation != null)
            reservationList.add(reservation);
    }

    public boolean removeReservation(Reservation reservation){
        boolean result = false;
        if(reservation != null){
            result  = reservationList.remove(reservation);
        }
        return result;
    }

}
