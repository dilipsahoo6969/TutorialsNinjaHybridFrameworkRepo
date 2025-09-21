package com.tutorialsninja.qa.base;

import com.tutorialsninja.qa.utils.Utilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.RecursiveTask;

public class Base {

    WebDriver driver = null;
    public Properties prop;
    public Properties dataProp;

    public Base(){
        prop = new Properties();
        String pathOfFile = System.getProperty("user.dir")+"\\src\\main\\java\\com\\tutorialsninja\\qa\\config" +
                "\\Config.properties";
        File file = new File(pathOfFile);
        try {
            FileInputStream fis = new FileInputStream(file);
            prop.load(fis);
        } catch (Throwable e) {
            e.printStackTrace();
        }

        dataProp = new Properties();
        String pathOfDataFile = System.getProperty("user.dir")+"\\src\\main\\java\\com\\tutorialsninja\\qa\\testdata" +
                "\\testData.properties";
        File dataPropFile = new File(pathOfDataFile);
        try{
            FileInputStream datafis = new FileInputStream(dataPropFile);
            dataProp.load(datafis);
        }catch (Throwable e){
            e.printStackTrace();
        }

    }

    public WebDriver initializeBrowserAndOpenApplicationURL(String browserName){

        if(browserName.equalsIgnoreCase("Chrome")){
            driver = new ChromeDriver();
        } else if (browserName.equalsIgnoreCase("Firefox")) {
            driver = new FirefoxDriver();
        }else if (browserName.equalsIgnoreCase("Edge")){
            driver = new EdgeDriver();
        }else {
            System.out.println("Please enter a Valid Browser.");
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utilities.IMPLICIT_WAIT_TIME));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Utilities.PAGE_LOAD_TIME));
        driver.get(prop.getProperty("url"));

        return driver;
    }
}
