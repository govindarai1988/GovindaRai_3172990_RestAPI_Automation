package app.payloads;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

/**
 * Represents the payload for authentication response.
 */
@Getter
public class AuthResponsePayload {

    /**
     * The authentication token received in the response.
     */
    @JsonProperty
    private String token;
}
