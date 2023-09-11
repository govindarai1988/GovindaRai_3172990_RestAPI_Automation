package app.payloads;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.extern.jackson.Jacksonized;

/**
 * Represents the payload for authentication request.
 */
@Jacksonized
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class AuthRequestPayload {

    /**
     * The username for authentication.
     */
    @JsonProperty
    private String username;

    /**
     * The password for authentication.
     */
    @JsonProperty
    private String password;
}
