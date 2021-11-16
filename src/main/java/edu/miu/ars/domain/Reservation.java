package edu.miu.ars.domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 6)
    private String code;

    @OneToMany(mappedBy = "reservation",cascade = {CascadeType.ALL})
    private List<FlightInfo> flightInfos = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "varchar(32) default 'PENDING'")
    private ReservationState status;

    public void addFlightInfo(FlightInfo flightInfo) {
        if ( flightInfo != null)
            flightInfos.add(flightInfo);
            flightInfo.setReservation(this);
    }
    public boolean removeFlightInfo(FlightInfo flightInfo){
        boolean result = false;
        if(flightInfo != null){
            result =  flightInfos.remove(flightInfo);
        }
        return result;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                ", code='" + code + '\'' +
                ", flightInfos=" + flightInfos +
                ", status=" + status +
                '}';
    }

}