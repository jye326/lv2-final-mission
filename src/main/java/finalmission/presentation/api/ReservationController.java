package finalmission.presentation.api;

import finalmission.auth.AuthRequired;
import finalmission.business.model.entity.Reservation;
import finalmission.business.service.ReservationService;
import finalmission.presentation.dto.ReservationRequest;
import java.net.URI;
import java.util.List;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
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
    public ResponseEntity<List<Reservation>> getReservationsByMemberId(Long memberId) {
        List<Reservation> reservations = reservationService.getReservationsByMemberId(memberId);
        return new ResponseEntity<>(reservations, HttpStatus.OK);
    }

    @AuthRequired
    @PostMapping("/reservation/{id}")
    public ResponseEntity<Reservation> modifyReservation(@PathVariable Long id, @RequestBody String passportId) {
        return new ResponseEntity<>(reservationService.modifyPassportId(id, passportId), HttpStatus.OK);
    }

    @AuthRequired
    @PostMapping("/reservations")
    public ResponseEntity<Reservation> createReservation(@RequestBody ReservationRequest request){
        Reservation reservation =  reservationService.save(request);
        return ResponseEntity.created(URI.create("/reservations")).body(reservation);
    }

}
