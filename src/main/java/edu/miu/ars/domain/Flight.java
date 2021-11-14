package edu.miu.ars.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.Date;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Flight {
    @Id
    @GeneratedValue
    private Long id;
    private String number;
    private int capacity;
    @ManyToOne
    private Airport origin;

    @ManyToOne
    private Airport destination;

    @ManyToOne
    @JoinColumn(name="airline_id")
    private Airline airline;


    //TODO: Narayan - Put Temporal on time later (Putting String is easy to pass parameter from postman)
    @Temporal(TemporalType.TIME)
    private Date departureTime;
    @Temporal(TemporalType.TIME)
    private Date arrivalTime;

    public Flight(String number, int capacity, Airport departure, Airport arrival, Date departureTime, Date arrivalTime) {
        this.number = number;
        this.capacity = capacity;
        this.origin = departure;
        this.destination = arrival;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
    }
}
