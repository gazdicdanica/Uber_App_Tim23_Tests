package tests;

import helper.Helper;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.*;

import java.sql.Driver;

@TestMethodOrder(MethodOrderer.MethodName.class)
public class OrderRideTest extends BaseTest{

    private static String EMAIL="test@email.com";
    private static String PASSWORD="123";

    private static String EMAIL_DRIVER = "pp@gmail.com";

    private static WebDriver driver;

    private static PassengerMainPage passengerMainPage;

    @BeforeAll
    public static void login(){
        BaseMainPage mainPage = new BaseMainPage(webDriver);
        mainPage.openLogin();
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.login(EMAIL, PASSWORD);
        passengerMainPage = new PassengerMainPage(webDriver);
    }

    @AfterEach
    public void navigate(){
        webDriver.get("http://localhost:4200");
    }

    @Test
    public void ztestSuccessfulRideOrder(){
        loginDriver();
        passengerMainPage.submitValidLocations();
        PassengerRideInfoPage rideInfoPage = new PassengerRideInfoPage(webDriver);
        Assertions.assertTrue(rideInfoPage.isPageLoaded());
        rideInfoPage.submitExistingEmail();
        rideInfoPage.checkBabies();
        rideInfoPage.submitProperVehicleType();
        rideInfoPage.waitForDialog();

        driver.quit();

    }

    @Test
    public void ztestUnsuccessfulRideOrder(){
        passengerMainPage.submitValidLocations();
        PassengerRideInfoPage rideInfoPage = new PassengerRideInfoPage(webDriver);
        Assertions.assertTrue(rideInfoPage.isPageLoaded());
        rideInfoPage.submitExistingEmail();
        rideInfoPage.checkBabies();
        rideInfoPage.submitProperVehicleType();
        rideInfoPage.waitForAlert();

    }

    @Test
    public void testEmptyLocationsRideOrder(){
        passengerMainPage.submitEmptyLocations();
    }

    @Test
    public void testInvalidLocationsRideOrder(){
        passengerMainPage.submitInvalidLocations();
    }

    @Test
    public void testNoVehicleType(){
        passengerMainPage.submitValidLocations();
        PassengerRideInfoPage rideInfoPage = new PassengerRideInfoPage(webDriver);
        Assertions.assertTrue(rideInfoPage.isPageLoaded());
        rideInfoPage.submitNoVehicleType();

    }

    @Test
    public void testNoDriversOnline(){
        passengerMainPage.submitValidLocations();
        PassengerRideInfoPage rideInfoPage = new PassengerRideInfoPage(webDriver);
        Assertions.assertTrue(rideInfoPage.isPageLoaded());
        rideInfoPage.submitForNoOnlineDriver();

    }

    private void loginDriver(){
        driver = new ChromeDriver();
        BaseMainPage mainPage = new BaseMainPage(driver);
        mainPage.openLogin();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(EMAIL_DRIVER, PASSWORD);
        DriverMainPage driverMainPage = new DriverMainPage(driver);
        driverMainPage.startShift();

    }

    private void logoutDriver(){
        DriverMainPage driverMainPage = new DriverMainPage(driver);
        driverMainPage.startShift();
        driverMainPage.logout();
    }

    @Test
    public void testNoProperDrivers(){
        loginDriver();
        passengerMainPage.submitValidLocations();
        PassengerRideInfoPage rideInfoPage = new PassengerRideInfoPage(webDriver);
        Assertions.assertTrue(rideInfoPage.isPageLoaded());
        rideInfoPage.submitWithNoProperDriver();

        logoutDriver();
        driver.quit();

    }

    @Test
    public void testAddFriendUnsuccess(){
        passengerMainPage.submitValidLocations();
        PassengerRideInfoPage rideInfoPage = new PassengerRideInfoPage(webDriver);
        Assertions.assertTrue(rideInfoPage.isPageLoaded());
        rideInfoPage.submitNonExistingEmail();

    }




}
