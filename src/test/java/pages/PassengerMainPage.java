package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PassengerMainPage {

    private WebDriver webDriver;

    @FindBy(id = "photo2")
    private WebElement dropdownImg;

    @FindBy(id = "favorite-rides")
    private WebElement favoriteRides;

    @FindBy(id = "logout-btn-psngr")
    private WebElement logoutBtn;

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
        Actions actions = new Actions(webDriver);
        actions.moveToElement(dropdownImg).perform();
    }

    public void logout() {
        hoverDropdown();
        logoutBtn.click();
    }
}
