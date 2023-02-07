package tests;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.*;

public class FinishRideTest extends BaseTest{
    private static final String EMAIL_DRIVER="pp@gmail.com";
    private static final String PASSWORD_DRIVER="123";

    private static final String EMAIL_USER="test@email.com";
    private static final String PASSWORD_USER="123";

    private static final String LOGIN_PAGE_TEXT="Don't have an account? SIGN UP";

    private static PassengerMainPage passengerMainPage;
    private static DriverMainPage driverMainPage;

    private static WebDriver secondDriver;

    @BeforeAll
    public static void setUp() {
        // Login Driver And Start Shift
        secondDriver = new ChromeDriver();
        BaseMainPage mainPageDriver = new BaseMainPage(secondDriver);
        mainPageDriver.openLogin();
        LoginPage loginPageDriver = new LoginPage(secondDriver);
        loginPageDriver.login(EMAIL_DRIVER, PASSWORD_DRIVER);
        driverMainPage = new DriverMainPage(secondDriver);
        driverMainPage.startShift();

        // Login User
        BaseMainPage mainPageUser = new BaseMainPage(webDriver);
        mainPageUser.openLogin();
        LoginPage loginPageUser = new LoginPage(webDriver);
        loginPageUser.login(EMAIL_USER, PASSWORD_USER);
        passengerMainPage = new PassengerMainPage(webDriver);

    }

    @BeforeEach
    public void setEnvironment() {
        // Create Ride
        passengerMainPage.createValidRide();
        PassengerRideInfoPage rideInfoPage = new PassengerRideInfoPage(webDriver);
        rideInfoPage.createValidRide();
    }

    @Test
    public void finishRideNaturalFlow() {
        driverMainPage.acceptRide();
        DriverInRidePage inRidePage = new DriverInRidePage(secondDriver);
        inRidePage.startRide();
        inRidePage.finishRide();
        Assertions.assertTrue(driverMainPage.isRideFinished());
    }

    @Test
    public void panicRideAfterStarting() {
        driverMainPage.acceptRide();
        DriverInRidePage inRidePage = new DriverInRidePage(secondDriver);
        inRidePage.startRide();
        inRidePage.panicRide();
        Assertions.assertTrue(driverMainPage.isPanic());
        driverMainPage.dismissPanicPopUp();
    }

    @Test
    public void cancelRide() {
        driverMainPage.cancelRide();
        Assertions.assertTrue(driverMainPage.isRideFinished());
    }

    @Test
    public void panicRideBeforeStarting() {
        driverMainPage.acceptRide();
        DriverInRidePage inRidePage = new DriverInRidePage(secondDriver);
        inRidePage.panicRide();
        Assertions.assertTrue(driverMainPage.isPanic());
        driverMainPage.dismissPanicPopUp();
    }

    @AfterEach
    public void navigate() { webDriver.get("http://localhost:4200"); }

    @AfterAll
    public static void cleanUp() {
        passengerMainPage.logout();
        secondDriver.quit();
    }
    
}
