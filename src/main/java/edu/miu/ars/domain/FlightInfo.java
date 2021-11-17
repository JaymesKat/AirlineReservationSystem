package edu.miu.ars.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class FlightInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Flight flight;

    //added
    @ManyToOne
    @JsonIgnore
    private Reservation reservation;

    @OneToOne(mappedBy = "flightInfo")
    private Ticket tickets;

    @Temporal(TemporalType.DATE)
    private Date departureDate;

    public FlightInfo(Flight flight, Date departureDate) {
        this.flight = flight;
        this.departureDate = departureDate;
    }

    /*public void addTicket(Ticket ticket){
        tickets.add(ticket);
    }
    public boolean removeTicket(Ticket ticket){
        boolean result = false;
        if(ticket != null){
            result = tickets.remove(ticket);
        }
        return result;
    }*/

    @Override
    public String toString() {
        return "FlightInfo{" +
               // "flight=" + flight +
                ", tickets=" + tickets +
                ", departureDate=" + departureDate +
                '}';
    }
}
