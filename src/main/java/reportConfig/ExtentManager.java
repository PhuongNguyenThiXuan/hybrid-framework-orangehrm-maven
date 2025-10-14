package reportConfig;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import core.GlobalConstants;

public class ExtentManager {
    private static ExtentReports extent;
    private static String extentReportPath = GlobalConstants.EXTENT_PATH + "ExtentReportV3.html";

    public static ExtentReports getInstance() {
        if (extent == null)
            createInstance();
        return extent;
    }

    public static ExtentReports createInstance() {
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(extentReportPath);
        htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
        htmlReporter.config().setChartVisibilityOnOpen(true);
        htmlReporter.config().setTheme(Theme.DARK);
        htmlReporter.config().setDocumentTitle("NopCommerce HTML Report");
        htmlReporter.config().setEncoding("utf-8");
        htmlReporter.config().setReportName("NopCommerce HTML Report");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        return extent;
    }
}