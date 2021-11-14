package edu.miu.ars.domain;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Agent extends AppUser {
    @OneToMany
    private List<Passenger> passengerList = new ArrayList<>();

    public void addPassenger(Passenger passenger) {
        if (null != passenger)
            passengerList.add(passenger);
    }
}
