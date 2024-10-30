package base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import helper.ScreenShot;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import properties.PropertiesUtils;
import properties.PropertyFileEnum;
import reports.ExtentManager;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.logging.Logger;

@Listeners
public class BasePage extends BaseTest {
    public static WebDriver driver;
    public ExtentHtmlReporter htmlReporter;
    public ExtentReports extent;
    public static ExtentTest test;
    public static String timestamp;

    @BeforeSuite
    public void dataReady() throws IOException, ParseException {
        htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/test-output/ExtentReport/MyReport1.html");

        htmlReporter.config().setDocumentTitle("Automation Test Report");
        htmlReporter.config().setReportName("Simply Secure Test Automation Report");
        htmlReporter.config().setTheme(Theme.DARK);

        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);

        extent.setSystemInfo("HostName", "MyHost");
        extent.setSystemInfo("ProjectName", "SSS");
        extent.setSystemInfo("Tester", "Murali");
        extent.setSystemInfo("OS", "Win10");
        extent.setSystemInfo("Browser", "Chrome");
        System.out.println("#################### Test Case Started ############################");
    }

    @BeforeMethod
    public void setUp() {
        String browserName = System.getProperty("browser");
        if (browserName == null) {
            browserName = "chrome";
        }
        if (browserName.equalsIgnoreCase("Firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
            driver.navigate().to(PropertiesUtils.getProperty(PropertyFileEnum.GLOB, "url"));
        } else if (browserName.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            driver.navigate().to(PropertiesUtils.getProperty(PropertyFileEnum.GLOB, "url"));
        } else {
            System.out.println(browserName + " Browser Not Supported");
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    @AfterMethod
    public void teardown(ITestResult result) throws IOException {
        timestamp = getTimestamp();

        if (result.getStatus() == ITestResult.FAILURE) {
            test.log(Status.FAIL, MarkupHelper.createLabel("Test Failed: " + result.getName(), ExtentColor.RED));
            test.log(Status.FAIL, "Failure Reason: " + result.getThrowable());

            // Capture screenshot as Base64 and attach it
            String base64Screenshot = ScreenShot.captureScreenshot(driver);
            test.addScreenCaptureFromBase64String(base64Screenshot, "Failure Screenshot at " + timestamp);
            test.log(Status.INFO, "Failure Screenshot captured at: " + timestamp);

        } else if (result.getStatus() == ITestResult.SUCCESS) {
            test.log(Status.PASS, MarkupHelper.createLabel("Test Passed: " + result.getName(), ExtentColor.GREEN));
            test.log(Status.INFO, "Test completed successfully at: " + timestamp);
            // Capture screenshot as Base64 and attach it
            String base64Screenshot = ScreenShot.captureScreenshot(driver);
            test.addScreenCaptureFromBase64String(base64Screenshot, "Passes Screenshot at " + timestamp);
            test.log(Status.INFO, "Passed Screenshot captured at: " + timestamp);

        } else if (result.getStatus() == ITestResult.SKIP) {
            test.log(Status.SKIP, MarkupHelper.createLabel("Test Skipped: " + result.getName(), ExtentColor.ORANGE));
        }
        extent.flush();
        driver.quit();
        ExtentManager.endReport();
    }

    public String getTimestamp() {
        return new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
    }

    public static WebDriver getWebDriver() {
        return driver;
    }

    public static void logScreenshot(String message, String imageBase64) throws IOException {
        if (test != null) {
            test.info(message, MediaEntityBuilder.createScreenCaptureFromBase64String(imageBase64).build());
        }
    }
    public static String getScreenshot() {
        TakesScreenshot screenshotTaker = (TakesScreenshot) driver;
        String base64Screenshot = screenshotTaker.getScreenshotAs(OutputType.BASE64);
        return base64Screenshot;
    }
}
