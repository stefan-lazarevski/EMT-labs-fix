package mk.ukim.finki.labb.model.exceptions;

public class HousingAlreadyInReservationException extends RuntimeException{
    public HousingAlreadyInReservationException(Long id, String username) {
        super(String.format("Housing with id: %d already exists in reservation for user with username %s", id, username));
    }

}
