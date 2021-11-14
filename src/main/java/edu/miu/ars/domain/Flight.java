package edu.miu.ars.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

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
    private Airport departure;
    @ManyToOne
    private Airport arrival;
    //TODO: Narayan - Put Temporal on time later (Putting String is easy to pass parameter from postman)
    private String departureTime;
    private String arrivalTime;

    public Flight(String number, int capacity, Airport departure, Airport arrival, String departureTime, String arrivalTime) {
        this.number = number;
        this.capacity = capacity;
        this.departure = departure;
        this.arrival = arrival;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
    }
}
