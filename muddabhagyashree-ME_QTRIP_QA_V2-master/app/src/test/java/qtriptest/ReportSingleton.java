package qtriptest;

import com.relevantcodes.extentreports.ExtentReports;

public class ReportSingleton {

    private static ExtentReports extent;

    // Private constructor to prevent instantiation from outside the class
    private ReportSingleton() {
    }

    // Method to get the single instance of ExtentReports
    public static ExtentReports getInstance() {
        if (extent == null) {
            // Initialize ExtentReports and specify the file path
            extent = new ExtentReports(System.getProperty("user.dir") + "/test-output/ExtentReport.html", true);//app/src/test/java/qtriptest/ReportSingleton.java
        }
        return extent;
    }
}