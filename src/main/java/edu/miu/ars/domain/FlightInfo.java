package edu.miu.ars.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class FlightInfo {
    @Id
    @GeneratedValue
    private Long id;
    @OneToOne
    private Flight flight;
    private String departureDate;

    public FlightInfo(Flight flight, String departureDate) {
        this.flight = flight;
        this.departureDate = departureDate;
    }
}
