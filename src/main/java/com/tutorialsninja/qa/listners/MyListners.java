package com.tutorialsninja.qa.listners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.tutorialsninja.qa.utils.ExtentReporter;
import com.tutorialsninja.qa.utils.Utilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.awt.*;
import java.io.File;

public class MyListners implements ITestListener {

    ExtentReports extentReports;
    ExtentTest extentTest;
    String testName;

    @Override
    public void onStart(ITestContext context) {
        extentReports = ExtentReporter.generateExtentReport();
    }

    @Override
    public void onTestStart(ITestResult result) {
        testName = result.getName();
        extentTest = extentReports.createTest(testName);
        extentTest.log(Status.INFO,testName+" started executing");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        extentTest.log(Status.PASS,testName+" got successfully executing");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        WebDriver driver = null;

        try{
            driver = (WebDriver) result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
        }catch (Throwable e){
            e.printStackTrace();
        }

        String destinationScreenshotPath = Utilities.captureScreenshot(driver, result.getName());
        extentTest.addScreenCaptureFromPath(destinationScreenshotPath);
        extentTest.log(Status.INFO,result.getThrowable());
        extentTest.log(Status.FAIL,testName+" got failed");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        extentTest.log(Status.INFO,result.getThrowable());
        extentTest.log(Status.FAIL,testName+" got skipped");
    }

    @Override
    public void onFinish(ITestContext context) {
        extentReports.flush();
        String pathOfExtentReport = System.getProperty("user.dir")+"\\test-output\\ExtentReports\\extentReport.html";
        File extentReportFile = new File(pathOfExtentReport);
        try {
            Desktop.getDesktop().browse(extentReportFile.toURI());
        }catch (Throwable e){
            e.printStackTrace();
        }
    }
}
