package objectRepo;

import base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class HomePage extends BaseTest {
    public HomePage() {
        PageFactory.initElements(driver, this);
    }
    @FindBy(xpath = "(//div[@class='MuiButtonBase-root MuiListItem-root menu-item MuiListItem-gutters MuiListItem-button'])[1]")
    public WebElement ProductIcon;
    @FindBy(xpath = "//span[text()='Title']")
    public WebElement TittleSlider;
    @FindBy(xpath = "//span[text()='New Closing Package']")
    public WebElement NewSignerLink;
    @FindBy(xpath = "//span[@class='cursor-pointer']")
    public WebElement LogOut;

    public void ClickProductIcon()
    {
        click(ProductIcon);
    }
    public void ClickTittleSlider()
    {
        click(TittleSlider);
    }
    public void ClickNewSignerLink()
    {
        click(NewSignerLink);
    }
    public void ValidateDashboardAtHome()
    {
        sleepTime(2);
        waitForVisibility(LogOut);
        Assert.assertTrue(LogOut.isDisplayed());
    }

}
