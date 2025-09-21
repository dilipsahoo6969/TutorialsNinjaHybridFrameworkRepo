package com.tutorialsninja.qa.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ExtentReporter {

    public static ExtentReports generateExtentReport(){
        ExtentReports extentReports = new ExtentReports();
        String screenshotPath = System.getProperty("user.dir")+"\\test-output\\ExtentReports\\extentReport.html";
        File extentReportFile = new File(screenshotPath);
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(extentReportFile);
        sparkReporter.config().setTheme(Theme.DARK);
        sparkReporter.config().setReportName("TutorialsNinja Test Automations Result");
        sparkReporter.config().setDocumentTitle("TN Automation Report");
        sparkReporter.config().setTimeStampFormat("dd/MM/yyyy hh:mm:ss");
        extentReports.attachReporter(sparkReporter);

        Properties configProp = new Properties();
        String configPath = System.getProperty("user.dir")+"\\src\\main\\java\\com\\tutorialsninja\\qa\\config\\Config.properties";
        File configPropFile = new File(configPath);
        try{
            FileInputStream fisConfigProp = new FileInputStream(configPropFile);
            configProp.load(fisConfigProp);
        }catch (Throwable e){
            e.printStackTrace();
        }

        extentReports.setSystemInfo("Application URL: ", configProp.getProperty("url"));
        extentReports.setSystemInfo("Application Browser Name: ",configProp.getProperty("browser"));
        extentReports.setSystemInfo("Email: ", configProp.getProperty("validEmail"));
        extentReports.setSystemInfo("Password: ",configProp.getProperty("validPassword"));
        extentReports.setSystemInfo("Operating System",System.getProperty("os.name"));
        extentReports.setSystemInfo("Username", System.getProperty("user.name"));
        extentReports.setSystemInfo("Java Version", System.getProperty("java.version"));

        return extentReports;
    }
}
