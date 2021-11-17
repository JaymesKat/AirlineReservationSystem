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
import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FlightDTO {
    private String number;
    private int capacity;
    private Long originAirportId;
    private Long destinationAirportId;
    private Long airlineId;
    private Date departureTime;
    private Date arrivalTime;
    private Date departureDate;
    private Date arrivalDate;
}
