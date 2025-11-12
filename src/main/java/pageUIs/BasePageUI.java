package pageUIs;

public class BasePageUI {
    //orangehrm
    public static final String SPINNER_ICON = "css=div.oxd-loading-spinner";
    public static final String TEXTBOX_BY_LABEL = "xpath=//label[text()=\"%s\"]/parent::div/following-sibling::div//input";
    public static final String TEXTBOX_BY_NAME = "xpath=//input[@name='%s']";
    public static final String BUTTON_BY_TEXT = "xpath=//button[contains(string(),'%s')]";
    public static final String BUTTON_BY_TEXT_IN_MAIN_TITLE = "XPath=//h6[text()='%s']/following-sibling::form//button[contains(string(),'%s')]";
    public static final String MODULE_BY_TEXT_IN_MENU_ITEM = "XPath=//span[text()='%s']/parent::a[contains(@class,'oxd-main-menu-item')]";
    public static final String PARENT_DROPDOWN_BY_LABEL = "XPath=//label[text()='%s']/parent::div/following-sibling::div//i";
    public static final String CHILD_DROPDOWN_BY_LABEL = "XPath=//label[text()='%s']/parent::div/following-sibling::div//div[@class='oxd-select-option']/span";
    public static final String TOAST_MESSAGE_BY_TEXT = "XPath=//p[contains(@class,'oxd-text--toast-message') and text()='%s']";
    public static final String RADIO_BUTTON_BY_LABEL = "XPath=//label[text()='%s']/input";
    public static final String CHECKBOX_BY_LABEL = "XPath=//p[text()='%s']/following-sibling::div//span";
    public static final String USER_DROPDOWN = "Css=p.oxd-userdropdown-name";
    public static final String LOGOUT_LINK = "XPath=//a[@class='oxd-userdropdown-link' and text()='Logout']";

    //opencart
    public static final String USER_MY_ACCOUNT_HEADER = "xpath=//div[@class='dropdown']//span[text()='My Account']";
    public static final String USER_LOGOUT_BUTTON = "xpath=//a[@class='dropdown-item' and text()='Logout']";
    public static final String USER_CONTINUE_BUTTON = "xpath=//a[contains(text(),'Continue')]";
    public static final String ADMIN_LOGOUT_BUTTON = "xpath=//span[contains(text(),'Logout')]";
    public static final String USER_HOME_LOGO = "css=div#logo>a";

    //jQuery
    public static final String UPLOAD_FILE_TYPE = "css=input[type='file']";

}
