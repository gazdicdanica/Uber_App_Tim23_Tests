package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseMainPage {
    private final WebDriver webDriver;

//    @FindBy(id = "map")       //Wanted to wait for map
//    private WebElement map;

    @FindBy(id = "login-btn")
    private WebElement loginBtn;

    private static final String URL="http://localhost:4200";

    public BaseMainPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        this.webDriver.get(URL);
        PageFactory.initElements(webDriver, this);
    }

    public String getTitle() {
        return this.webDriver.getTitle();
    }

    public boolean containsLoginBtn() {
        return (new WebDriverWait(webDriver, 10)).
                until(ExpectedConditions.visibilityOf(loginBtn)).isDisplayed();
    }

    public void openLogin() {
        (new WebDriverWait(webDriver, 10)).until(
                ExpectedConditions.visibilityOf(loginBtn)
        ).click();
    }

}
