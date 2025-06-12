package core;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.*;
import java.time.Duration;
import java.util.List;
import java.util.Set;

//Parent class cho các class thuộc main.java.pageObjects
//La abstract class => cho phep cac class con dieu chinh de su dung lai
public class BasePage {
    private final long SHORT_TIMEOUT = 10;
    private final long LONG_TIMEOUT = 30;

    //      1 - Access Modifier:
    //          public: tất cả các class trong cùng/không cùng package đều sử dụng được. Nếu muốn class nào kế thừa mới được dùng => thì ko khai báo public
    //          protected: chỉ class nào kế thừa mới dùng được
    //          private: chỉ cho các hàm trong cùng class này sử dụng
    //          default: Chỉ cho các class trong cùng package sử dụng
    //      2 - Kiểu dữ liệu của hàm (Data type): void/ int/ String/ boolean/ WebElement/ List<WebElement>/..
    //          Nó sẽ liên quan đến cái chức năng mình viết trong thân hàm
    //      3 - Tên hàm: Đặt tên có nghĩa theo chức năng đang cần viết
    //          Convention tuân theo chuẩn của từng ngôn ngữ lập trình (Java)
    //          lowerCamelCase: từ đầu tiên viết thường - chữ cái đầu tiên của các từ tiếp theo sẽ viết hoa
    //      4 - Có tham số hay ko (tùy vào chức năng cần viết)
    //      5 - Kiểu dữ liệu trả về cho hàm
    //          Nếu như có return dữ liệu thì sẽ khớp vs kiểu dữ liệu ở số 2
    //          Nếu như có return thì nó là cái step cuối cùng

    //Hàm static có nhiệm vụ lấy ra instance của chính class này
    //
    public static BasePage getInstance (){
        return new BasePage();
    }

    public void openPageUrl (WebDriver driver, String pageUrl) {
        System.out.println("driver trong BasePage: " + driver.toString());
        driver.get(pageUrl);
    }

    public String getPageTitle (WebDriver driver){
        return driver.getTitle();
    }

    public String getCurrentUrl (WebDriver driver){
        return driver.getCurrentUrl();
    }

    public String getPageSource (WebDriver driver){
        return driver.getPageSource();
    }

    public void backToPage (WebDriver driver){
        driver.navigate().back();
    }

    public void forwardToPage (WebDriver driver){
        driver.navigate().forward();
    }

    public void refreshPage (WebDriver driver){
        driver.navigate().refresh();
    }

    public Alert waitAlertPresence (WebDriver driver){
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT)).until(ExpectedConditions.alertIsPresent());
    }

    public void acceptAlert (WebDriver driver){
        waitAlertPresence(driver).accept();
    }

    public void cancelAlert (WebDriver driver){
        waitAlertPresence(driver).dismiss();
    }

    public String getAlertText (WebDriver driver){
        return waitAlertPresence(driver).getText();
    }

    public void sendKeyToAlert (WebDriver driver, String text){
        waitAlertPresence(driver).sendKeys(text);
    }

    public void sleepInSecond(int timeInSecond){
        try {
            Thread.sleep(timeInSecond * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void switchToWindowByTitle(WebDriver driver, String title){
        Set<String> allWindows = driver.getWindowHandles();

        for (String runWindow : allWindows){
            driver.switchTo().window(runWindow);
            String currentWin = driver.getTitle();
            if(currentWin.equals(title)){
                break;
            }
        }
        sleepInSecond(2);
    }

    public void switchToWindowByID(WebDriver driver, String parentID){
        Set<String> allWindows = driver.getWindowHandles();

        for (String runWindow : allWindows){
            if(runWindow.equals(parentID)) {
                driver.switchTo().window(runWindow);
                break;
            }
        }
        sleepInSecond(2);
    }

    public void switchToWindowByContainTitle (WebDriver driver, String expectedTitle) {
        Set<String> allWindows = driver.getWindowHandles();

        for (String window : allWindows){
            driver.switchTo().window(window);
            if(driver.getTitle().contains(expectedTitle)){
                break;
            }
        }
        sleepInSecond(2);
    }

    public boolean closeAllWindowsWithoutParent (WebDriver driver, String parentID) {
        Set<String> allWindows = driver.getWindowHandles();

        for (String runWindow : allWindows) {
            if (!runWindow.equals(parentID)) {
                driver.switchTo().window(runWindow);
                driver.close();
            }
        }
        driver.switchTo().window(parentID);
        if (driver.getWindowHandles().size()==1)
            return true;
        else
            return false;
    }

    private By getByXPath(String locator) {
        return By.xpath(locator);
    }

    private WebElement getWebElement (WebDriver driver, String locator){
        return driver.findElement(getByXPath(locator));
    }

    private List<WebElement> getListWebElements (WebDriver driver, String locator){
        return driver.findElements(getByXPath(locator));
    }

    public void clickToElement (WebDriver driver, String locator){
        getWebElement(driver, locator).click();
    }

    public void sendKey (WebDriver driver, String locator, String keyToSend){
        getWebElement(driver, locator).sendKeys(keyToSend);
    }

    public String getElementText (WebDriver driver, String locator){
        return getWebElement(driver, locator).getText();
    }

    public void selectItemInDropdown (WebDriver driver, String locator, String valueItem){
        new Select(getWebElement(driver, locator))
                .selectByVisibleText(valueItem);
    }

    public void getSelectItemInDropdown (WebDriver driver, String locator){
        new Select(getWebElement(driver, locator))
                .getFirstSelectedOption().getText();
    }

    public boolean isDropdownMultiple (WebDriver driver, String locator){
       return new Select(getWebElement(driver, locator))
                .isMultiple();
    }

    public void selectItemInCustomDropdown(WebDriver driver, String dropdownName, String ListValue, String value){
        clickToElement(driver, dropdownName);
        sleepInSecond(1);

        new WebDriverWait(driver,Duration.ofSeconds(LONG_TIMEOUT)).until(ExpectedConditions
                .presenceOfAllElementsLocatedBy(By.cssSelector(ListValue)));

        List<WebElement> allItems = getListWebElements(driver, ListValue);

        for (WebElement item : allItems){
            if(item.getText().equals(value)){
                item.click();
                sleepInSecond(1);
                break;
            }
        }
    }

    public String getAttributeValue (WebDriver driver, String locator, String attributeName){
        return getWebElement(driver, locator).getAttribute(attributeName);
    }

    public String getDOMAttribute (WebDriver driver, String locator, String attributeName){
        return getWebElement(driver, locator).getDomAttribute(attributeName);
    }

    public String getDOMProperty (WebDriver driver, String locator, String propertyName){
        return getWebElement(driver, locator).getDomProperty(propertyName);
    }

//    public String getElementText (WebDriver driver, String locator){
//        return getWebElement(driver, locator).getText();
//    }

    public String getElementCss (WebDriver driver, String locator, String propertyName){
        return getWebElement(driver, locator).getCssValue(propertyName);
    }

    public String getHexColorFromRGBA (String rgbaColor){
        return Color.fromString(rgbaColor).asHex().toUpperCase();
    }

    public int getListElementNumber (WebDriver driver, String locator){
        return getListWebElements(driver, locator).size();
    }

    public void checkToCheckBox (WebDriver driver, String locator){
        if(!isElementSelected(driver, locator)){
            getWebElement(driver, locator).click();
        }
    }

    public void uncheckToCheckBox (WebDriver driver, String locator){
        if(isElementSelected(driver, locator)){
            getWebElement(driver, locator).click();
        }
    }

    public boolean isElementDisplayed (WebDriver driver, String locator){
        waitListElementVisible(driver, locator);
        return getWebElement(driver, locator).isDisplayed();
    }

    public boolean isElementSelected (WebDriver driver, String locator){
        waitElementSelected(driver, locator);
        return getWebElement(driver, locator).isSelected();
    }

    public boolean isElementEnabled (WebDriver driver, String locator){
        return getWebElement(driver, locator).isEnabled();
    }

    public void switchToFrame (WebDriver driver, String locator){
        driver.switchTo().frame(getWebElement(driver, locator));
    }

    public void switchToDefaultContent (WebDriver driver, String locator){
        driver.switchTo().defaultContent();
    }

    public void doubleClick (WebDriver driver, String locator){
        new Actions(driver).doubleClick(getWebElement(driver, locator)).perform();
    }

    public void rightClick (WebDriver driver, String locator){
        new Actions(driver).contextClick(getWebElement(driver, locator)).perform();
    }

    public void moveToElement (WebDriver driver, String locator){
        new Actions(driver).moveToElement(getWebElement(driver, locator)).perform();
    }

    public void dragAndDrop (WebDriver driver, String sourceLocator, String targetLocator){
        new Actions(driver).dragAndDrop(getWebElement(driver, sourceLocator),
                getWebElement(driver, targetLocator)).perform();
    }

    public void sendKeyBoardToElement (WebDriver driver, String locator, Keys keys){
        new Actions(driver).sendKeys(getWebElement(driver, locator), keys).perform();
    }

    public Object executeForBrowser(WebDriver driver, String javaScript) {
        return ((JavascriptExecutor) driver).executeScript(javaScript);
    }

    public String getInnerText(WebDriver driver) {
        return (String) ((JavascriptExecutor) driver).executeScript("return document.documentElement.innerText;");
    }

//    public boolean isExpectedTextInInnerText(WebDriver driver, String textExpected) {
//        String textActual = (String) ((JavascriptExecutor) driver).executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0];");
//        return textActual.equals(textExpected);
//    }

    public void scrollToBottomPage(WebDriver driver) {
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }

//    public void navigateToUrlByJS(WebDriver driver, String url) {
//        ((JavascriptExecutor) driver).executeScript("window.location = '" + url + "'");
//        sleepInSecond(3);
//    }

    public void hightlightElement(WebDriver driver, String locator) {
        WebElement element = getWebElement(driver, locator);
        String originalStyle = element.getAttribute("style");
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', arguments[1])", element, "border: 2px solid red; border-style: dashed;");
        sleepInSecond(2);
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
    }

    public void clickToElementByJS(WebDriver driver, String locator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", getWebElement(driver, locator));
        sleepInSecond(3);
    }

    public String getElementTextByJS(WebDriver driver, String locator) {
        return (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].textContent;", getWebElement(driver, locator));
    }

    public void scrollToElementOnTop(WebDriver driver, String locator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", getWebElement(driver, locator));
    }

    public void scrollToElementOnDown(WebDriver driver, String locator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);", getWebElement(driver, locator));
    }

    public void setAttributeInDOM(WebDriver driver, String locator, String attributeName, String attributeValue) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('" + attributeName + "', '" + attributeValue +"');", getWebElement(driver, locator));
    }

    public void removeAttributeInDOM(WebDriver driver, String locator, String attributeRemove) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getWebElement(driver, locator));
    }

    public void sendkeyToElementByJS(WebDriver driver, String locator, String value) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('value', '" + value + "')", getWebElement(driver, locator));
    }

    public String getAttributeInDOM(WebDriver driver, String locator, String attributeName) {
        return (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].getAttribute('" + attributeName + "');", getWebElement(driver, locator));
    }

    public String getElementValidationMessage(WebDriver driver, String locator) {
        return (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].validationMessage;", getWebElement(driver, locator));
    }

    public boolean isImageLoaded(WebDriver driver, String locator) {
        return (boolean) ((JavascriptExecutor) driver)
                .executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0",
                getWebElement(driver, locator));
    }

    public WebElement waitElementVisible (WebDriver driver, String locator){
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(getByXPath(locator)));
    }

    public List<WebElement> waitListElementVisible (WebDriver driver, String locator){
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT))
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByXPath(locator)));
    }

    public boolean waitElementSelected (WebDriver driver, String locator){
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT))
                .until(ExpectedConditions.elementToBeSelected(getByXPath(locator)));
    }

    public WebElement waitElementClickable (WebDriver driver, String locator){
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT))
                .until(ExpectedConditions.elementToBeClickable(getByXPath(locator)));
    }

    public boolean waitElementInvisible (WebDriver driver, String locator){
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT))
                .until(ExpectedConditions.invisibilityOfElementLocated(getByXPath(locator)));
    }

    public boolean waitListElementInvisible (WebDriver driver, String locator){
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT))
                .until(ExpectedConditions.invisibilityOfAllElements(getListWebElements(driver, locator)));
    }

    public WebElement waitElementPresence (WebDriver driver, String locator){
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT))
                .until(ExpectedConditions.presenceOfElementLocated(getByXPath(locator)));
    }

    public List<WebElement> waitListElementPresence (WebDriver driver, String locator){
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT))
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByXPath(locator)));
    }

}
