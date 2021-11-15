package edu.miu.ars.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@SecondaryTable(name="history")
@Setter
@Getter
public class Airline {
    @Id
    @GeneratedValue
    private Long id;
    @Column(length=2,nullable = false) // Add size validation
    private String code;
    private String name;
    @Column(table = "history", length = 2000)
    private String history;
    @OneToMany(mappedBy="airline")
    @OrderBy("departureTime desc")
    private List<Flight> flights;

    public Airline(String code, String name, String history) {
        this.code = code;
        this.name = name;
        this.history = history;
    }
}
