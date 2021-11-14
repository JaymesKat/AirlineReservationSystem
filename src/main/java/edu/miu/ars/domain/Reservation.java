package edu.miu.ars.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Reservation {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    private Passenger passenger;
    @OneToMany
    private List<FlightInfo> flightInfoList = new ArrayList<>();
    @OneToMany
    private List<Ticket> ticketList = new ArrayList<>();

    private Reservation(Passenger passenger) {
        this.passenger = passenger;
    }

    public void addFlightInfo(FlightInfo flightInfo) {
        if (null != flightInfo)
            flightInfoList.add(flightInfo);
    }

    public void addTicket(Ticket ticket) {
        if (null != ticket)
            ticketList.add(ticket);
    }


}
