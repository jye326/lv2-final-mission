package finalmission.presentation.api;

import finalmission.auth.AuthRequired;
import finalmission.business.model.entity.Reservation;
import finalmission.business.service.ReservationService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReservationController {
    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @AuthRequired
    @GetMapping("/reservations")
    public ResponseEntity<List<Reservation>> getReservationsByMemberId(@RequestParam Long memberId) {
        List<Reservation> reservations = reservationService.getReservationsByMemberId(memberId);
        return new ResponseEntity<>(reservations, HttpStatus.OK);
    }

    @AuthRequired
    @PostMapping("/reservation/{id}")
    public ResponseEntity<Reservation> modifyReservation(@PathVariable Long id, @RequestBody String passportId) {
        return new ResponseEntity<>(reservationService.modifyPassportId(id, passportId), HttpStatus.OK);
    }

}
