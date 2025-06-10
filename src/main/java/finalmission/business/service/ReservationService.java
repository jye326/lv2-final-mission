package finalmission.business.service;

import finalmission.business.model.entity.Reservation;
import finalmission.infrastructure.repository.ReservationRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;

    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public void save(Reservation reservation) {
        reservationRepository.save(reservation);
    }

    public List<Reservation> getReservationsByMemberId(Long memberId) {
        return reservationRepository.findReservationsByMemberId(memberId);
    }

    public Reservation getReservationById(Long id) {
        return reservationRepository.findReservationById(id);
    }

    public Reservation modifyPassportId(Long reservationId, String passportId) {
        Reservation reservation = getReservationById(reservationId);
        reservation.setPassportId(passportId);
        save(reservation);
        return reservation;
    }

}
