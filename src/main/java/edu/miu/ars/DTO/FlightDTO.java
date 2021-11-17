package edu.miu.ars.DTO;

import edu.miu.ars.domain.Flight;
import lombok.Data;

import java.util.Date;

@Data
public class FlightDTO {
    private String flightNumber;
    private int capacity;
    private Date departureTime;
    private Date arrivalTime;
    private String origin;
    private String destination;

    public FlightDTO(Flight flight){
        this.capacity = flight.getCapacity();
        this.flightNumber = flight.getNumber();
        this.departureTime = flight.getDepartureTime();
        this.arrivalTime = flight.getArrivalTime();
        this.origin = flight.getOrigin().getName() + " (" + flight.getOrigin().getCode() + ")";
        this.destination = flight.getDestination().getName() + " (" + flight.getDestination().getCode() + ")";
    }
}
