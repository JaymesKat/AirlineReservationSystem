package edu.miu.ars.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReservationDTO {
    private List<Long> flightId;
    private PassengerDTO passengerDTO;
    private Long agentId;
    private Long userId;
}
