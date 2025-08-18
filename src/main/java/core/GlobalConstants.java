package core;

public class GlobalConstants {
    //System info
    public static final String PROJECT_PATH=System.getProperty("user.dir");
    public static final String OS_NAME=System.getProperty("os.name");

    //App Info User
    public static final String DEV_USER_URL="";
    public static final String STAGING_USER_URL="";
    public static final String LIVE_USER_URL="";

    //App Info Admin
    public static final String DEV_ADMIN_URL="";
    public static final String STAGING_ADMIN_URL="";
    public static final String LIVE_ADMIN_URL="";

    public static final String ADMIN_USERNAME="";
    public static final String ADMIN_PASSWORD="";

    //Wait Info
    public static final long SHORT_TIMEOUT=10;
    public static final long LONG_TIMEOUT=30;

    //Upload/ Download
    public static final String UPLOAD_PATH=PROJECT_PATH+"/uploadFiles/";
    public static final String DOWNLOAD_PATH=PROJECT_PATH+"/downloadFiles/";

    //Retry Case Failed
    public static final int RETRY_NUMBER = 3;

    //Browser Logs/ Extension
    public static final String BROWSER_LOG_PATH=PROJECT_PATH+"/browserLogs/";
    public static final String BROWSER_EXTENSION_PATH=PROJECT_PATH+"/browserExtensions/";

    //HTML Report folder
    public static final String REPORTING_PATH=PROJECT_PATH+"";
    public static final String EXTENT_PATH=PROJECT_PATH+"";
    public static final String ALLURE_PATH=PROJECT_PATH+"";

    //Date Test/ Environment
    public static final String DATA_TEST_PATH=PROJECT_PATH+"/dataTest/";
    public static final String ENVIRONMENT_CONFIG_PATH=PROJECT_PATH+"//";
}
