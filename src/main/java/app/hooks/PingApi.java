package app.hooks;

import io.restassured.response.Response;

import static app.urls.ApiEndpoints.BASE_URL;
import static app.urls.ApiEndpoints.PING_ENDPOINT;
import static io.restassured.RestAssured.given;

public final class PingApi {
    private PingApi() {}

    /**
     * Sends a GET request to perform a health check on the API.
     *
     * @return The response indicating the health status of the API.
     */
    public static Response healthCheck() {
        return given().when().get(BASE_URL + PING_ENDPOINT);
    }
}
