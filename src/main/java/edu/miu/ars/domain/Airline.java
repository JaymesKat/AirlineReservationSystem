package edu.miu.ars.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SecondaryTable;

@Entity
@NoArgsConstructor
@SecondaryTable(name="history")
public class Airline {
    @Id
    @GeneratedValue
    private Long id;
    private String code;
    private String name;
    @Column(table = "history")
    private String history;

    public Airline(String code, String name, String history) {
        this.code = code;
        this.name = name;
        this.history = history;
    }
}
