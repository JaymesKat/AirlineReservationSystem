package edu.miu.ars.service.impl;

import edu.miu.ars.DTO.TicketDTO;
import edu.miu.ars.domain.*;
import edu.miu.ars.repository.FlightInfoRepository;
import edu.miu.ars.service.FlightInfoService;
import edu.miu.ars.util.DateUtil;
import edu.miu.ars.util.TicketUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class FlightInfoServiceImpl implements FlightInfoService {

    private FlightInfoRepository flightInfoRepository;

    @Autowired
    public FlightInfoServiceImpl(FlightInfoRepository flightInfoRepository) {
        this.flightInfoRepository = flightInfoRepository;
    }
    @Override
    public FlightInfo save(FlightInfo flightInfo) {
        return null != flightInfo ? flightInfoRepository.save(flightInfo) : null;
    }

    @Override
    public List<FlightInfo> findAll() {
        return flightInfoRepository.findAll();
    }

    @Override
    public FlightInfo findById(Long id) {
        return flightInfoRepository.findById(id).orElse(null);
    }

    @Override
    public boolean update(FlightInfo flightInfo, Long id) {
        FlightInfo updateFlight = findById(id);
        if(updateFlight != null){
            updateFlight.setDepartureDate(flightInfo.getDepartureDate());
            updateFlight.setFlight(flightInfo.getFlight());
            updateFlight.setTicket(flightInfo.getTicket());
            save(updateFlight);
        }
        return false;
    }

    @Override
    public boolean deleteById(Long id) {
        FlightInfo flightInfo = findById(id);
        if (null != flightInfo) {
            flightInfoRepository.delete(flightInfo);
            return true;
        }
        return false;
    }

    @Override
    public FlightInfo createFromFlight(Flight flight, String departureDateStr) {
        Date departureDate = DateUtil.parseDate(departureDateStr);
        FlightInfo flightInfo = new FlightInfo(flight, departureDate);
        return flightInfo;
    }

    @Override
    public List<TicketDTO> generateTickets(Reservation reservation) {

        List<FlightInfo> flightInfoList = reservation.getFlightInfos();

        List<Ticket> tickets = flightInfoList.stream().map(flightInfo -> {
            Ticket ticket = new Ticket(TicketUtil.generateNumber(), flightInfo.getDepartureDate());
            flightInfo.setTicket(ticket);
            ticket.setFlightInfo(flightInfo);
            save(flightInfo);
            return ticket;
        }).collect(Collectors.toList());

        return tickets.stream().map(TicketDTO::new).collect(Collectors.toList());
    }
}
