package edu.miu.ars.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

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

    @Temporal(TemporalType.DATE)
    private LocalDate departureDate;

    public FlightInfo(Flight flight, LocalDate departureDate) {
        this.flight = flight;
        this.departureDate = departureDate;
    }
}
