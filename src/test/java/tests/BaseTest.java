package tests;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BaseTest {

    public static WebDriver webDriver;

    @BeforeAll
    public static void initDriver() {
        System.err.println("init");
        System.setProperty("WebDriver.chrome.driver", "./chromedriver.exe");
        webDriver = new ChromeDriver();

        webDriver.manage().window().maximize();
    }

    @AfterAll
    public static void quitDriver() {
        webDriver.quit();
    }
}
