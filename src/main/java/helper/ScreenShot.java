package helper;

import base.BasePage;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenShot {
    public static String captureScreenshot(WebDriver driver) {
            return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);

    }

}
