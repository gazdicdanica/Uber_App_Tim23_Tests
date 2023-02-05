package pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class PassengerMainPage {

    private final String EMPTY_LOCATION_ALERT = "Please enter both start and end location!";

    private final String INVALID_LOCATION_ALERT = "Sorry, we didn't find a match for enetered address!";
    private final String INVALID_START = "asiufhas";
    private final String INVALID_END = "adfiuha";

    private final String VALID_START="Dimitrija Avramovica 3";
    private final String VALID_END="Strazilovska 17";

    private final WebDriver webDriver;

    @FindBy(id = "photo2")
    private WebElement dropdownImg;

    @FindBy(id = "favorite-rides")
    private WebElement favoriteRides;

    @FindBy(id = "logout-btn-psngr")
    private WebElement logoutBtn;

    @FindBy(id="next-btn")
    private WebElement nextBtn;

    @FindBy(id = "start-point")
    private WebElement startLocation;

    @FindBy(id = "end-point")
    private WebElement endLocation;

    public PassengerMainPage(WebDriver webDriver) {
        this.webDriver = webDriver;

        PageFactory.initElements(webDriver, this);
    }

    public boolean containsFavoriteRides() {
        hoverDropdown();
        return (new WebDriverWait(webDriver, 10)).
                until(ExpectedConditions.visibilityOf(favoriteRides)).isDisplayed();
    }

    private void hoverDropdown() {
//        (new WebDriverWait(webDriver, 10)).
//                until(ExpectedConditions.visibilityOf(dropdownImg)).click();
        Actions actions = new Actions(webDriver);
        actions.moveToElement(dropdownImg).perform();
    }

    public void logout() {
        hoverDropdown();
        logoutBtn.click();
    }

    public void enterStartLocation(){

    }

    public void submitEmptyLocations(){
        startLocation.clear();
        endLocation.clear();
        (new WebDriverWait(webDriver, 10)).until(ExpectedConditions.elementToBeClickable(nextBtn)).click();
        Alert alert = (new WebDriverWait(webDriver, 10)).until(ExpectedConditions.alertIsPresent());
        String text = alert.getText();
        assertEquals(EMPTY_LOCATION_ALERT, text);
        alert.dismiss();

    }

    public void submitInvalidLocations(){
        startLocation.clear();
        endLocation.clear();
        (new WebDriverWait(webDriver, 10)).until(ExpectedConditions.visibilityOf(startLocation));
        startLocation.sendKeys(INVALID_START);
        endLocation.sendKeys(INVALID_END);
        nextBtn.click();

        Alert alert = (new WebDriverWait(webDriver, 10)).until(ExpectedConditions.alertIsPresent());
        String text = alert.getText();

        assertEquals(INVALID_LOCATION_ALERT, text);
        alert.dismiss();
    }
}
