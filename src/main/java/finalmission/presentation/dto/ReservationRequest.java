package finalmission.presentation.dto;

import finalmission.business.model.entity.Member;
import finalmission.business.model.entity.Reservation;
import java.time.LocalDateTime;

public record ReservationRequest(Member member, String passportId, LocalDateTime departureDateTime,
                                 LocalDateTime arrivalDateTime, String departures, String arrivals,
                                 String flightCode) {
    public static ReservationRequest of(Reservation reservation) {
        return new ReservationRequest(reservation.getMember(), reservation.getPassportId(), reservation.getDepartureDateTime(), reservation.getArrivalDateTime(),
                reservation.getDepartures(), reservation.getArrivals(), reservation.getFlightCode());
    }

}
