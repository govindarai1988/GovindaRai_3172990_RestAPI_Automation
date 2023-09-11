/**
 * This class contains test methods for updating bookings using the Booking API.
 */
package APITests;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import app.hooks.BookingApi;
import app.configs.ExtentManager;
import app.payloads.BookingDatesPayload;
import app.payloads.BookingRequestPayload;
import app.payloads.BookingResponsePayload;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

class UpdateBookingTests extends BaseTest {

    /**
     * Generates a BookingRequestPayload with random data.
     *
     * @return BookingRequestPayload object
     */
    BookingRequestPayload createBookingRequestPayload() {
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = sdfDate.format(new Date());
        return BookingRequestPayload.builder()
                .firstName(faker.name().firstName())
                .lastName(faker.name().lastName())
                .totalPrice(faker.number().numberBetween(100, 500))
                .depositPaid(true)
                .bookingDates(
                        BookingDatesPayload.builder().checkin(currentDate).checkout(currentDate).build())
                .additionalNeeds("None")
                .build();
    }

    /**
     * Tests whether updating a booking returns a 200 status code.
     */
    @Test
    void testUpdateBookingReturns200() {
        ExtentTest test = ExtentManager.createTest("testUpdateBookingReturns200");
        String methodName = new Object() {}.getClass().getEnclosingMethod().getName();
        test.log(Status.INFO, "Executing method: " + methodName);
        try {
            BookingRequestPayload bookingRequestPayload = createBookingRequestPayload();
            int id =
                    BookingApi.createBooking(bookingRequestPayload)
                            .as(BookingResponsePayload.class)
                            .getBookingId();
            bookingRequestPayload.setTotalPrice(faker.number().numberBetween(100, 500));
            Response response = BookingApi.updateBooking(bookingRequestPayload, id, token);
            assertThat(response.statusCode(), equalTo(SC_OK));
            test.log(Status.PASS, "Test Step Passed");
        } catch (AssertionError e) {
            // Assertion failed, mark the test as failed
            test.log(Status.FAIL, "Test step failed: " + e.getMessage());
            throw e;
        } // Re-throw the assertion error to mark the overall test as failed

        logger.info(methodName + " has been executed");
    }

    /**
     * Tests whether updating a booking returns correct details.
     */
    @Test
    void testUpdateBookingReturnsCorrectDetails() {
        ExtentTest test = ExtentManager.createTest("testUpdateBookingReturnsCorrectDetails");
        String methodName = new Object() {}.getClass().getEnclosingMethod().getName();
        test.log(Status.INFO, "Executing method: " + methodName);

        try {
            BookingRequestPayload bookingRequestPayload = createBookingRequestPayload();
            int id =
                    BookingApi.createBooking(bookingRequestPayload)
                            .as(BookingResponsePayload.class)
                            .getBookingId();

            bookingRequestPayload.setFirstName(faker.name().firstName());
            bookingRequestPayload.setLastName(faker.name().lastName());
            bookingRequestPayload.setTotalPrice(faker.number().numberBetween(100, 500));
            BookingRequestPayload bookingResponsePayload =
                    BookingApi.updateBooking(bookingRequestPayload, id, token)
                            .as(BookingRequestPayload.class);
            assertThat(bookingRequestPayload.equals(bookingResponsePayload), is(true));
            test.log(Status.PASS, "Test Step Passed");
        } catch (AssertionError e) {
            // Assertion failed, mark the test as failed
            test.log(Status.FAIL, "Test step failed: " + e.getMessage());
            throw e;
        } // Re-throw the assertion error to mark the overall test as failed

        logger.info(methodName + " has been executed");
    }

    /**
     * Tests whether partial updating of a booking returns a 200 status code.
     */
    @Test
    void testPartialUpdateBookingReturns200() {
        ExtentTest test = ExtentManager.createTest("testPartialUpdateBookingReturns200");
        String methodName = new Object() {}.getClass().getEnclosingMethod().getName();
        test.log(Status.INFO, "Executing method: " + methodName);
        try {
            BookingRequestPayload bookingRequestPayload = createBookingRequestPayload();
            int id =
                    BookingApi.createBooking(bookingRequestPayload)
                            .as(BookingResponsePayload.class)
                            .getBookingId();
            bookingRequestPayload.setTotalPrice(faker.number().numberBetween(100, 500));

            Response response = BookingApi.partialUpdateBooking(bookingRequestPayload, id, token);
            assertThat(response.statusCode(), equalTo(SC_OK));
            test.log(Status.PASS, "Test Step Passed");
        } catch (AssertionError e) {
            // Assertion failed, mark the test as failed
            test.log(Status.FAIL, "Test step failed: " + e.getMessage());
            throw e;
        } // Re-throw the assertion error to mark the overall test as failed

        logger.info(methodName + " has been executed");
    }
}
