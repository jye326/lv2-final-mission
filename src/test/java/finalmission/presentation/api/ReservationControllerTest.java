package finalmission.presentation.api;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;

import finalmission.auth.AuthRequired;
import finalmission.auth.AuthToken;
import finalmission.auth.jwt.JJWTJwtUtil;
import finalmission.auth.jwt.JwtUtil;
import finalmission.business.model.entity.Member;
import finalmission.business.model.entity.Reservation;
import finalmission.business.service.ReservationService;
import finalmission.infrastructure.repository.ReservationRepository;
import finalmission.presentation.dto.ReservationRequest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import java.time.LocalDateTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class ReservationControllerTest {
    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private ReservationService reservationService;
    @Autowired
    JJWTJwtUtil jjwtJwtUtil;

    private final String TOKEN_NAME = "auth_token";
    private AuthToken authToken;
    private Member ddiyong;

    void dataPost() {
        ddiyong = Member.create(1L,"띠용", "ddiyong@gmail.com", "1234");
        authToken = jjwtJwtUtil.createToken(ddiyong);

        String passportId1 = "DDI1YONG2";
        LocalDateTime tomorrow = LocalDateTime.now().plusDays(1);
        String incheon = "인천국제공항";
        String osaka = "간사이국제공항";
        String flightCode = "BHANG123";

        ReservationRequest request = new ReservationRequest(ddiyong, passportId1,
                tomorrow, tomorrow.plusHours(2), incheon, osaka,
                flightCode);
        ReservationRequest request2 = new ReservationRequest(ddiyong, passportId1,
                tomorrow.plusHours(2), tomorrow.plusHours(4), osaka, incheon,
                flightCode);

        RestAssured.given().log().all()
                .cookie(TOKEN_NAME, authToken.value())
                .body(request)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .post("/reservations");
    }

    @Test
    @DisplayName("내 예약 조회 테스트 - RestAssured")
    void test1() {
        // given
        dataPost();
        // when & then
        RestAssured.given().log().all()
                .cookie(TOKEN_NAME, authToken.value())
                .contentType(ContentType.JSON)
                .when()
                .get("/reservations")
                .then().log().all()
                .assertThat()
                .statusCode(200);
    }
}
