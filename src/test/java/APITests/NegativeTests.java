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

import static org.apache.http.HttpStatus.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * This class contains test methods for negative scenarios related to the Booking API.
 */
class NegativeTests extends BaseTest {

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
                .bookingDates(BookingDatesPayload.builder().checkin(currentDate).checkout(currentDate).build())
                .additionalNeeds("None")
                .build();
    }

    /**
     * Tests whether the API returns a non-empty array of booking IDs.
     */
    @Test
    void testGetAllBookingIdsReturnsNonEmptyArray() {
        ExtentTest test = ExtentManager.createTest("testGetAllBookingIdsReturnsNonEmptyArray");
        BookingResponsePayload[] bookingResponsePayload = BookingApi.getAllBookingIds().as(BookingResponsePayload[].class);
        String methodName = new Object() {}.getClass().getEnclosingMethod().getName();
        test.log(Status.INFO, "Executing method: " + methodName);

        try {
            assertThat(bookingResponsePayload.length, greaterThan(0));
            test.log(Status.PASS, "Test Step Passed");
        } catch (AssertionError e) {
            // Assertion failed, mark the test as failed
            test.log(Status.FAIL, "Test step failed: " + e.getMessage());
            throw e; // Re-throw the assertion error to mark the overall test as failed
        }
        logger.info(methodName + " has been executed");
    }

    /**
     * Tests whether deleting a booking with an invalid token returns a 403 status code.
     */
    @Test
    void testDeleteBookingReturns403() {
        ExtentTest test = ExtentManager.createTest("testDeleteBookingReturns403");
        String methodName = new Object() {}.getClass().getEnclosingMethod().getName();
        test.log(Status.INFO, "Executing method: " + methodName);
        try {
            int id = BookingApi.createBooking(createBookingRequestPayload())
                    .as(BookingResponsePayload.class)
                    .getBookingId();

            Response response = BookingApi.deleteBooking(id, "xyz");
            assertThat(response.statusCode(), equalTo(SC_FORBIDDEN));
            test.log(Status.PASS, "Test Step Passed");
        } catch (AssertionError e) {
            // Assertion failed, mark the test as failed
            test.log(Status.FAIL, "Test step failed: " + e.getMessage());
            throw e; // Re-throw the assertion error to mark the overall test as failed
        }
        logger.info(methodName + " has been executed");
    }

    /**
     * Tests whether deleting a booking with an invalid booking ID returns a 403 status code.
     */
    @Test
    void testDeleteBookingReturns401() {
        ExtentTest test = ExtentManager.createTest("testDeleteBookingReturns401");
        String methodName = new Object() {}.getClass().getEnclosingMethod().getName();
        test.log(Status.INFO, "Executing method: " + methodName);
        try {
            int id = BookingApi.createBooking(createBookingRequestPayload())
                    .as(BookingResponsePayload.class)
                    .getBookingId();

            Response response = BookingApi.deleteBooking(id, "21321");
            assertThat(response.statusCode(), equalTo(SC_FORBIDDEN));
            test.log(Status.PASS, "Test Step Passed");
        } catch (AssertionError e) {
            // Assertion failed, mark the test as failed
            test.log(Status.FAIL, "Test step failed: " + e.getMessage());
            throw e; // Re-throw the assertion error to mark the overall test as failed
        }
        logger.info(methodName + " has been executed");
    }

    /**
     * Tests whether updating a booking with an invalid booking ID returns a 405 status code.
     */
    @Test
    void testUpdateBookingReturns405() {
        ExtentTest test = ExtentManager.createTest("testUpdateBookingReturns405");
        String methodName = new Object() {}.getClass().getEnclosingMethod().getName();
        test.log(Status.INFO, "Executing method: " + methodName);
        try {
            BookingRequestPayload bookingRequestPayload = createBookingRequestPayload();
            bookingRequestPayload.setTotalPrice(faker.number().numberBetween(100, 500));
            Response response = BookingApi.updateBooking(bookingRequestPayload, 231123, token);
            assertThat(response.statusCode(), equalTo(405));
            test.log(Status.PASS, "Test Step Passed");
        } catch (AssertionError e) {
            // Assertion failed, mark the test as failed
            test.log(Status.FAIL, "Test step failed: " + e.getMessage());
            throw e; // Re-throw the assertion error to mark the overall test as failed
        }
        logger.info(methodName + " has been executed");
    }

    /**
     * Tests whether the token used for authentication is non-empty.
     */
    @Test
    void testCreateTokenReturnsNonEmptyToken() {

        ExtentTest test = ExtentManager.createTest("testCreateTokenReturnsNonEmptyToken");
        String methodName = new Object() {}.getClass().getEnclosingMethod().getName();
        test.log(Status.INFO, "Executing method: " + methodName);
        assertThat(token, is(not("")));
        test.log(Status.PASS, "Test Step Passed");
    }
}
