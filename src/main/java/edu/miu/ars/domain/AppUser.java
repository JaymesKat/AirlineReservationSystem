package edu.miu.ars.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import java.time.LocalDate;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Setter
@Getter
@NoArgsConstructor
public class AppUser {
    @Id
    @GeneratedValue
    private Long id;
    private String email;
    private String password;
    private String role;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    @Embedded
    private Address address;

    public AppUser(String email, String password, String role, String firstName, String lastName, LocalDate dateOfBirth, Address address) {
        this.email = email;
        this.password = password;
        this.role = role;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
    }
}
