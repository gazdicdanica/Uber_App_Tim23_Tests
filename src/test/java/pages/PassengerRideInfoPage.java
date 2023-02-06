package pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PassengerRideInfoPage {
    private WebDriver driver;

    private final String NO_TYPE_ALERT = "Please Choose Vehicle Type";
    private final String NO_PROPER_DRIVER_ALERT = "No driver is online with appropriate vehicle.";
    private final String NO_ONLINE_DRIVER_ALERT = "No drivers are online.";
    private final String ADD_FRIEND_ALERT = "Friend Added Successfully";
    private final String ADD_JD_ALERT = "User With This Email Does Not Exist";
    private final String RIDE_UNSUCCESS_ALERT = "Cannot create a ride while you have one already pending!";

    private final String EXISTING_EMAIL = "test2@email.com";
    private final String NON_EXISTING_EMAIL = "aaaa@gmail.com";
    private final String SELF_EMAIL = "test@email.com";

    @FindBy(css = ".column-info > #start-ride-btn")
    private WebElement startRideBtn;

    @FindBy(css = ".vehicle-container > app-vehicle-ride[ng-reflect-type='STANDARD']")
    private WebElement vehicleTypeStandard;
    @FindBy(css = ".vehicle-container > app-vehicle-ride[ng-reflect-type='LUXURY']")
    private WebElement vehicleTypeLuxury;

    @FindBy(id = "time-select")
    private WebElement timeSelect;

    @FindBy(id = "time-picker")
    private WebElement timeInput;

    @FindBy(id = "friend")
    private WebElement friendInput;

    @FindBy(id = "add-friend-btn")
    private WebElement friendButton;

    @FindBy(id = "babies-input")
    private WebElement babiesInput;

    @FindBy(id = "cancel-btn")
    private WebElement cancelBtn;

    public PassengerRideInfoPage(WebDriver webDriver){
        this.driver = webDriver;
        PageFactory.initElements(driver, this);
    }

    public boolean isPageLoaded(){
       return (new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(startRideBtn)).isEnabled();
    }

    public void submitNoVehicleType(){
        startRideBtn.click();
        Alert alert = (new WebDriverWait(driver, 10)).until(ExpectedConditions.alertIsPresent());
        String text = alert.getText();
        assertEquals(NO_TYPE_ALERT, text);
        alert.dismiss();
    }

    public void submitForNoOnlineDriver(){
        vehicleTypeStandard.click();
        startRideBtn.click();
        Alert alert = (new WebDriverWait(driver, 10)).until(ExpectedConditions.alertIsPresent());
        String text = alert.getText();
        assertEquals(NO_ONLINE_DRIVER_ALERT, text);
        alert.dismiss();
    }

    public void submitProperVehicleType() {
        (new WebDriverWait(driver, 10)).
                until(ExpectedConditions.elementToBeClickable(vehicleTypeStandard)).click();
        startRideBtn.click();
    }

    public void submitWithNoProperDriver(){
        vehicleTypeLuxury.click();
        startRideBtn.click();
        Alert alert = (new WebDriverWait(driver, 10)).until(ExpectedConditions.alertIsPresent());
        String text = alert.getText();
        assertEquals(NO_PROPER_DRIVER_ALERT, text);
        alert.dismiss();
    }

    public void submitExistingEmail(){
        friendInput.clear();
        friendInput.sendKeys(EXISTING_EMAIL);
        friendButton.click();
        Alert alert = (new WebDriverWait(driver, 10)).until(ExpectedConditions.alertIsPresent());
        String text = alert.getText();
        assertEquals(ADD_FRIEND_ALERT, text);
        alert.dismiss();
    }

    public void submitNonExistingEmail(){
        friendInput.clear();
        friendInput.sendKeys(NON_EXISTING_EMAIL);
        friendButton.click();
        Alert alert = (new WebDriverWait(driver, 10)).until(ExpectedConditions.alertIsPresent());
        String text = alert.getText();
        assertEquals(ADD_JD_ALERT, text);
        alert.dismiss();
    }

    public void checkBabies(){
        this.babiesInput.click();
    }

    public void waitForDialog(){
        (new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(cancelBtn));
        String innerHtml = driver.findElement(By.cssSelector(".mat-mdc-dialog-title")).getAttribute("innerHTML");
        assertTrue(innerHtml.contains("Waiting for driver..."));
    }

    public void waitForAlert(){
        Alert alert = (new WebDriverWait(driver, 10)).until(ExpectedConditions.alertIsPresent());
        String text = alert.getText();
        assertEquals(RIDE_UNSUCCESS_ALERT, text);
        alert.dismiss();
    }

    public void createValidRide() {
        (new WebDriverWait(driver, 10)).
                until(ExpectedConditions.elementToBeClickable(startRideBtn)).
                isEnabled();
        submitProperVehicleType();
    }
}
