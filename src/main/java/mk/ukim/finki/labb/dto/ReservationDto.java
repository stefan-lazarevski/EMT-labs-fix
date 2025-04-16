package mk.ukim.finki.labb.dto;

import mk.ukim.finki.labb.model.domain.Reservation;
import mk.ukim.finki.labb.model.enums.ReservationStatus;

import java.time.LocalDateTime;
import java.util.List;

public record ReservationDto(
        Long id,
        LocalDateTime dateCreated,
        DisplayUserDto user,
        List<DisplayHousingDto> houses,
        ReservationStatus status
) {

    public static ReservationDto from(Reservation reservation) {
        return new ReservationDto(
                reservation.getId(),
                reservation.getDateCreated(),
                DisplayUserDto.from(reservation.getUser()),
                DisplayHousingDto.from(reservation.getHouses()),
                reservation.getStatus()
        );
    }

}
