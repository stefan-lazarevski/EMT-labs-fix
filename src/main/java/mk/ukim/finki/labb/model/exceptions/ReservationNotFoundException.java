package mk.ukim.finki.labb.model.exceptions;

public class ReservationNotFoundException extends RuntimeException{
    public ReservationNotFoundException(Long id) {
        super(String.format("Reservation with id: %d was not found", id));
    }

}
