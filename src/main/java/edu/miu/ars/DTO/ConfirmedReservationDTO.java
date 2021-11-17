package edu.miu.ars.DTO;

import edu.miu.ars.domain.ReservationState;
import lombok.Data;

import java.util.List;

@Data
public class ConfirmedReservationDTO {
    private String reservationCode;
    private ReservationState status;
    private List<TicketDTO> tickets;

    public ConfirmedReservationDTO(String reservationCode, ReservationState status, List<TicketDTO> tickets) {
        this.reservationCode = reservationCode;
        this.status = status;
        this.tickets = tickets;
    }

    @Override
    public String toString() {
        return "Reservation {" +
                "reservationCode='" + reservationCode + '\'' +
                ", status=" + status +
                ", tickets=" + tickets +
                '}';
    }
}
