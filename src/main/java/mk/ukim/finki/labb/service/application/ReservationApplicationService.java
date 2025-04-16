package mk.ukim.finki.labb.service.application;

import mk.ukim.finki.labb.dto.DisplayHousingDto;
import mk.ukim.finki.labb.dto.ReservationDto;

import java.util.List;
import java.util.Optional;

public interface ReservationApplicationService {

    List<DisplayHousingDto> listAllHousingsInReservations(Long reservationId);

    Optional<ReservationDto> getActiveReservations(String username);

    Optional<ReservationDto> addHousingsToReservations(String username, Long housingId);

    Optional<ReservationDto> confirmReservation(String username);

}
