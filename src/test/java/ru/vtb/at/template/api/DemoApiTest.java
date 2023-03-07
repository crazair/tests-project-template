package ru.vtb.at.template.api;

import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.vtb.at.template.Props;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;

@DisplayName("API демо")
class DemoApiTest {

    private static Props props = ConfigFactory.create(Props.class);
    private RequestSpecification requestSpecification = given()
            .baseUri(props.profileUrl())
            .contentType(ContentType.JSON);

    @Test
    @DisplayName("Проверка наличия мультикарты в карточных продуктах")
    void test() {
        requestSpecification
                .log().all()
                .param("method", "nmrReportsGetInfo")
                .when()
                .get("/getInfo")
                .then()
                .statusCode(200)
                .log().all()
                .and()
                .body(containsString("MULTICARTA"));
    }
}
