package app.payloads;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.extern.jackson.Jacksonized;

/**
 * Represents the payload for booking dates.
 */
@Jacksonized
@Builder
@EqualsAndHashCode
@JsonIgnoreProperties(ignoreUnknown = true)
public class BookingDatesPayload {

    /**
     * The check-in date for the booking.
     */
    @JsonProperty
    private String checkin;

    /**
     * The check-out date for the booking.
     */
    @JsonProperty
    private String checkout;
}
