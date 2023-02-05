package helper;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

public class Helper {
    public static void takeScreenShot(WebDriver driver, String fname) {
//        TakesScreenshot screenshot = (TakesScreenshot) driver;

        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screenshot, new File("./ScreenShots/" + fname + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
