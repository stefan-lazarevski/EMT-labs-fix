package mk.ukim.finki.labb.service.domain;


import mk.ukim.finki.labb.model.domain.Housing;
import mk.ukim.finki.labb.model.domain.Reservation;

import java.util.List;
import java.util.Optional;

public interface ReservationService {

    List<Housing> listAllHousingsInReservations(Long reservationId);

    Optional<Reservation> getActiveReservations(String username);

    Optional<Reservation> addHousingsToReservations(String username, Long housingId);

    Optional<Reservation> confirmReservation(String username);

}
