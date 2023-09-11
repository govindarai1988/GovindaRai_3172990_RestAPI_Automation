package app.urls;

/**
 * Defines the endpoints for the API.
 */
public final class ApiEndpoints {
    /**
     * Base URL for the API.
     */
    public static final String BASE_URL = "https://restful-booker.herokuapp.com/";

    /**
     * Endpoint for authentication.
     */
    public static final String AUTH_ENDPOINT = "auth/";

    /**
     * Endpoint for booking-related operations.
     */
    public static final String BOOKING_ENDPOINT = "booking/";

    /**
     * Endpoint for health check (ping).
     */
    public static final String PING_ENDPOINT = "ping/";

    /**
     * Private constructor to prevent instantiation of this class.
     */
    private ApiEndpoints() {}
}
