package edu.miu.ars.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Airport {
    @Id
    @GeneratedValue
    private Long id;
    private String code;
    private String name;
    @Embedded
    private Address address;

    public Airport(String code, String name, Address address) {
        this.code = code;
        this.name = name;
        this.address = address;
    }
}
