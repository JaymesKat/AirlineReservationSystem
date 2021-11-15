package edu.miu.ars.domain;

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


    @OneToMany(mappedBy = "reservation",cascade = CascadeType.PERSIST)
    private List<Ticket> ticketList = new ArrayList<>();


    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "varchar(32) default 'PENDING'")
    private ReservationState status;


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