package tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;
import pages.BaseMainPage;
import pages.DriverMainPage;
import pages.LoginPage;
import pages.PassengerMainPage;

public class FinishRideTest extends BaseTest{
    private final String EMAIL_DRIVER="pp@gmail.com";
    private final String PASSWORD_DRIVER="123";

    private static final String EMAIL_USER="test@email.com";
    private static final String PASSWORD_USER="123";

    private static final String LOGIN_PAGE_TEXT="Don't have an account? SIGN UP";

    private static PassengerMainPage passengerMainPage;
    private static DriverMainPage driverMainPage;

    private static WebDriver secondDriver;

    @BeforeAll
    public static void login() {
        BaseMainPage mainPage = new BaseMainPage(webDriver);
        mainPage.openLogin();
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.login(EMAIL_USER, PASSWORD_USER);
        passengerMainPage = new PassengerMainPage(webDriver);
    }

    @AfterEach
    public void navigate() { webDriver.get("http://localhost:4200"); }

    
}
