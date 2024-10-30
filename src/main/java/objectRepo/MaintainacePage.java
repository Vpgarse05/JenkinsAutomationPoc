package objectRepo;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MaintainacePage extends BaseTest {
    public MaintainacePage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//span[text()='Maintenance']")
    public WebElement Maintenance;
    @FindBy(xpath = "//span[text()='Private Labeling']")
    public WebElement PrivateLabeling;
    @FindBy(xpath = "//div[@id='mui-component-select-office']")
    public WebElement SelectOfficeDrop;
    @FindBy(xpath = "//button//span[text()='Edit Default Notifications']")
    public WebElement EditDefault;
    @FindBy(xpath = "//button[.='Add']")
    public WebElement AddNotification;
    @FindBy(xpath = "(//div[@class='MuiSelect-root MuiSelect-select MuiSelect-selectMenu MuiSelect-filled MuiInputBase-input MuiFilledInput-input'])[25]")
    public WebElement EventDropdown;
    @FindBy(xpath = "(//div[@class='MuiSelect-root MuiSelect-select MuiSelect-selectMenu MuiSelect-filled MuiInputBase-input MuiFilledInput-input'])[26]")
    public WebElement TypeDropdown;
    @FindBy(xpath = "(//div[@class='MuiSelect-root MuiSelect-select MuiSelect-selectMenu MuiSelect-filled MuiInputBase-input MuiFilledInput-input'])[27]")
    public WebElement ManagersDropdown;
    @FindBy(xpath = "//button[@class='button button-medium button-primary undefined']")
    public WebElement SaveNotificationEvent;
    @FindBy(xpath = "//button[@class='MuiButtonBase-root MuiButton-root MuiButton-text btn btn-primary']//span[text()='Save']")
    public WebElement SaveAllNotification;
    @FindBy(xpath = "//span[text()='OK']")
    public WebElement OkButtonOnPopup;
    @FindBy(xpath = "//p[@id='alert-dialog-description']")
    public WebElement NotificationSuccessMessage;
    @FindBy(xpath = "//div[@id='mui-component-select-SignAheadTiming']")
    public WebElement SignAheadDropdown;
    @FindBy(xpath = "//button//span[.='Save Settings']")
    public WebElement SaveSetting;

    public void ClickOnPrivateLabeling() {
        waitForVisibility(PrivateLabeling);
        click(PrivateLabeling);
    }

    public void waitForSaveSetting() {
        sleepTime(1);
        waitForVisibility(SaveSetting);
        sleepTime(1);
    }

    public void ClickOnMaintenance() {
        sleepTime(1);
        click(Maintenance);
    }

    public void ClickOnSaveSetting() {
        waitForVisibility(SaveSetting);
        click(SaveSetting);
    }

    public void SelectOffice(String value) {
        click(SelectOfficeDrop);
        if (driver.findElement(By.xpath("//li[text()='" + value + "']")).isDisplayed()) {
            click(driver.findElement(By.xpath("//li[text()='" + value + "']")));
        }
    }

    public void ScrollToEditNotification() {
        sleepTime(1);
        scrollToElement(EditDefault);
    }

    public void ClickOnEditNotification() {
        sleepTime(1);
        jsClick(EditDefault);
    }

    public void ClickOnAddNotification() {
        waitForVisibility(AddNotification);
        click(AddNotification);
    }

    public void waitForSaveNotification() {
        waitForVisibility(SaveAllNotification);
    }
    public void ClickOnSaveNotificationEvent()
    {
        click(SaveNotificationEvent);
    }

    public void SelectEventFromDropdown(String value) {
        click(EventDropdown);
        if (driver.findElement(By.xpath("//li[text()='" + value + "']")).isDisplayed()) {
            click(driver.findElement(By.xpath("//li[text()='" + value + "']")));
        }
    }

    public void SelectTypeFromDropdown(String value) {
        click(TypeDropdown);
        if (driver.findElement(By.xpath("//li[text()='" + value + "']")).isDisplayed()) {
            click(driver.findElement(By.xpath("//li[text()='" + value + "']")));
        }
    }
    public void SaveNotification()
    {
        sleepTime(2);
        click(SaveAllNotification);
    }
    public void ClickOnOKPopup()
    {
        try {
            sleepTime(1);
            click(OkButtonOnPopup);
        }catch (Exception e)
        {
            System.out.println(e);
        }

    }
    public void selectSignHead(String signHead)
    {
        scrollToElement(SignAheadDropdown);
        selectByVisibleText(SignAheadDropdown,signHead);
    }
    public void VerifySaveSettingMessage()
    {
        sleepTime(1);
        softAssert(NotificationSuccessMessage,true,"Settings saved successfully.");
    }

}
