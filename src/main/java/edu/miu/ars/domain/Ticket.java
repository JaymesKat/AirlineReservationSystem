package edu.miu.ars.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class Ticket {
    @Id
    @GeneratedValue
    private Long id;
    private String number;
    private String flightDate;

    public Ticket(String number, String flightDate) {
        this.number = number;
        this.flightDate = flightDate;
    }
}
