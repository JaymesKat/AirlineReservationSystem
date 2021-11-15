package edu.miu.ars.service;

import edu.miu.ars.domain.Agent;
import edu.miu.ars.domain.Airline;
import edu.miu.ars.domain.Reservation;

import java.util.List;

public interface IReservation {
    Reservation addReservation(Reservation reservation);
    List<Reservation> getReservations();
    Reservation getReservation(long id);
    Reservation updateReservation(long id, Reservation reservation);
    String removeReservation(long id);
}
