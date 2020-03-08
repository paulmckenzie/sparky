
package sparky;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

class AppIntegrationTest {
    private final String name = "Derek";

    @Test
    void appHasAGreeting() {
        given()
                .port(7000)
                .param("name", name)
                .when()
                .get("/hello")
                .then()
                .statusCode(200)
                .body("greeting", equalTo("hello"))
                .body("name", equalTo(name));
    }
}
