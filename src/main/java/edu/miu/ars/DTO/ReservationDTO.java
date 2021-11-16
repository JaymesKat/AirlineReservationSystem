package edu.miu.ars.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class ReservationDTO {
    private String departureDate;
    private List<String> flightNumbers;

    @Override
    public String toString() {
        return "ReservationDTO{" +
                "departureDate='" + departureDate + '\'' +
                ", flightNumbers=" + flightNumbers +
                '}';
    }

    public String generateCode() {
        UUID randomUUID = UUID.randomUUID();
        return randomUUID.toString().replaceAll("-", "").substring(0,6);
    }
}
