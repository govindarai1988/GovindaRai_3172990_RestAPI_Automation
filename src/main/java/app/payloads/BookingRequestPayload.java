package app.payloads;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;

/**
 * Represents the payload for a booking request.
 */
@Jacksonized
@Builder
@Setter
@EqualsAndHashCode
@JsonIgnoreProperties(ignoreUnknown = true)
public class BookingRequestPayload {

    /**
     * The first name of the guest making the booking.
     */
    @JsonProperty("firstname")
    private String firstName;

    /**
     * The last name of the guest making the booking.
     */
    @JsonProperty("lastname")
    private String lastName;

    /**
     * The total price of the booking.
     */
    @JsonProperty("totalprice")
    private int totalPrice;

    /**
     * Indicates whether a deposit has been paid for the booking.
     */
    @JsonProperty("depositpaid")
    private boolean depositPaid;

    /**
     * The booking dates including check-in and check-out dates.
     */
    @JsonProperty("bookingdates")
    private BookingDatesPayload bookingDates;

    /**
     * Any additional needs or special requests for the booking.
     */
    @JsonProperty("additionalneeds")
    private String additionalNeeds;
}
