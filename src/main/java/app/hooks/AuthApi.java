package app.hooks;

import app.payloads.AuthRequestPayload;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static app.urls.ApiEndpoints.AUTH_ENDPOINT;
import static app.urls.ApiEndpoints.BASE_URL;
import static io.restassured.RestAssured.given;

public final class AuthApi {
    // Private constructor to prevent instantiation
    private AuthApi() {}

    /**
     * Sends a POST request to create a token for authentication.
     *
     * @param authRequestPayload The payload containing authentication credentials.
     * @return The response containing the token.
     */
    public static Response createToken(AuthRequestPayload authRequestPayload) {
        return given()
                .contentType(ContentType.JSON)
                .body(authRequestPayload)
                .when()
                .post(BASE_URL + AUTH_ENDPOINT);
    }
}
