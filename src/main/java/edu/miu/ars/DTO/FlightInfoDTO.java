package edu.miu.ars.DTO;

import edu.miu.ars.domain.FlightInfo;
import lombok.Data;

import javax.persistence.SecondaryTable;
import java.util.Date;

@Data
public class FlightInfoDTO {
    FlightDTO flightDetails;
    private Date departureDate;

    public FlightInfoDTO(FlightInfo flightInfo) {
        this.departureDate = flightInfo.getDepartureDate();
        this.flightDetails = new FlightDTO(flightInfo.getFlight());
    }
}
