package mk.ukim.finki.labb.repository;

import mk.ukim.finki.labb.model.domain.Housing;
import mk.ukim.finki.labb.model.domain.Reservation;
import mk.ukim.finki.labb.model.domain.User;
import mk.ukim.finki.labb.model.enums.ReservationStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    Optional<Reservation> findByUserAndStatus(User user, ReservationStatus status);

    boolean existsByHousesContainsAndStatus(Housing housing, ReservationStatus status);


}
