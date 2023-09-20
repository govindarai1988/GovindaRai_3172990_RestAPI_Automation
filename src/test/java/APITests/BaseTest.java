/**
 * This is the base class for API test cases in the APITests package.
 * It provides common setup and teardown logic for all test cases.
 */
package APITests;

import app.configs.ExcelFileUtility;
import app.hooks.AuthApi;
import app.hooks.PingApi;
import app.configs.ExtentManager;
import app.payloads.AuthRequestPayload;
import app.payloads.AuthResponsePayload;
import io.restassured.response.Response;
import net.datafaker.Faker;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static org.apache.http.HttpStatus.SC_CREATED;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BaseTest {
    protected final Faker faker = new Faker();
    protected String token;
    public static final Logger logger = LogManager.getLogger(BaseTest.class);

    public ExcelFileUtility eLib = new ExcelFileUtility();

    /**
     * Executed once before all test cases in the class.
     * Checks if the API health check returns a 201 status code.
     */
    @BeforeAll
    void testHealthCheckReturns201() {
        ExtentManager.getInstance();
        Response response = PingApi.healthCheck();
        assertThat(response.statusCode(), equalTo(SC_CREATED));
        logger.info("Test case execution has started");
    }

    /**
     * Reads a property from the configuration file.
     *
     * @param key The key to look up in the configuration file.
     * @return The value associated with the given key.
     */
    public static String readProperty(String key) {
        Properties properties = new Properties();
        String filePath = System.getProperty("user.dir") + "\\src\\main\\resources\\config.properties";
        try (FileInputStream fis = new FileInputStream(filePath)) {
            properties.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties.getProperty(key);
    }

    /**
     * Executed before each test case.
     * Retrieves an authentication token and stores it for use in the test cases.
     * Verifies that creating the token returns a 200 status code.
     */
    @BeforeEach
    void testCreateTokenReturns200() {
        AuthRequestPayload authRequestPayload =
                AuthRequestPayload.builder()
                        .username(readProperty("username"))
                        .password(readProperty("password"))
                        .build();
        Response response = AuthApi.createToken(authRequestPayload);
        token = response.as(AuthResponsePayload.class).getToken();
        assertThat(response.statusCode(), equalTo(SC_OK));
    }

    /**
     * Executed after all test cases in the class.
     * Flushes the Extent report and logs completion of all test executions.
     */
    @AfterAll
    void afterAll() {
        ExtentManager.flushReport();
        logger.info("All test cases have been executed.");
    }
}
