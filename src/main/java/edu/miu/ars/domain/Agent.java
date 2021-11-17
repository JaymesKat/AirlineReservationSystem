package edu.miu.ars.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Agent extends User {

    @OneToMany(mappedBy = "agent")
    private List<Reservation> reservationList = new ArrayList<>();

    public Agent(String email, String firstName, String lastName, Date dateOfBirth, Address address) {
        super(email, firstName, lastName, dateOfBirth, address);
    }

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
