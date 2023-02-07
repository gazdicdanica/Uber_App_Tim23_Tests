package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DriverMainPage {

    private WebDriver driver;

    @FindBy(id = "logout-btn")
    private WebElement logoutBtn;

    @FindBy(id = "photo2")
    private WebElement dropdownImg;

    @FindBy(id = "offline-text")
    private WebElement offlineText;

    @FindBy(id="onlineToggle")
    private WebElement toggle;

    @FindBy(id = "accept-ride-btn")
    private WebElement acceptRideBtn;

    @FindBy(id = "decline-ride-btn")
    private WebElement declineRideBtn;

    @FindBy(id = "mapica")
    private WebElement map;

    @FindBy(id = "panic-reason")
    private WebElement panicReason;
    @FindBy(id = "panic-p")
    private WebElement panicPresent;

    @FindBy(id = "panic-submit")
    private WebElement panicSubmitBtn;


    public DriverMainPage(WebDriver driver) {
        this.driver = driver;

        PageFactory.initElements(driver, this);
    }

    public boolean containsOfflineText() {
        return (new WebDriverWait(driver, 10)).
                until(ExpectedConditions.visibilityOf(offlineText)).isDisplayed();
    }

    public void logout() {
        hoverDropdown();
        logoutBtn.click();

    }

    private void hoverDropdown() {
//        (new WebDriverWait(driver, 10)).
//                until(ExpectedConditions.visibilityOf(dropdownImg)).click();
        Actions actions = new Actions(driver);
        actions.moveToElement(dropdownImg).perform();
    }

    public void startShift(){
        (new WebDriverWait(driver, 10)).
                until(ExpectedConditions.elementToBeClickable(toggle)).click();
    }

    public void acceptRide() {
        (new WebDriverWait(driver, 10)).
                until(ExpectedConditions.visibilityOf(acceptRideBtn)).click();
    }

    public void cancelRide() {
        (new WebDriverWait(driver, 10)).
                until(ExpectedConditions.visibilityOf(declineRideBtn)).click();
        fillCancel();
        panicSubmitBtn.click();
    }

    public void fillCancel() {
        panicReason.sendKeys("Razlog 2");
    }

    public boolean isRideFinished() {
        return (new WebDriverWait(driver, 10)).
                until(ExpectedConditions.visibilityOf(map)).isDisplayed();
    }

    public boolean isPanic() {
        return (new WebDriverWait(driver, 10)).
                until(ExpectedConditions.visibilityOf(panicPresent)).isDisplayed();
    }

    public void dismissPanicPopUp() {
        Actions actions = new Actions(driver);
        WebElement modalElement = driver.findElement(By.cssSelector("mat-dialog-content"));
        actions.moveToElement(modalElement, 300, 300).click().perform();
    }
}
