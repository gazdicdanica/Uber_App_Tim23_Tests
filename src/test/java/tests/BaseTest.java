package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class BaseTest {

    public static WebDriver webDriver;

    @BeforeSuite
    public void initDriver() {
        System.setProperty("WebDriver.chrome.driver", "./chromedriver.exe");
        webDriver = new ChromeDriver();

        webDriver.manage().window().maximize();
    }

    @AfterSuite
    public void quitDriver() {
        webDriver.quit();
    }
}
