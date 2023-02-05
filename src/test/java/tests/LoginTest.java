package tests;

import helper.Helper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.BaseMainPage;
import pages.DriverMainPage;
import pages.LoginPage;
import pages.PassengerMainPage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginTest extends BaseTest {
    private final String EMAIL_DRIVER="pp@gmail.com";
    private final String PASSWORD_DRIVER="123";

    private final String EMAIL_USER="test@email.com";
    private final String PASSWORD_USER="123";


    private final String LOGIN_PAGE_TEXT="Don't have an account? SIGN UP";

    @BeforeEach
    public void setUp() {

    }

//    @Before
//    public void openAirRide() {
//        BaseMainPage mainPage = new BaseMainPage(webDriver);
//        mainPage.openLogin();
//    }

    @Test
    public void testLoginAsDriverSuccessful() {
        BaseMainPage mainPage = new BaseMainPage(webDriver);
        mainPage.openLogin();

        LoginPage loginPage = new LoginPage(webDriver);
        assertEquals(LOGIN_PAGE_TEXT, loginPage.getParagraphText());
        loginPage.login(EMAIL_DRIVER, PASSWORD_DRIVER);

        DriverMainPage driverMain = new DriverMainPage(webDriver);
        assertTrue(driverMain.containsOfflineText());

        Helper.takeScreenShot(webDriver, "loginDriver/driver");
        driverMain.logout();

        assertTrue(mainPage.containsLoginBtn());
    }

    @Test
    public void testLoginAsPassengerSuccessful() {
        BaseMainPage mainPage = new BaseMainPage(webDriver);
        mainPage.openLogin();

        LoginPage loginPage = new LoginPage(webDriver);
        assertEquals(LOGIN_PAGE_TEXT, loginPage.getParagraphText());
        loginPage.login(EMAIL_USER, PASSWORD_USER);

        PassengerMainPage passengerMainPage = new PassengerMainPage(webDriver);
        assertTrue(passengerMainPage.containsFavoriteRides());

        Helper.takeScreenShot(webDriver, "loginPassenger/passenger");
        passengerMainPage.logout();

        assertTrue(mainPage.containsLoginBtn());

    }

    @Test
    public void testLoginInvalidInputFailure() {
        BaseMainPage mainPage = new BaseMainPage(webDriver);
        mainPage.openLogin();

        LoginPage loginPage = new LoginPage(webDriver);
        assertEquals(LOGIN_PAGE_TEXT, loginPage.getParagraphText());
        loginPage.login(EMAIL_DRIVER, EMAIL_USER);

        assertTrue(loginPage.containsMatError());

        Helper.takeScreenShot(webDriver, "invalidLogin/error");
    }
}
