package pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PassengerRideInfoPage {
    private WebDriver driver;

    private final String NO_TYPE_ALERT = "Please Choose Vehicle Type";
    private final String NO_PROPER_DRIVER_ALERT = "No driver is online with appropriate vehicle.";
    private final String NO_ONLINE_DRIVER_ALERT = "No drivers are online.";
    private final String ADD_FRIEND_ALERT = "Friend Added Successfully";
    private final String ADD_JD_ALERT = "User With This Email Does Not Exist";
    private final String RIDE_UNSUCCESSFUL_ALERT = "Cannot create a ride while you have one already pending!";
    private final String NOTIFIED_ALERT = "System notified, see you soon";
    private final String EXISTING_EMAIL = "test2@email.com";
    private final String NON_EXISTING_EMAIL = "aaaa@gmail.com";


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
        assertEquals(RIDE_UNSUCCESSFUL_ALERT, text);
        alert.dismiss();
    }

    public void createValidRide() {
        (new WebDriverWait(driver, 10)).
                until(ExpectedConditions.elementToBeClickable(startRideBtn)).
                isEnabled();
        submitProperVehicleType();
    }

    public void inputTime(){
        timeSelect.click();
        LocalDateTime time = LocalDateTime.now().plusMinutes(15);
        Instant instant = time.atZone(ZoneId.systemDefault()).toInstant();
        Date date = Date.from(instant);

        SimpleDateFormat display = new SimpleDateFormat("hh:mm");
        String timeValue = display.format(date);
        timeInput.sendKeys(timeValue);
    }

    public void waitForScheduleAlert(){
        Alert alert = (new WebDriverWait(driver, 10)).until(ExpectedConditions.alertIsPresent());
        String text = alert.getText();
        assertEquals(NOTIFIED_ALERT, text);
        alert.dismiss();
    }

    public void cleanUp(){
        (new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(cancelBtn)).click();
        Alert alert = (new WebDriverWait(driver, 10)).until(ExpectedConditions.alertIsPresent());
        alert.dismiss();
    }
}
