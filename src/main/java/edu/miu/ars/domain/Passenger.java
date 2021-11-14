package edu.miu.ars.domain;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Passenger extends AppUser {
    @OneToMany(mappedBy = "passenger")
    private List<Reservation> reservationList = new ArrayList<>();

    private void addReservation(Reservation reservation) {
        if (null != reservation)
            reservationList.add(reservation);
    }

}
