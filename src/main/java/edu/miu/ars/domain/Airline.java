package edu.miu.ars.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
@SecondaryTable(name="history")
public class Airline {
    @Id
    @GeneratedValue
    private Long id;
    @Column(length=2,nullable = false) // Add size validation
    private String code;
    private String name;
    @Column(table = "history", length = 2000)
    private String history;
    @OneToMany(mappedBy="airline",cascade = CascadeType.ALL)
    @OrderBy("departureTime desc")
    private List<Flight> flights;

    public Airline(String code, String name, String history) {
        this.code = code;
        this.name = name;
        this.history = history;
        flights = new ArrayList<>();
    }


    public void addFlight(Flight flight){
        flights.add(flight);
    }
    public boolean removeFlight(Flight flight){
        return flights.remove(flight);
    }
}
