package edu.miu.ars.DTO;

import edu.miu.ars.domain.FlightInfo;
import edu.miu.ars.domain.Ticket;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class TicketDTO {
    private String ticketNumber;
    private Date departureDate;
    private FlightInfoDTO flightInfo;

    public TicketDTO(Ticket ticket) {
        this.ticketNumber = ticket.getNumber();
        this.departureDate = ticket.getFlightDate();
        this.flightInfo = new FlightInfoDTO(ticket.getFlightInfo());
    }
}
