package edu.miu.ars.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    List<Flight> arrivals;

    @OneToMany(mappedBy = "origin")
    @JsonIgnore
    List<Flight> departures;


    public Airport(String code, String name, Address address) {
        this.code = code;
        this.name = name;
        this.address = address;
        arrivals = new ArrayList<>();
        departures = new ArrayList<>();
    }

    public void addArrivals(Flight flight){
        if(flight != null)
            arrivals.add(flight);
    }
    public boolean removeArrivals(Flight flight){
        boolean result = false;
        if(flight != null){
            result = arrivals.remove(flight);
        }
        return result;
    }

    public void addDeparture(Flight flight){
        if(flight != null)
             departures.add(flight);
    }
    public boolean removeDeparture(Flight flight){
        boolean result = false;
        if(flight != null)
             result = departures.remove(flight);
        return result;
    }
}
