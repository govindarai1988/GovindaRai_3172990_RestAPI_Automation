/**
 * This class contains test methods for deleting bookings using the Booking API.
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

import static org.apache.http.HttpStatus.SC_CREATED;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

class DeleteBookingTests extends BaseTest {

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
     * Tests whether deleting a booking returns a 201 status code.
     */
    @Test
    void testDeleteBookingReturns201() {
        ExtentTest test = ExtentManager.createTest("testDeleteBookingReturns201");
        String methodName = new Object() {}.getClass().getEnclosingMethod().getName();
        test.log(Status.INFO, "Executing method: " + methodName);
        try {
            int id =
                    BookingApi.createBooking(createBookingRequestPayload())
                            .as(BookingResponsePayload.class)
                            .getBookingId();

            Response response = BookingApi.deleteBooking(id, token);
            assertThat(response.statusCode(), equalTo(SC_CREATED));
            test.log(Status.PASS, "Test Step Passed");
        } catch (AssertionError e) {
            // Assertion failed, mark the test as failed
            test.log(Status.FAIL, "Test step failed: " + e.getMessage());
            throw e; // Re-throw the assertion error to mark the overall test as failed
        }
        logger.info(methodName + " has been executed");
    }
}
