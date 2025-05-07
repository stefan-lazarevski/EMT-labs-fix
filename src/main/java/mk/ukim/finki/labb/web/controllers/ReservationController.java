package mk.ukim.finki.labb.web.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.servlet.http.HttpServletRequest;
import mk.ukim.finki.labb.dto.ReservationDto;
import mk.ukim.finki.labb.model.domain.User;
import mk.ukim.finki.labb.service.application.ReservationApplicationService;
import org.springframework.security.core.Authentication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    private final ReservationApplicationService reservationApplicationService;

    public ReservationController(ReservationApplicationService reservationApplicationService) {
        this.reservationApplicationService = reservationApplicationService;
    }

    @Operation(
            summary = "Get active reservations",
            description = "Retrieves the active reservations for the logged-in user"
    )
    @ApiResponses(
            value = {@ApiResponse(
                    responseCode = "200",
                    description = "Reservation retrieved successfully"
            ), @ApiResponse(responseCode = "404", description = "Reservation not found")}
    )
    @GetMapping
    public ResponseEntity<ReservationDto> getActiveReservations(HttpServletRequest req) {
        String username = req.getRemoteUser();
        return reservationApplicationService.getActiveReservations(username)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Add housing to reservation",
            description = "Adds a housing to reservation for the logged-in user"
    )
    @ApiResponses(
            value = {@ApiResponse(
                    responseCode = "200", description = "Housing added to reservation successfully"
            ), @ApiResponse(
                    responseCode = "400",
                    description = "Invalid request"
            ), @ApiResponse(responseCode = "404", description = "Housing not found")}
    )
    @PostMapping("/add-housings/{id}")
    public ResponseEntity<ReservationDto> addHousingsToReservations(
            @PathVariable Long id,
            Authentication authentication
    ) {
        try {
            User user = (User) authentication.getPrincipal();
            return reservationApplicationService.addHousingsToReservations(user.getUsername(), id)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (RuntimeException exception) {
            return ResponseEntity.badRequest().build();
        }
    }


    @Operation(
            summary = "Confirm reservation",
            description = "Confirms the current user's reservation and marks all housings as rented"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reservation confirmed successfully"),
            @ApiResponse(responseCode = "400", description = "One or more housings are already rented"),
            @ApiResponse(responseCode = "404", description = "No active reservation found")
    })
    @PostMapping("/confirm")
    public ResponseEntity<ReservationDto> confirmReservation(Authentication authentication) {
        try {
            User user = (User) authentication.getPrincipal();
            return reservationApplicationService.confirmReservation(user.getUsername())
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(null); // You could also send back the message
        }
    }


}
