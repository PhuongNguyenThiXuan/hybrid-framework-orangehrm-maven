package pageFactory;

import core.BasePageFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class LoginPageObject extends BasePageFactory {
    private WebDriver driver;

    //define cac locator/ element
    @CacheLookup
    @FindBy(how = How.CSS, using = "input[name='username']")
    private WebElement usernameTextbox;

    @CacheLookup
    @FindBy(name = "password")
    private WebElement passwordTextbox;

    @CacheLookup
    @FindBy(xpath = "//button[@type='submit']")
    private WebElement loginButton;

    //define cac locator/element
    public LoginPageObject(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //bat buoc cac locator/
    public void enterToUserNameTextBox(String username) {
        waitElementVisible(driver, usernameTextbox);
        sendKey(usernameTextbox, username);
    }

    public void enterToPasswordTextBox(String password) {
        waitElementVisible(driver, passwordTextbox);
        sendKey(passwordTextbox, password);
    }

    public void clickLoginButton() {
        waitElementClickable(driver,loginButton);
        clickToElement(loginButton);
    }
}
