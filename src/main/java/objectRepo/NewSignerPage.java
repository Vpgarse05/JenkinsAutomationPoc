package objectRepo;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.File;

public class NewSignerPage extends BaseTest {
    public NewSignerPage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//*[text()='Next']")
    public WebElement NextButton;
    @FindBy(xpath = "//input[@id='title']")
    public WebElement OrderID;
    @FindBy(xpath = "//input[@id='clientrefnumber']")
    public WebElement ClientReferenceID;
    @FindBy(xpath = "//select[@name='lender']")
    public WebElement LenderDropdownElement;
    @FindBy(xpath = "//input[@id='street']")
    public WebElement Street;
    @FindBy(xpath = "//input[@id='city']")
    public WebElement City;
    @FindBy(xpath = "//select[@name='state']")
    public WebElement SateDropdownElement;
    @FindBy(xpath = "//input[@name='zip']")
    public WebElement Zip;
    @FindBy(xpath = "//input[@id='uploadPackageFile']")
    public WebElement SelectFileToUpload;
    @FindBy(xpath = "//button/span[text()='Delete All Documents']")
    public WebElement DeleteAllButton;
    @FindBy(xpath = "//div[@id='AssignedOfficeUpload']")
    public WebElement SelectOffice;
    @FindBy(xpath = "//*[text()='Upload File']")
    public WebElement UploadFile;

    public void ClickOnUploadFileButton() {
        sleepTime(1);
        click(UploadFile);
    }

    public void SelectOfficeAndValue(String value) {
        click(SelectOffice);
        if (driver.findElement(By.xpath("//li[text()='" + value + "']")).isDisplayed()) {
            click(driver.findElement(By.xpath("//li[text()='" + value + "']")));
        }
    }

    public void ClickOnNext() {
        sleepTime(2);
        waitForElementToBeClickable(NextButton);
        //scrollToElement(NextButton);
        jsClick(NextButton);
    }

    public void waitForClosingRoomInformationScreen() {
        waitForVisibility(DeleteAllButton);
    }

    public void sendNotarySelectFileToUpload(String path) {
        sleepTime(2);
        File file = new File(path);
        SelectFileToUpload.sendKeys(file.getAbsolutePath());
    }

    public void EnterOrderID(String orderID) {
        sleepTime(2);
        sendKeys(OrderID, orderID);
    }

    public void EnterClientReferenceID(String clientID) {
        sendKeys(ClientReferenceID, clientID);
    }

    public void selectLanderFromDropdown(String value) {
        selectByVisibleText(LenderDropdownElement, value);
    }

    public void EnterStreet(String street) {
        sendKeys(Street, street);
    }

    public void SelectState(String state) {
        selectByVisibleText(SateDropdownElement, state);
    }

    public void SelectUserState(String state) {
        selectByVisibleText(StateDropdownElement, state);
    }

    public void EnterZip(String zip) {
        sendKeys(Zip, zip);
    }

    @FindBy(xpath = "//button[@class='MuiButtonBase-root MuiButton-root MuiButton-text btn btn-primary mb-2 MuiButton-fullWidth']")
    public WebElement AddSignerButton;
    @FindBy(xpath = "//span[@class='MuiChip-label']")
    public WebElement SelectUploadButton;
    @FindBy(xpath = "//input[@name='FirstName']")
    public WebElement FirstNameInput;
    @FindBy(xpath = "//input[@name='MiddleName']")
    public WebElement MiddleNameInput;
    @FindBy(xpath = "//input[@name='LastName']")
    public WebElement LastNameInput;
    @FindBy(xpath = "//input[@id='email_input']")
    public WebElement EmailInput;
    @FindBy(xpath = "//input[@name='Company']")
    public WebElement Company;
    @FindBy(xpath = "//input[@id='userStreet']")
    public WebElement UserStreet;
    @FindBy(xpath = "//input[@name='Street2']")
    public WebElement Street2;
    @FindBy(xpath = "//select[@id='filled-state-simple']")
    public WebElement StateDropdownElement;
    @FindBy(xpath = "//input[@name='ZipCode']")
    public WebElement ZipCode;
    @FindBy(xpath = "//input[@name='City']")
    public WebElement UserCity;
    @FindBy(xpath = "//input[@name='PIN']")
    public WebElement PIN;
    @FindBy(xpath = "//input[@name='CellPhone']")
    public WebElement CellPhone;
    @FindBy(xpath = "//input[@name='ClientRefNumber']")
    public WebElement ClientRefNumber;
    @FindBy(xpath = "//div[@id='acrdn_header_1']")
    public WebElement SignerCoordinate;
    @FindBy(xpath = "//div[text()='Signature']")
    public WebElement Signature;

    @FindBy(xpath = "//div[text()='Checkbox']")
    public WebElement CheckBox;
    @FindBy(xpath = "//img[@id='pdf']")
    public WebElement pdf;

    public void DragSignToPDF() throws Exception {
        sleepTime(4);
        scrollUntilElementFound(Signature);
        dragAndDrop(Signature, pdf);
        sleepTime(2);
        scrollUntilElementFound(NextButton);
    }

    @FindBy(xpath = "//button[@name='scheduleAdd']")
    public WebElement addClosingSchedule;
    @FindBy(xpath = "//div[@id='mui-component-select-closingType']")
    public WebElement ClosingTypeDropdown;
    @FindBy(xpath = "//li[text()='ROC']")
    public WebElement ROCClosingType;
    @FindBy(xpath = "(//div[@class='MuiSelect-root MuiSelect-select MuiSelect-selectMenu MuiSelect-filled MuiInputBase-input MuiFilledInput-input'])[2]")
    public WebElement selectSignersDropdown;
    @FindBy(xpath = "(//li)[1]")
    public WebElement firstSigner;
    @FindBy(xpath = "//div[@class='MuiInputBase-root MuiFilledInput-root MuiFilledInput-underline MuiAutocomplete-inputRoot MuiInputBase-fullWidth MuiInputBase-formControl MuiInputBase-adornedEnd MuiFilledInput-adornedEnd']")
    public WebElement NotaryDropdown;
    @FindBy(xpath = "//li[@id='notary-option-8']")
    public WebElement automationAccountNotary;

    public void SelectSigner() {
        click(selectSignersDropdown);
        click(firstSigner);
    }

    public void SelectClosingType(String value) {
        click(ClosingTypeDropdown);
        if (driver.findElement(By.xpath("//li[text()='" + value + "']")).isDisplayed()) {
            click(driver.findElement(By.xpath("//li[text()='" + value + "']")));
        }
    }

    public void SelectNotaryType(String value) {
        click(NotaryDropdown);
        if (driver.findElement(By.xpath("//li[text()='" + value + "']")).isDisplayed()) {
            click(driver.findElement(By.xpath("//li[text()='" + value + "']")));
        }
    }

    public void ClickOnAddScheduleIcon() {
        sleepTime(2);
        click(addClosingSchedule);
    }

    public void ClickOnSignerCoordinate() {
        sleepTime(2);
        waitForVisibility(pdf);
        jsClick(SignerCoordinate);
        sleepTime(1);
    }

    @FindBy(xpath = "//button//span[text()='OK']")
    public WebElement OkButton;
    @FindBy(xpath = "//button[@class='MuiButtonBase-root MuiButton-root MuiButton-text btn btn-primary']//span[text()='Yes']")
    public WebElement YesButtonConflict;
    @FindBy(xpath = "//div[text()='Notification Event Type']")
    public WebElement NotificationEventTypeHeader;
    @FindBy(xpath = "//input[@aria-label='Select All Rows checkbox']")
    public WebElement SelectAllNotificationEventType;
    @FindBy(xpath = "//button//span[text()='Save']")
    public WebElement SaveButton;
    @FindBy(xpath = "//p[@id='alert-dialog-description']")
    public WebElement SuccessMessage;
    @FindBy(xpath = "//span[normalize-space()='Dashboard']")
    public WebElement DashBoard;

    public void ClickOnDashBoard() {
        sleepTime(3);
        click(DashBoard);
    }


    public void ClickOnSelectAllNotificationCheckBox() {
        waitForElementToBeClickable(NotificationEventTypeHeader);
        SelectAllNotificationEventType.click();
    }

    public void ValidateSuccessNotaryMessage(String message) {
        softAssert(SuccessMessage, true, message);
    }

    public void ClickAddSigner() {
        scrollToElement(AddSignerButton);
        jsClick(AddSignerButton);
        sleepTime(3);
    }

    public void UploadFile(String path) {
        sendKeys(SelectUploadButton, path);
    }

    public void EnterFirstName(String firstName) {
        sendKeys(FirstNameInput, firstName);
    }

    public void EnterMiddleName(String middleName) {
        sendKeys(MiddleNameInput, middleName);
    }

    public void EnterLastName(String lastName) {
        sendKeys(LastNameInput, lastName);
    }

    public void EnterEmail(String email) {
        sendKeys(EmailInput, email);
    }

    public void EnterCompany(String company) {
        sendKeys(Company, company);
    }

    public void EnterUserStreet(String street) {
        sendKeys(UserStreet, street);
    }

    public void EnterStreet2(String street) {
        sendKeys(Street2, street);
    }

    public void EnterCity(String city) {
        sendKeys(City, city);
    }

    public void EnterUserCity(String city) {
        sendKeys(UserCity, city);
    }

    public void EnterZipCode(String zip) {
        sendKeys(ZipCode, zip);
    }

    public void EnterPIN(String pin) {
        sendKeys(PIN, pin);
    }

    public void EnterCellPhone(String cellPhone) {
        //sendKeys(CellPhone, cellPhone);
        Actions actions = new Actions(driver);
        /*actions.moveToElement(CellPhone).click().sendKeys(cellPhone).build().perform();*/
        scrollToElement(NextButton);
        /*JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].value=’"+cellPhone+"’;", CellPhone);*/
        char[] str = cellPhone.toCharArray();
        for (char cell : str) {
            CellPhone.click();
            actions.moveToElement(CellPhone, 0, 0).click().perform();
            CellPhone.sendKeys(Keys.chord(Keys.CONTROL, Keys.HOME));
            CellPhone.sendKeys(String.valueOf(cell));
        }
    }

    public void EnterClientNumber(String client) {
        sendKeys(ClientReferenceID, client);
    }

    public void ClickOnSaveButton() {
        sleepTime(1);
        jsClick(SaveButton);
    }

    public void ClickOnOk() {
        waitForVisibility(OkButton);
        jsClick(OkButton);
    }

    public void HandleConflictPopup() {
        try {
            if (YesButtonConflict.isDisplayed()) {
                click(YesButtonConflict);
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }

    }

    @FindBy(xpath = "//button//span[text()='Yes, create schedule']")
    public WebElement yesCreateScheduleButton;

    public void ClickYesCreateScheduleButton() {
        waitForVisibility(yesCreateScheduleButton);
        click(yesCreateScheduleButton);
    }
    @FindBy(xpath = "//input[@name='street']")
    public WebElement ScheduleStreet;
    @FindBy(xpath = "//input[@name='city']")
    public WebElement ScheduleCity;
    @FindBy(xpath = "//input[@name='zip']")
    public WebElement ScheduleZip;
    @FindBy(xpath = "//select[@id='filled-state-simple']")
    public WebElement ScheduleState;

    public void EnterStreetSchedule(String street)
    {
        sleepTime(1);
        sendKeys(ScheduleStreet,street);
    }
    public void EnterCitySchedule(String city)
    {
        sleepTime(1);
        sendKeys(ScheduleCity,city);
    }
    public void EnterZipSchedule(String zip)
    {
        sleepTime(1);
        sendKeys(ScheduleZip,zip);
    }
    public void SelectStateSchedule(String state)
    {
        selectByVisibleText(ScheduleState,state);
    }
}
