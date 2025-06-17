package finalmission.business.model.entity;

import finalmission.presentation.dto.ReservationRequest;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private Member member;
    @Setter
    private String passportId;  // 여권번호

    private LocalDateTime departureDateTime;
    private LocalDateTime arrivalDateTime;

    private String departures;  // 출발지
    private String arrivals;    // 도착지

    private String flightCode;  // 항공기 편명

    public Reservation() {
    }

    public static Reservation create(Member member, String passportId, LocalDateTime departureDateTime,
                                     LocalDateTime arrivalDateTime, String departures, String arrivals,
                                     String flightCode) {
        return new Reservation(null, member, passportId, departureDateTime, arrivalDateTime, departures, arrivals,
                flightCode);
    }

    public static Reservation create(ReservationRequest request) {
        return new Reservation(null, request.member(), request.passportId(), request.departureDateTime(), request.arrivalDateTime(), request.departures(), request.arrivals(),
                request.flightCode());
    }

}
