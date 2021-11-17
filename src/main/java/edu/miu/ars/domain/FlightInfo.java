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

    @ManyToOne
    private Flight flight;

    //added
    @ManyToOne
    @JsonIgnore
    private Reservation reservation;

    @OneToOne(mappedBy = "flightInfo")
    @JsonIgnore
    private Ticket ticket;

    @Temporal(TemporalType.DATE)
    private Date departureDate;

    public FlightInfo(Flight flight, Date departureDate) {
        this.flight = flight;
        this.departureDate = departureDate;
    }

    @Override
    public String toString() {
        return "FlightInfo{" +
               // "flight=" + flight +
                ", ticket=" + ticket +
                ", departureDate=" + departureDate +
                '}';
    }
}
