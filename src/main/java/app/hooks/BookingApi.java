package app.hooks;

import app.configs.ExcelFileUtility;
import app.payloads.BookingRequestPayload;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static app.urls.ApiEndpoints.BASE_URL;
import static app.urls.ApiEndpoints.BOOKING_ENDPOINT;
import static io.restassured.RestAssured.given;

public final class BookingApi {

    public static ExcelFileUtility eLib = new ExcelFileUtility();

    private static final String ENDPOINT1;

    static {
        try {
            ENDPOINT1 = eLib.readDataFromExcel("API",1,0) + eLib.readDataFromExcel("API",1,2);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    private static final String ENDPOINT = BASE_URL + BOOKING_ENDPOINT;
    private static final String MEDIA_TYPE_JSON = "application/json";

    // Private constructor to prevent instantiation
    private BookingApi() {}

    /**
     * Sends a GET request to retrieve all booking IDs.
     *
     * @return The response containing all booking IDs.
     */
    public static Response getAllBookingIds() {
        return given().when().get(ENDPOINT1);
    }

    /**
     * Sends a GET request to retrieve booking IDs by first name and last name.
     *
     * @param firstName The first name of the booking.
     * @param lastName  The last name of the booking.
     * @return The response containing booking IDs matching the provided names.
     */
    public static Response getBookingIdsByName(String firstName, String lastName) {
        return given()
                .param("firstname", firstName)
                .param("lastname", lastName)
                .when()
                .get(ENDPOINT1);
    }

    /**
     * Sends a GET request to retrieve booking IDs within a date range.
     *
     * @param checkin  The check-in date for the date range.
     * @param checkout The check-out date for the date range.
     * @return The response containing booking IDs within the specified date range.
     */
    public static Response getBookingIdsByDate(String checkin, String checkout) {
        return given()
                .param("checkin", checkin)
                .param("checkout", checkout)
                .when()
                .get(ENDPOINT1);
    }

    /**
     * Sends a GET request to retrieve booking details by ID.
     *
     * @param id The ID of the booking to retrieve.
     * @return The response containing booking details for the specified ID.
     */
    public static Response getBookingById(int id) {
        return given().accept(MEDIA_TYPE_JSON).when().get(ENDPOINT1 + id);
    }

    /**
     * Sends a POST request to create a new booking.
     *
     * @param bookingRequestPayload The payload containing booking details.
     * @return The response containing the newly created booking.
     */
    public static Response createBooking(BookingRequestPayload bookingRequestPayload) {
        return given()
                .contentType(ContentType.JSON)
                .accept(MEDIA_TYPE_JSON)
                .body(bookingRequestPayload)
                .when()
                .post(ENDPOINT1);
    }

    /**
     * Sends a PUT request to update an existing booking.
     *
     * @param bookingRequestPayload The payload containing updated booking details.
     * @param id                    The ID of the booking to update.
     * @param authToken             The authentication token.
     * @return The response indicating the status of the update.
     */
    public static Response updateBooking(
            BookingRequestPayload bookingRequestPayload, int id, String authToken) {
        return given()
                .contentType(ContentType.JSON)
                .accept(MEDIA_TYPE_JSON)
                .header("Cookie", "token=" + authToken)
                .body(bookingRequestPayload)
                .when()
                .put(ENDPOINT1 + id);
    }

    /**
     * Sends a PATCH request to partially update an existing booking.
     *
     * @param bookingRequestPayload The payload containing partially updated booking details.
     * @param id                    The ID of the booking to partially update.
     * @param authToken             The authentication token.
     * @return The response indicating the status of the partial update.
     */
    public static Response partialUpdateBooking(
            BookingRequestPayload bookingRequestPayload, int id, String authToken) {
        return given()
                .contentType(ContentType.JSON)
                .accept(MEDIA_TYPE_JSON)
                .header("Cookie", "token=" + authToken)
                .body(bookingRequestPayload)
                .when()
                .patch(ENDPOINT1 + id);
    }

    /**
     * Sends a DELETE request to delete a booking.
     *
     * @param id        The ID of the booking to delete.
     * @param authToken The authentication token.
     * @return The response indicating the status of the delete operation.
     */
    public static Response deleteBooking(int id, String authToken) {
        return given().header("Cookie", "token=" + authToken).when().delete(ENDPOINT1 + id);
    }
}
