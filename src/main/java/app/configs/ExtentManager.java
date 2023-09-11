package app.configs;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentManager {

    public static ExtentReports extent;
    private static ExtentTest test;

    // Method to get the ExtentReports instance
    public static ExtentReports getInstance() {

        if (extent == null) {
            extent = new ExtentReports();
            ExtentSparkReporter htmlReporter = new ExtentSparkReporter("Reports//" + getFilename());
            extent.attachReporter(htmlReporter);

            // Customize the appearance of the HTML report
            htmlReporter.config().setDocumentTitle("API Test Report");
            htmlReporter.config().setReportName("API Automation Report");
            htmlReporter.config().setTheme(Theme.STANDARD);
            htmlReporter.config().getResourceCDN();
        }
        return extent;
    }

    // Method to create a new test and return the ExtentTest instance
    public static ExtentTest createTest(String testName) {
        test = extent.createTest(testName);
        return test;
    }

    // Method to flush the ExtentReports instance and save the report
    public static void flushReport() {
        if (extent != null) {
            extent.flush();
        }
    }

    // Method to generate a unique filename for the HTML report
    public static String getFilename() {
        String fileName = new SimpleDateFormat("'ExtentReport_'yyyyMMddHHmm'.html'").format(new Date());
        return fileName;
    }
}
