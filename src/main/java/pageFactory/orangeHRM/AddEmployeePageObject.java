package pageFactory.orangeHRM;

import core.BasePageFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class AddEmployeePageObject extends BasePageFactory {
    private WebDriver driver;

    @CacheLookup
    @FindBy(xpath = "//input[@name='firstName']")
    private WebElement firstNameTextbox;

    @CacheLookup
    @FindBy(xpath = "//input[@name='middleName']")
    private WebElement middleNameTextbox;

    @CacheLookup
    @FindBy(xpath = "//input[@name='lastName']")
    private WebElement lastNameTextbox;

    @CacheLookup
    @FindBy(xpath = "//label[text()='Employee Id']/parent::div/following-sibling::div/input")
    private WebElement employeeID;

    @CacheLookup
    @FindBy(xpath = "//button[@type='submit']")
    private WebElement saveButton;

    @FindBy(xpath = "//div[@class='oxd-loading-spinner']")
    private List<WebElement> loadingSpinner;

    public AddEmployeePageObject(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void enterToFirstNameTextBox(String firstName) {
        waitElementVisible(driver, firstNameTextbox);
        sendKey(firstNameTextbox, firstName);
    }

    public void enterToMiddleNameTextBox(String middleName) {
        waitElementVisible(driver, middleNameTextbox);
        sendKey(middleNameTextbox, middleName);
    }

    public void enterToLastNameTextBox(String lastName) {
        waitElementVisible(driver, lastNameTextbox);
        sendKey(lastNameTextbox, lastName);

    }

    public String getEmployeeID() {
        waitElementVisible(driver, employeeID);
       return getDOMProperty(employeeID, "value");
    }

    public void clickSaveButton() {
        waitElementClickable(driver, saveButton);
        clickToElement(saveButton);
    }

    public boolean isLoadingSpinnerDisappear() {
        return waitListElementInvisible(driver, loadingSpinner);
    }
}
