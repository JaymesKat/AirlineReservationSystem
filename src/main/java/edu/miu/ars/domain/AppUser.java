package edu.miu.ars.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;


@Entity
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
    @OneToOne
    @JoinColumn(name="user_id")
    private User user;

    public AppUser(String email, String password, String role) {
        this.email = email;
        this.password = password;
        this.role = role;
    }
}
