package app.payloads;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

/**
 * Represents the payload for a booking response.
 */
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class BookingResponsePayload {

    /**
     * The booking ID associated with the booking response.
     */
    @JsonProperty("bookingid")
    private int bookingId;

    /**
     * The booking request payload containing booking details.
     */
    @JsonProperty("booking")
    private BookingRequestPayload bookingRequestPayload;
}
