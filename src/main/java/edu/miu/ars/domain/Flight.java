package edu.miu.ars.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Flight {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String number;
    private int capacity;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Airport origin;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Airport destination;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "airline_id")
    private Airline airline;

    @Override
    public String toString() {
        return "Flight{" +
                "number='" + number + '\'' +
                ", capacity=" + capacity +
                ", origin=" + origin +
                ", destination=" + destination +
                ", airline=" + airline +
                ", departureTime=" + departureTime +
                ", arrivalTime=" + arrivalTime +
                '}';
    }

    @Temporal(TemporalType.TIME)
    private Date departureTime;
    @Temporal(TemporalType.TIME)
    private Date arrivalTime;

    @Temporal(TemporalType.DATE)
    private Date departureDate;

    @Temporal(TemporalType.DATE)
    private Date arrivalDate;

    public Flight(String number, int capacity, Airport departure, Airport arrival, Date departureTime, Date arrivalTime, Date departureDate, Date arrivalDate) {
        this.number = number;
        this.capacity = capacity;
        this.origin = departure;
        this.destination = arrival;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.arrivalDate = arrivalDate;
        this.departureDate = departureDate;
    }

    public Flight(String number, int capacity, Date departureTime, Date arrivalTime, Date arrivalDate, Date departureDate) {
        this.number = number;
        this.capacity = capacity;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.arrivalDate = arrivalDate;
        this.departureDate = departureDate;
    }

    public Flight(String number, int capacity, Date arrivalDate, Date departureDate) {
        this.number=number;
        this.capacity=capacity;
        this.arrivalDate=arrivalDate;
        this.departureDate=departureDate;
    }
}
