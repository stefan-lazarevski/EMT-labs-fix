package mk.ukim.finki.labb.service.application.impl;

import mk.ukim.finki.labb.dto.DisplayHousingDto;
import mk.ukim.finki.labb.dto.ReservationDto;
import mk.ukim.finki.labb.model.domain.Reservation;
import mk.ukim.finki.labb.service.application.ReservationApplicationService;
import mk.ukim.finki.labb.service.domain.ReservationService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationApplicationServiceImpl implements ReservationApplicationService {

    private final ReservationService reservationService;

    public ReservationApplicationServiceImpl(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @Override
    public List<DisplayHousingDto> listAllHousingsInReservations(Long reservationId) {
        return DisplayHousingDto.from(reservationService.listAllHousingsInReservations(reservationId));
    }

    @Override
    public Optional<ReservationDto> getActiveReservations(String username) {
        return reservationService.getActiveReservations(username).map(ReservationDto::from);
    }

    @Override
    public Optional<ReservationDto> addHousingsToReservations(String username, Long housingId) {
        return reservationService.addHousingsToReservations(username, housingId).map(ReservationDto::from);
    }

    @Override
    public Optional<ReservationDto> confirmReservation(String username) {
        return reservationService.confirmReservation(username).map(ReservationDto::from);
    }
}
