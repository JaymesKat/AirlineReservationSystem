package edu.miu.ars.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Size;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Login {
    @Size(min=2)
    private String email;
    @Size(min=2)
    private String password;
}
