package edu.miu.ars.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class Ticket {
    @Id
    @GeneratedValue
    private Long id;
    @Column(length = 20,nullable = false)
    private String number;
    @Temporal(TemporalType.DATE)
    private Date flightDate;

    @ManyToOne
    Reservation reservation;

    @ManyToOne
    FlightInfo flightInfo;

    public Ticket(String number, Date flightDate) {
        this.number = number;
        this.flightDate = flightDate;
    }


}
