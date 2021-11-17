package edu.miu.ars.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import edu.miu.ars.domain.Airline;
import edu.miu.ars.domain.Airport;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FlightDTO {
    @Size(min = 1)
    private String number;
    @Min(1)
    private int capacity;
    @Min(1)
    private Long originAirportId;
    @Min(1)
    private Long destinationAirportId;
    @Min(1)
    private Long airlineId;
    private Date departureTime;
    private Date arrivalTime;
    private Date departureDate;
    private Date arrivalDate;
}
