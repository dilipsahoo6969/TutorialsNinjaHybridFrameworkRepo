package com.tutorialsninja.qa.testcases;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.AccountPage;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.LoginPage;
import com.tutorialsninja.qa.utils.Utilities;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;



@Slf4j
public class LoginTest extends Base {

    LoginPage loginPage;

    public LoginTest(){
        super();
    }

    public WebDriver driver;

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }

    @BeforeMethod
    public void setUp(){
        driver = initializeBrowserAndOpenApplicationURL(prop.getProperty("browser"));
        HomePage homePage = new HomePage(driver);
        homePage.clickOnMyAccount();
        loginPage = homePage.selectLoginOption();
    }

    /*
    @Test(priority = 1)
    public void verifyLoginWithValidCredentials() {

        driver.findElement(By.id("input-email")).sendKeys(prop.getProperty("validEmail"));
        driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("validPassword"));
        driver.findElement(By.xpath("//input[@value='Login']")).click();

        WebElement checkElement = driver.findElement(By.linkText("Edit your account information"));
        Assert.assertTrue(checkElement.isDisplayed());
    }*/

    @Test(priority = 1,dataProvider = "validCredentialSupplier")
    public void verifyLoginWithValidCredentials(String email, String Password) {

        AccountPage accountPage= loginPage.login(email,Password);

        Assert.assertTrue(accountPage.getDisplayStatusOfEditYourAccountInformationOption(),"Edit Your Account " +
                "Information is not displayed.");
    }

    @DataProvider(name = "validCredentialSupplier")
    public Object[][] supplyTestData(){
        Object[][] data = Utilities.getTestDataFromExcel("Login");
        return data;
    }

    @Test(priority = 2)
    public void verifyLoginWithInValidCredentials() {

        loginPage.login(Utilities.generateEmailTimeStamp(),dataProp.getProperty("invalidPassword"));

        String actualWarningMessage = loginPage.retrievEmailPasswordNotMatchingWarningMessageText();
        String expectedWarningMessage = dataProp.getProperty("emailPasswordNoMatchWarning");
        Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage),"Expected Warning message is not " +
                "displayed");
    }


    @Test(priority = 3)
    public void verifyLoginWithInValidEmailAndValidPassword() {

        loginPage.login(Utilities.generateEmailTimeStamp(),prop.getProperty("validPassword"));

        String actualWarningMessage = loginPage.retrievEmailPasswordNotMatchingWarningMessageText();
        String expectedWarningMessage = dataProp.getProperty("emailPasswordNoMatchWarning");
        Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage),"Expected Warning message is not " +
                "displayed");
    }

    @Test(priority = 4)
    public void verifyLoginWithValidEmailAndInvalidPassword() {

        loginPage.login(prop.getProperty("validEmail"),dataProp.getProperty("invalidPassword"));

        String actualWarningMessage = loginPage.retrievEmailPasswordNotMatchingWarningMessageText();
        String expectedWarningMessage = dataProp.getProperty("emailPasswordNoMatchWarning");
        Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage),"Expected Warning message is not " +
                "displayed");
    }

    @Test(priority = 5)
    public void verifyLoginWithoutProvidingCredentials() {

        loginPage.clickOnLoginButton();

        String actualWarningMessage = loginPage.retrievEmailPasswordNotMatchingWarningMessageText();
        String expectedWarningMessage = dataProp.getProperty("emailPasswordNoMatchWarning");
        Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage),"Expected Warning message is not " +
                "displayed");
    }
}
