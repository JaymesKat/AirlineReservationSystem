package edu.miu.ars.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import edu.miu.ars.enums.ReservationStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
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
    @Column(length = 6, nullable = false)
    private String code;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JsonIgnore
    private Passenger passenger;
    @ManyToOne
    @JsonIgnore
    private Agent agent;

    @ManyToMany
    @JoinTable(name="Reservation_Flights")
    @JsonIgnore
    private List<Flight> flightList= new ArrayList<>();

    @ManyToOne
    @JsonIgnore
    private User createdBy;
    private boolean emailSend;
    private ReservationStatus status;

    @OneToMany(mappedBy = "reservation", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Ticket> ticketList = new ArrayList<>();

    /*  public void addFlightInfo(FlightInfo flightInfo) {
        if (null != flightInfo)
          flightInfoList.add(flightInfo);
            }*/
    public void addTicket(Ticket ticket) {
        if (ticket != null)
            ticketList.add(ticket);
    }

    public boolean removeTicket(Ticket ticket) {
        boolean result = false;
        if (ticket != null) {
            result = ticketList.remove(ticket);
        }
        return result;
    }

    public void addFlight(Flight flight){
        flightList.add(flight);
    }
}