package edu.miu.ars.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReservationDTO {
    @NotEmpty
    private List<Long> flightId;
    @Valid
    private PassengerDTO passengerDTO;
    @Min(1)
    private Long agentId;
    @Min(1)
    private Long userId;
}
