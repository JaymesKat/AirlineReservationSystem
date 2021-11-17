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
public class AddressDTO {
    @Size(min=2)
    private String street;
    @Size(min=2)
    private String city;
    @Size(min=2)
    private String zip;
    @Size(min=2)
    private String state;
}
