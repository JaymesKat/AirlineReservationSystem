package edu.miu.ars.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Airport {
    @Id
    @GeneratedValue
    private Long id;
    @Column(length = 3,nullable = false) //later on adding validation
    private String code;
    private String name;
    @Embedded
    private Address address;

    @OneToMany(mappedBy = "destination")
    List<Flight> arrivals;

    @OneToMany(mappedBy = "origin")
    List<Flight> departures;


    public Airport(String code, String name, Address address) {
        this.code = code;
        this.name = name;
        this.address = address;
    }
}
