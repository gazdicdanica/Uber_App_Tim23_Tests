package tests;

import helper.Helper;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.*;

import java.sql.Driver;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
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

    @Order(2)
    @Test
    public void testSuccessfulRideOrder(){
        loginDriver();
        passengerMainPage.submitValidLocations();
        PassengerRideInfoPage rideInfoPage = new PassengerRideInfoPage(webDriver);
        Assertions.assertTrue(rideInfoPage.isPageLoaded());
        rideInfoPage.submitExistingEmail();
        rideInfoPage.checkBabies();
        rideInfoPage.submitProperVehicleType();
        rideInfoPage.waitForDialog();
        rideInfoPage.cleanUp();
        driver.quit();

    }
    @Order(4)
    @Test
    public void testUnsuccessfulRideOrder(){
        passengerMainPage.submitValidLocations();
        PassengerRideInfoPage rideInfoPage = new PassengerRideInfoPage(webDriver);
        Assertions.assertTrue(rideInfoPage.isPageLoaded());
        rideInfoPage.submitExistingEmail();
        rideInfoPage.checkBabies();
        rideInfoPage.submitProperVehicleType();
        rideInfoPage.waitForAlert();

    }

    @Order(3)
    @Test
    public void testScheduleRideOrder(){
        passengerMainPage.submitValidLocations();
        PassengerRideInfoPage rideInfoPage = new PassengerRideInfoPage(webDriver);
        Assertions.assertTrue(rideInfoPage.isPageLoaded());
        rideInfoPage.submitExistingEmail();
        rideInfoPage.checkBabies();
        rideInfoPage.inputTime();
        Helper.takeScreenShot(webDriver, "debug/wtf");
        rideInfoPage.submitProperVehicleType();
        rideInfoPage.waitForScheduleAlert();
    }

    @Order(1)
    @Test
    public void testEmptyLocationsRideOrder(){
        passengerMainPage.submitEmptyLocations();
    }

    @Order(1)
    @Test
    public void testInvalidLocationsRideOrder(){
        passengerMainPage.submitInvalidLocations();
    }


    @Order(1)
    @Test
    public void testNoVehicleType(){
        passengerMainPage.submitValidLocations();
        PassengerRideInfoPage rideInfoPage = new PassengerRideInfoPage(webDriver);
        Assertions.assertTrue(rideInfoPage.isPageLoaded());
        rideInfoPage.submitNoVehicleType();

    }

    @Order(1)
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

    @Order(1)
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

    @Order(1)
    @Test
    public void testAddFriendUnsuccess(){
        passengerMainPage.submitValidLocations();
        PassengerRideInfoPage rideInfoPage = new PassengerRideInfoPage(webDriver);
        Assertions.assertTrue(rideInfoPage.isPageLoaded());
        rideInfoPage.submitNonExistingEmail();

    }




}
