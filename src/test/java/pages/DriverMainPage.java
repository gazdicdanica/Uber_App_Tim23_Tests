package pages;

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

    public DriverMainPage(WebDriver driver) {
        this.driver = driver;

        PageFactory.initElements(driver, this);
    }

    public boolean containsOfflineText() {
        return (new WebDriverWait(driver, 10)).
                until(ExpectedConditions.visibilityOf(offlineText)).isDisplayed();
    }

    public void logout() {
        Actions actions = new Actions(driver);
        actions.moveToElement(dropdownImg).perform();
        logoutBtn.click();
    }
}
