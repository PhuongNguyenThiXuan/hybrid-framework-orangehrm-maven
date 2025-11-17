package core;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObjects.PageGenerator;
import pageObjects.openCart.admin.AdminLoginPO;
import pageObjects.openCart.user.UserHomePO;
import pageObjects.orangeHRM.LoginPageObject;
import pageUIs.BasePageUI;

import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

//Parent class cho các class thuộc main.java.pageObjects
//La abstract class => cho phep cac class con dieu chinh de su dung lai
public class BasePage {
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

    public Set<Cookie> getPageCookies (WebDriver driver){
        return driver.manage().getCookies();
    }

    public void setPageCookies (WebDriver driver, Set<Cookie> cookies){
        for(Cookie cookie:cookies){
            driver.manage().addCookie(cookie);
        }
    }

    public Alert waitAlertPresence (WebDriver driver){
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT)).until(ExpectedConditions.alertIsPresent());
    }

    public void acceptToAlert(WebDriver driver){
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

    public String getCurrentWindowID (WebDriver driver){
        return driver.getWindowHandle();
    }

    public void openUrlByNewTAB (WebDriver driver, String url) {
        driver.switchTo().newWindow(WindowType.TAB).get(url);
    }

    public void openUrlByNewWindow (WebDriver driver, String url) {
        driver.switchTo().newWindow(WindowType.WINDOW).get(url);
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

    private By getByLocator (String locatorType){
        //System.out.println("Locator type = " + locatorType);
        if (locatorType == null || locatorType.trim().isEmpty()) {
            throw new IllegalArgumentException("Locator type cannot be null or empty!");
        }

        String[] locatorArr = locatorType.split("=", 2);
        String locatorPrefix = locatorArr[0].trim();
        String locatorValue = locatorArr[1].trim();

//        System.out.println("Locator Prefix: " + locatorPrefix);
//        System.out.println("Locator Value: " + locatorValue);

        switch (locatorPrefix.toLowerCase()){
            case "id":
                return By.id(locatorValue);
            case "class":
                return By.className(locatorValue);
            case "name":
                return By.name(locatorValue);
            case "css":
                return By.cssSelector(locatorValue);
            case "xpath":
                return By.xpath(locatorValue);
            default:
                throw new IllegalArgumentException("Locator type is not supported!" + locatorType);
        }
    }

    private String castParameter (String locator, String... values) {
        return String.format(locator, (Object[]) values);
    }

    private WebElement getWebElement (WebDriver driver, String locator){
        return driver.findElement(getByLocator(locator));
    }

    private WebElement getWebElement (WebDriver driver, String locator, String... restValues){
        return driver.findElement(getByLocator(castParameter(locator, restValues)));
    }

    public List<WebElement> getListWebElements (WebDriver driver, String locator){
        return driver.findElements(getByLocator(locator));
    }

    public List<WebElement> getListWebElements (WebDriver driver, String locator, String... restValues){
        return driver.findElements(getByLocator(castParameter(locator, restValues)));
    }

    public void clickToElement (WebDriver driver, String locator){
        getWebElement(driver, locator).click();
    }

    public void clickToElement (WebDriver driver, String locator, String... restValues){
        getWebElement(driver, castParameter(locator, restValues)).click();
    }

    public void sendKey (WebDriver driver, String locator, CharSequence keyToSend){
        getWebElement(driver, locator).clear();
        getWebElement(driver, locator).sendKeys(keyToSend);
    }

    public void sendKey (WebDriver driver, String locator, CharSequence keyToSend, String... restValues){
        getWebElement(driver, castParameter(locator, restValues)).clear();
        getWebElement(driver, castParameter(locator, restValues)).sendKeys(keyToSend);
    }

    public String getElementText (WebDriver driver, String locator){
        return getWebElement(driver, locator).getText();
    }

    public String getElementText (WebDriver driver, String locator, String... restValues){
        return getWebElement(driver, castParameter(locator, restValues)).getText();
    }

    public void selectItemInDropdown (WebDriver driver, String locator, String valueItem){
        new Select(getWebElement(driver, locator))
                .selectByVisibleText(valueItem);
    }

    public void selectItemInDropdown (WebDriver driver, String locator, String valueItem, String... restValues){
        new Select(getWebElement(driver, castParameter(locator, restValues))).selectByVisibleText(valueItem);
    }

    public void getSelectItemInDropdown (WebDriver driver, String locator){
        new Select(getWebElement(driver, locator)).getFirstSelectedOption().getText();
    }

    public void getSelectItemInDropdown (WebDriver driver, String locator, String... restValues){
        new Select(getWebElement(driver, castParameter(locator, restValues))).getFirstSelectedOption().getText();
    }

    public boolean isDropdownMultiple (WebDriver driver, String locator){
       return new Select(getWebElement(driver, locator))
                .isMultiple();
    }

    public void selectItemInSelectTableDropdown(WebDriver driver, String dropdownName, String listValue, String value){
        clickToElement(driver, dropdownName);
        sleepInSecond(1);

        List<WebElement> allItems = new WebDriverWait(driver,Duration.ofSeconds(LONG_TIMEOUT)).until(ExpectedConditions
                .presenceOfAllElementsLocatedBy(By.cssSelector(listValue)));

        for (WebElement item : allItems){
            if(item.getText().equals(value)){
                item.click();
                sleepInSecond(1);
                break;
            }
        }
    }

    public void selectItemInSelectTableDropdown(WebDriver driver, String dropdownName, String listValue, String value, String... restValues){
        clickToElement(driver, castParameter(dropdownName, restValues));
        sleepInSecond(1);

        List<WebElement> allItems = new WebDriverWait(driver,Duration.ofSeconds(LONG_TIMEOUT))
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(listValue)));

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

    public String getDOMAttribute (WebDriver driver, String locator, String attributeName, String... restValues){
        return getWebElement(driver, castParameter(locator, restValues)).getDomAttribute(attributeName);
    }

    public String getElementDOMProperty(WebDriver driver, String locator, String propertyName){
        return getWebElement(driver, locator).getDomProperty(propertyName);
    }

    public String getElementDOMProperty(WebDriver driver, String locator, String propertyName, String... restValues){
        return getWebElement(driver, castParameter(locator, restValues)).getDomProperty(propertyName);
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

    public int getListElementNumber (WebDriver driver, String locator, String... restValues){
        return getListWebElements(driver, castParameter(locator, restValues)).size();
    }

    public void checkToCheckBox (WebDriver driver, String locator){
        if(!isElementSelected(driver, locator)){
            getWebElement(driver, locator).click();
        }
    }

    public void checkToCheckBox (WebDriver driver, String locator, String... restValues){
        if(!isElementSelected(driver, castParameter(locator, restValues))){
            getWebElement(driver, castParameter(locator, restValues)).click();
        }
    }

    public void uncheckToCheckBox (WebDriver driver, String locator){
        if(isElementSelected(driver, locator)){
            getWebElement(driver, locator).click();
        }
    }

    public void uncheckToCheckBox (WebDriver driver, String locator, String... restValues){
        if(isElementSelected(driver, castParameter(locator, restValues))){
            getWebElement(driver, locator).click();
        }
    }


    public boolean isElementDisplayed (WebDriver driver, String locator){
        boolean status = false;
        try {
            //Trường hợp 1: Element có hiển thị ở trên UI và có xuất hiện ở trong DOM (Visible/ Displayed) => true
            //Trường hợp 2: Element không hiển thị ở trên UI và có xuất hiện ở trong DOM (Invisible) => false
            return getWebElement(driver, locator).isDisplayed();
        } catch (NoSuchElementException e) {
            //Trường hợp 3: Element không hiển thị ở trên UI và không có ở trong DOM (Invisible)=> false
            return status;
        }
    }

    public boolean isElementDisplayed (WebDriver driver, String locator, String... restValues){
        boolean status = false;
        try {
            return getWebElement(driver, castParameter(locator, restValues)).isDisplayed();
        } catch (NoSuchElementException e) {
            return status;
        }
    }

    private void overrideGlobalTimeout (WebDriver driver, long timeInSecond){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeInSecond));
    }

    public boolean isElementUndisplayed(WebDriver driver, String locator) {
        overrideGlobalTimeout(driver, SHORT_TIMEOUT);
        List<WebElement> elements = getListWebElements(driver, locator);
        overrideGlobalTimeout(driver, LONG_TIMEOUT);

        if (elements.size() == 0) {
            return true;
        } else if (elements.size() > 0 && !elements.get(0).isDisplayed()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isElementUndisplayed(WebDriver driver, String locator, String... restValues) {
        System.out.println("Start time = " + new Date().toString());
        overrideGlobalTimeout(driver, SHORT_TIMEOUT);
        List<WebElement> elements = getListWebElements(driver, castParameter(locator, restValues));
        overrideGlobalTimeout(driver, LONG_TIMEOUT);

        if (elements.size() == 0) {
            System.out.println("Trường hợp 3: Element không hiển thị ở trên UI và không có ở trong DOM (Invisible)");
            System.out.println("End time = " + new Date().toString());
            return true;
        } else if (elements.size() > 0 && !elements.get(0).isDisplayed()) {
            System.out.println("Trường hợp 2: Element không hiển thị ở trên UI và có xuất hiện ở trong DOM (Invisible)");
            System.out.println("End time = " + new Date().toString());
            return true;
        } else {
            System.out.println("Trường hợp 1: Element có hiển thị ở trên UI và có xuất hiện ở trong DOM (Visible/ Displayed)");
            return false;
        }
    }


    public boolean isElementSelected (WebDriver driver, String locator){
        waitElementVisible(driver, locator);
        return getWebElement(driver, locator).isSelected();
    }

    public boolean isElementSelected (WebDriver driver, String locator, String... restValues){
        waitElementVisible(driver, castParameter(locator, restValues));
        return getWebElement(driver, castParameter(locator, restValues)).isSelected();
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
        sleepInSecond(1);
    }

    public void scrollToElementOnTop(WebDriver driver, String locator, String... restValues) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", getWebElement(driver, castParameter(locator, restValues)));
        sleepInSecond(1);
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
                .until(ExpectedConditions.visibilityOfElementLocated(getByLocator(locator)));
    }

    public WebElement waitElementVisible (WebDriver driver, String locator, String... restValues){
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(getByLocator(castParameter(locator, restValues))));
    }

    public List<WebElement> waitListElementVisible (WebDriver driver, String locator){
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT))
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByLocator(locator)));
    }

    public List<WebElement> waitListElementVisible (WebDriver driver, String locator, String... restValues){
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT))
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByLocator(castParameter(locator, restValues))));
    }

    public boolean waitElementSelected (WebDriver driver, String locator){
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT))
                .until(ExpectedConditions.elementToBeSelected(getByLocator(locator)));
    }

    public boolean waitElementSelected (WebDriver driver, String locator, String... restValues){
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT))
                .until(ExpectedConditions.elementToBeSelected(getByLocator(castParameter(locator, restValues))));
    }

    public WebElement waitElementClickable (WebDriver driver, String locator){
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT))
                .until(ExpectedConditions.elementToBeClickable(getByLocator(locator)));
    }

    public WebElement waitElementClickable (WebDriver driver, WebElement element){
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT))
                .until(ExpectedConditions.elementToBeClickable(element));
    }

    public WebElement waitElementClickable (WebDriver driver, String locator, String... restValue){
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT))
                .until(ExpectedConditions.elementToBeClickable(getByLocator(castParameter(locator, restValue))));
    }

    public boolean waitElementInvisible (WebDriver driver, String locator){
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT))
                .until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(locator)));
    }

    public boolean waitElementInvisible (WebDriver driver, String locator, String... restValues){
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT))
                .until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(castParameter(locator, restValues))));
    }

    public boolean waitElementInvisibleNotInDOM (WebDriver driver, String locator, String... restValues){
        return new WebDriverWait(driver, Duration.ofSeconds(SHORT_TIMEOUT))
                .until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(castParameter(locator, restValues))));
    }

    public boolean waitListElementInvisible (WebDriver driver, String locator){
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT))
                .until(ExpectedConditions.invisibilityOfAllElements(getListWebElements(driver, locator)));
    }

    public boolean waitListElementInvisible (WebDriver driver, String locator, String... restValues){
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT))
                .until(ExpectedConditions.invisibilityOfAllElements(getListWebElements(driver, castParameter(locator, restValues))));
    }

    public WebElement waitElementPresence (WebDriver driver, String locator){
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT))
                .until(ExpectedConditions.presenceOfElementLocated(getByLocator(locator)));
    }

    public List<WebElement> waitListElementPresence (WebDriver driver, String locator){
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT))
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByLocator(locator)));
    }

    public void uploadMultipleFiles (WebDriver driver, String... fileNames){
        String filePath = GlobalConstants.UPLOAD_PATH;
        String fullFileName = "";

        for (String file : fileNames){
            fullFileName = fullFileName + filePath + file + "\n";
        }
        getWebElement(driver, BasePageUI.UPLOAD_FILE_TYPE).sendKeys(fullFileName.trim());
    }

    //opencart
    public UserHomePO clickToLogoutLinkAtUserSite (WebDriver driver) {
        waitElementClickable(driver, BasePageUI.USER_MY_ACCOUNT_HEADER);
        clickToElement(driver, BasePageUI.USER_MY_ACCOUNT_HEADER);

        waitElementClickable(driver, BasePageUI.USER_LOGOUT_BUTTON);
        clickToElement(driver, BasePageUI.USER_LOGOUT_BUTTON);

        waitElementClickable(driver, BasePageUI.USER_CONTINUE_BUTTON);
        clickToElement(driver, BasePageUI.USER_CONTINUE_BUTTON);
        return PageGenerator.getPage(UserHomePO.class, driver);
    }

    public AdminLoginPO clickToLogoutLinkAtAdminSite (WebDriver driver) {
        waitElementClickable(driver, BasePageUI.ADMIN_LOGOUT_BUTTON);
        clickToElement(driver, BasePageUI.ADMIN_LOGOUT_BUTTON);
        return PageGenerator.getPage(AdminLoginPO.class, driver);
    }


    public void openAdminSite(WebDriver driver, String adminUrl) {
        openPageUrl(driver, adminUrl);
    }

    public UserHomePO openUserSite(WebDriver driver, String userUrl) {
        openPageUrl(driver, userUrl);
        return PageGenerator.getPage(UserHomePO.class, driver);
    }

    public UserHomePO openHomeLogo (WebDriver driver){
        waitElementClickable(driver, BasePageUI.USER_HOME_LOGO);
        clickToElement(driver, BasePageUI.USER_HOME_LOGO);
        return PageGenerator.getPage(UserHomePO.class, driver);
    }

    //orangehrm
    @Step("Waiting for Loading Spinner disappear")
    public boolean isLoadingSpinnerDisappear (WebDriver driver){
        return waitListElementInvisible(driver, BasePageUI.SPINNER_ICON);
    }

    @Step("Enter to {0} textbox by label with value {1}")
    public void enterToTextboxByLabel(WebDriver driver, String textboxLabel, String valueToSendkey){
        waitElementVisible(driver, BasePageUI.TEXTBOX_BY_LABEL, textboxLabel);
        sendKey(driver, BasePageUI.TEXTBOX_BY_LABEL, valueToSendkey, textboxLabel);
    }

    @Step("Enter to {0} textbox by name with value {1}")
    public void enterToTextboxByName(WebDriver driver, String textboxNameAttribute, String valueToSendkey){
        waitElementVisible(driver, BasePageUI.TEXTBOX_BY_NAME, textboxNameAttribute);
        sendKey(driver, BasePageUI.TEXTBOX_BY_NAME, valueToSendkey, textboxNameAttribute);
    }

    @Step("Click to {0} button by text")
    public void clickToButtonByText(WebDriver driver, String buttonText){
        waitElementClickable(driver, BasePageUI.BUTTON_BY_TEXT, buttonText);
        clickToElement(driver, BasePageUI.BUTTON_BY_TEXT, buttonText);
    }

    public void clickToButtonByTextInMainTitle(WebDriver driver, String buttonText, String mainTitleName){
        waitElementClickable(driver, BasePageUI.BUTTON_BY_TEXT_IN_MAIN_TITLE, mainTitleName, buttonText);
        clickToElement(driver, BasePageUI.BUTTON_BY_TEXT_IN_MAIN_TITLE, mainTitleName, buttonText);
    }

    public String getTextboxValueByName(WebDriver driver, String textboxNameAttribute){
        waitElementVisible(driver, BasePageUI.TEXTBOX_BY_NAME, textboxNameAttribute);
        return getElementDOMProperty(driver, BasePageUI.TEXTBOX_BY_NAME, "value", textboxNameAttribute);
        //getDOMProperty (WebDriver driver, String locator, String propertyName, String... restValues)
    }

    public String getTextboxValueByLabel(WebDriver driver, String textboxLabel){
        waitElementVisible(driver, BasePageUI.TEXTBOX_BY_LABEL, textboxLabel);
        return getElementDOMProperty(driver, BasePageUI.TEXTBOX_BY_LABEL, "value", textboxLabel);
        //getDOMProperty (WebDriver driver, String locator, String propertyName, String... restValues)
    }

    @Step("Click to {0} module in Menu item")
    public void clickToModuleByTextInMenuItem(WebDriver driver, String moduleName) {
        waitElementClickable(driver, BasePageUI.MODULE_BY_TEXT_IN_MENU_ITEM, moduleName);
        clickToElement(driver, BasePageUI.MODULE_BY_TEXT_IN_MENU_ITEM, moduleName);
    }

    public boolean isModuleByTextInMenuItemDisplayed(WebDriver driver, String moduleName) {
        waitElementVisible(driver, BasePageUI.MODULE_BY_TEXT_IN_MENU_ITEM, moduleName);
        return isElementDisplayed(driver, BasePageUI.MODULE_BY_TEXT_IN_MENU_ITEM, moduleName);
    }

    public boolean isModuleByTextInMenuItemUnDisplayed(WebDriver driver, String moduleName) {
        //waitElementInvisibleNotInDOM(driver, BasePageUI.MODULE_BY_TEXT_IN_MENU_ITEM, moduleName); //5s => co the bo luon de do ton tgian
        return isElementUndisplayed(driver, BasePageUI.MODULE_BY_TEXT_IN_MENU_ITEM, moduleName); //5s
    }

    public void selectDropdownByLabel(WebDriver driver, String labelName, String valueToSelect) {
        waitElementVisible(driver, BasePageUI.PARENT_DROPDOWN_BY_LABEL, labelName);
        selectItemInSelectTableDropdown(driver, BasePageUI.PARENT_DROPDOWN_BY_LABEL, BasePageUI.CHILD_DROPDOWN_BY_LABEL, valueToSelect, labelName);
    }

    public boolean isToastMessageDisplayed(WebDriver driver, String toastMessage) {
        waitElementVisible(driver, BasePageUI.TOAST_MESSAGE_BY_TEXT, toastMessage);
        return isElementDisplayed(driver, BasePageUI.TOAST_MESSAGE_BY_TEXT, toastMessage);
    }

    public void clickToRadioByLabel(WebDriver driver, String labelName) {
        //waitElementClickable(driver, BasePageUI.RADIO_BUTTON_BY_LABEL, labelName);
        clickToElement(driver, BasePageUI.RADIO_BUTTON_BY_LABEL, labelName);
    }

    public void clickToCheckboxByLabel(WebDriver driver, String labelName) {
        waitElementClickable(driver, BasePageUI.CHECKBOX_BY_LABEL, labelName);
        clickToElement(driver, BasePageUI.CHECKBOX_BY_LABEL, labelName);
    }

    public LoginPageObject clickLogoutOnTopMenu(WebDriver driver){
        waitElementClickable(driver, BasePageUI.USER_DROPDOWN);
        clickToElement(driver, BasePageUI.USER_DROPDOWN);
        waitElementClickable(driver, BasePageUI.LOGOUT_LINK);
        clickToElement(driver, BasePageUI.LOGOUT_LINK);
        return PageGenerator.getPage(LoginPageObject.class,driver);
    }

    private final long SHORT_TIMEOUT = GlobalConstants.SHORT_TIMEOUT;
    private final long LONG_TIMEOUT = GlobalConstants.LONG_TIMEOUT;
}
