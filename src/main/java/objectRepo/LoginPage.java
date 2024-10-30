package objectRepo;

import base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class LoginPage extends BaseTest {
    public LoginPage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[@id='email-address']")
    public WebElement LoginEmail;
    @FindBy(xpath = "//button[@type='submit']")
    public WebElement SubmitButton;
    @FindBy(xpath = "//input[@id='password']")
    public WebElement LoginPassword;
    @FindBy(xpath = "//button[@type='submit']")
    public WebElement LoginSignInButton;

    @FindBy(xpath = "//input[@id='access-code']")
    public WebElement EnterOTP;

    @FindBy(xpath = "//button[text()='Submit']")
    public WebElement SubmitOTP;

    public void EnterLoginEmail(String email) {
        sendKeys(LoginEmail, email);
    }

    public void ClickSubmit() {
        click(SubmitButton);
    }

    public void EnterPassword(String password) {
        sendKeys(LoginPassword, password);
    }

    public void PressSignInButton() {
        click(LoginSignInButton);
    }

    public void EnterOTP(String OTP) {
        sendKeys(EnterOTP, OTP);
    }

    public void SubmitOTP() {
        click(SubmitOTP);
    }
}
