package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.BaseMainPage;
import pages.LoginPage;
import pages.PassengerMainPage;

public class OrderRideTest extends BaseTest{

    private final String EMAIL="test@email.com";
    private final String PASSWORD="123";

    @BeforeEach
    public void login(){
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
        mainPage.submitEmptyOrder();
    }


}
