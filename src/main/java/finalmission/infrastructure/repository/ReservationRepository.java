package finalmission.infrastructure.repository;

import finalmission.business.model.entity.Reservation;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
    List<Reservation> findReservationsByMemberId(Long memberId);
    Reservation findReservationById(Long id);
}
