package tests;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pages.BaseMainPage;
import pages.LoginPage;
import pages.PassengerMainPage;

public class OrderRideTest extends BaseTest{

    private static String EMAIL="test@email.com";
    private static String PASSWORD="123";

    @BeforeAll
    public static void login(){
        BaseMainPage mainPage = new BaseMainPage(webDriver);
        mainPage.openLogin();
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.login(EMAIL, PASSWORD);
    }

//    @Test
//    public void testSuccessfulRideOrder(){
//
//    }

    @Test
    public void testEmptyLocationsRideOrder(){
        PassengerMainPage mainPage = new PassengerMainPage(webDriver);
        mainPage.submitEmptyLocations();
    }

    @Test
    public void testInvalidLocationsRideOrder(){
        PassengerMainPage mainPage = new PassengerMainPage(webDriver);
        mainPage.submitInvalidLocations();
    }


}
