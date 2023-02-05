package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
    private final WebDriver driver;

    @FindBy(id = "input-email-login")
    private WebElement inputEmail;

    @FindBy(id = "input-password-login")
    private WebElement inputPassword;

    @FindBy(id = "login-btn-form")
    private WebElement loginBtnForm;

    @FindBy(css = "mat-error")
    private WebElement matError;

    @FindBy(className = "bottom-p-text")
    private WebElement dontHaveAccText;


    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void setEmail(String email) {
        inputEmail.clear();
        inputEmail.sendKeys(email);
    }

    public void setPassword(String password) {
        inputPassword.clear();
        inputPassword.sendKeys(password);
    }

    public boolean containsMatError() {
        (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOf(matError));
        return matError.isDisplayed();
    }
    public String getParagraphText() {
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOf(this.dontHaveAccText));
        return this.dontHaveAccText.getText();
    }

    public void login(String email, String pw) {
        System.err.println("Vrednosti:" + email + " " + pw);
        setEmail(email);
        setPassword(pw);
        loginBtnForm.click();
    }
}
