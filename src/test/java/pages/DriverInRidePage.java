package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DriverInRidePage {

    WebDriver driver;
    @FindBy(id = "startBtn")
    private WebElement startBtn;

    @FindBy(id = "finishBtn")
    private WebElement finishBtn;

    @FindBy(id = "panic-btn")
    private WebElement panicBtn;

    @FindBy(id = "panic-cancel")
    private WebElement panicCancelBtn;

    @FindBy(id = "panic-submit")
    private WebElement panicSubmitBtn;

    @FindBy(id = "panic-reason")
    private WebElement panicReason;

    public DriverInRidePage(WebDriver driver) {
        this.driver = driver;

        PageFactory.initElements(driver, this);
    }



    public void startRide() {
        (new WebDriverWait(driver, 10)).
                until(ExpectedConditions.elementToBeClickable(startBtn)).click();
    }

    public void finishRide() {
        (new WebDriverWait(driver, 10)).
                until(ExpectedConditions.elementToBeClickable(finishBtn)).click();
    }

    public void panicRide() {
        (new WebDriverWait(driver, 10)).
                until(ExpectedConditions.elementToBeClickable(panicBtn)).click();
        fillPanic();
        panicSubmitBtn.click();
    }

    public void fillPanic() {
        (new WebDriverWait(driver, 10)).
                until(ExpectedConditions.visibilityOf(panicReason));
        panicReason.sendKeys("Razlog 1");
    }

}
