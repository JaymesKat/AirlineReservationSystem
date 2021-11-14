package edu.miu.ars.domain;

import lombok.AllArgsConstructor;
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

    @Column(length = 6,nullable = false)
    private String code;

    @ManyToOne
    @JoinTable(name="agent_reservation")
    private Agent agent;

    @ManyToOne
    private Passenger passenger;

    private ReservarionState status;

  //  @OneToMany
    //private List<FlightInfo> flightInfoList = new ArrayList<>();

    @OneToMany(mappedBy = "reservation")
    private List<Ticket> ticketList = new ArrayList<>();



  /*  public void addFlightInfo(FlightInfo flightInfo) {
        if (null != flightInfo)
            flightInfoList.add(flightInfo);
    }*/

    public void addTicket(Ticket ticket) {
        if ( ticket != null)
            ticketList.add(ticket);
    }

    public boolean removeTicket(Ticket ticket){
        boolean result = false;
        if(ticket != null){
           result =  ticketList.remove(ticket);
        }
        return result;

    }


}
