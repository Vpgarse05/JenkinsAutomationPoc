package reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.util.HashMap;
import java.util.Map;

public class ExtentManager {

    public static ExtentTest test;
    public static ExtentSparkReporter sparkHtml;
    public static final ExtentReports extentReports = new ExtentReports();
    public static ExtentReports setExtent() {

        sparkHtml = new ExtentSparkReporter("extent-report.html");
        sparkHtml.config().setReportName("CX choice Extent Report");
        extentReports.attachReporter(sparkHtml);
        extentReports.setSystemInfo("Name", "QA Automation");
        extentReports.setSystemInfo("Author", "Team Automation");
        return extentReports;
    }
    static Map<Integer, ExtentTest> extentTestMap = new HashMap<>();
    static ExtentReports            extent        = ExtentManager.setExtent();
    public static synchronized ExtentTest getTest() {
        return extentTestMap.get((int) Thread.currentThread().getId());
    }
    public static synchronized ExtentTest startTest(String testName, String desc) {
        ExtentTest test = extent.createTest(testName, desc);
        extentTestMap.put((int) Thread.currentThread().getId(), test);
        return test;
    }
    public static void endReport() {
        extent.flush();
    }
}