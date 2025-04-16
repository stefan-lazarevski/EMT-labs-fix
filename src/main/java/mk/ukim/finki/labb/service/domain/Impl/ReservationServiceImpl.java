package mk.ukim.finki.labb.service.domain.Impl;

import mk.ukim.finki.labb.model.domain.Housing;
import mk.ukim.finki.labb.model.domain.Reservation;
import mk.ukim.finki.labb.model.domain.User;
import mk.ukim.finki.labb.model.enums.ReservationStatus;
import mk.ukim.finki.labb.model.exceptions.HousingAlreadyInReservationException;
import mk.ukim.finki.labb.model.exceptions.InvalidArgumentsException;
import mk.ukim.finki.labb.model.exceptions.ReservationNotFoundException;
import mk.ukim.finki.labb.repository.ReservationRepository;
import mk.ukim.finki.labb.service.domain.HousingService;
import mk.ukim.finki.labb.service.domain.ReservationService;
import mk.ukim.finki.labb.service.domain.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final UserService userService;
    private final HousingService housingService;

    public ReservationServiceImpl(ReservationRepository reservationRepository, UserService userService, HousingService housingService) {
        this.reservationRepository = reservationRepository;
        this.userService = userService;
        this.housingService = housingService;
    }

    @Override
    public List<Housing> listAllHousingsInReservations(Long reservationId) {
        if (reservationRepository.findById(reservationId).isEmpty())
            throw new InvalidArgumentsException();
        return reservationRepository.findById(reservationId).get().getHouses();

    }

    @Override
    public Optional<Reservation> getActiveReservations(String username) {
        User user = userService.findByUsername(username);

        return Optional.of(reservationRepository.findByUserAndStatus(
                user,
                ReservationStatus.AVAILABLE
        ).orElseGet(() -> reservationRepository.save(new Reservation(user))));

    }

    @Override
    public Optional<Reservation> addHousingsToReservations(String username, Long housingId) {
        if (getActiveReservations(username).isPresent()) {
            Reservation reservation = getActiveReservations(username).get();

            Housing housing = housingService.findById(housingId)
                    .orElseThrow(() -> new ReservationNotFoundException(housingId));
            if (!reservation.getHouses().stream().filter(i -> i.getId().equals(housingId)).toList().isEmpty())
                throw new HousingAlreadyInReservationException(housingId, username);
            reservation.getHouses().add(housing);
            return Optional.of(reservationRepository.save(reservation));
        }
        return Optional.empty();

    }

    @Override
    public Optional<Reservation> confirmReservation(String username) {
        Reservation reservation = getActiveReservations(username)
                .orElseThrow(() -> new IllegalStateException("No active reservation found"));

        boolean hasRentedHousing = reservation.getHouses().stream()
                .anyMatch(h -> reservationRepository.existsByHousesContainsAndStatus(h, ReservationStatus.RENTED));

        if (hasRentedHousing) {
            throw new IllegalStateException("One or more housings are already rented");
        }

        reservation.setStatus(ReservationStatus.RENTED);
        return Optional.of(reservationRepository.save(reservation));
    }


}
