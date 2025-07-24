package base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
public class ReportManager {
	
    public static ExtentReports createReport(String className) {
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String folderPath = "reports";
        String reportPath = folderPath + "/" + className + "_" + timestamp + ".html";

        File folder = new File(folderPath);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
        ExtentReports extent = new ExtentReports();
        extent.attachReporter(spark);

        extent.setSystemInfo("Author", "Sanket");
        extent.setSystemInfo("Environment", "QA");

        return extent;
    }
}


