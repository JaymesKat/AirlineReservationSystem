package edu.miu.ars.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.util.Date;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PassengerDTO {
    @Size(min=2)
    private String email;
    @Size(min=2)
    private String firstName;
    @Size(min=2)
    private String lastName;
    private Date dateOfBirth;
    @Valid
    private AddressDTO address;
}
