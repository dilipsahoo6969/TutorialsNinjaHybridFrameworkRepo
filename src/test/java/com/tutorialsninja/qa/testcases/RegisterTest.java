package com.tutorialsninja.qa.testcases;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.AccountSuccessPage;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.RegisterPage;
import com.tutorialsninja.qa.utils.Utilities;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RegisterTest extends Base {

    RegisterPage registerPage;
    AccountSuccessPage accountSuccessPage;

    public RegisterTest(){
        super();
    }

    public WebDriver driver;

    @BeforeMethod
    public void setup(){
        driver = initializeBrowserAndOpenApplicationURL(prop.getProperty("browser"));
        HomePage homePage = new HomePage(driver);
        homePage.clickOnMyAccount();
        registerPage = homePage.selectRegisterOption();
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }

    @Test(priority = 1)
    public void verifyRegisteringAnAccountWithMandatoryFields(){

        accountSuccessPage = registerPage.registerWithMandatoryField(dataProp.getProperty("firstName"),dataProp.getProperty("lastName"),
                Utilities.generateEmailTimeStamp(),dataProp.getProperty("telephoneNumber"),prop.getProperty(
                        "validPassword"),prop.getProperty("validPassword"));

        /*registerPage.enterFirstName(dataProp.getProperty("firstName"));
        registerPage.enterLastName(dataProp.getProperty("lastName"));
        registerPage.enterEmail(Utilities.generateEmailTimeStamp());
        registerPage.enterTelephoneNumber(dataProp.getProperty("telephoneNumber"));
        registerPage.enterPassword(prop.getProperty("validPassword"));
        registerPage.enterConfirmPassword(prop.getProperty("validPassword"));
        registerPage.selectPrivacyPolicy();
        accountSuccessPage = registerPage.clickOnContinueButton();*/

        String confirmMessage = accountSuccessPage.retrieveAccountSuccessPageHeading();
        Assert.assertEquals(confirmMessage,dataProp.getProperty("accountSuccessfullyCreatedHeading"),"Account success page is not displayed");
    }

    @Test(priority = 2)
    public void verifyRegisteringAccountByProvidingAllFields(){

        accountSuccessPage = registerPage.registerWithAllFields(dataProp.getProperty("firstName"),dataProp.getProperty("lastName"),
                Utilities.generateEmailTimeStamp(),dataProp.getProperty("telephoneNumber"),prop.getProperty(
                        "validPassword"),prop.getProperty("validPassword"));

        /*registerPage.enterFirstName(dataProp.getProperty("firstName"));
        registerPage.enterLastName(dataProp.getProperty("lastName"));
        registerPage.enterEmail(Utilities.generateEmailTimeStamp());
        registerPage.enterTelephoneNumber(dataProp.getProperty("telephoneNumber"));
        registerPage.enterPassword(prop.getProperty("validPassword"));
        registerPage.enterConfirmPassword(prop.getProperty("validPassword"));
        registerPage.selectYesNewsLetterOption();
        registerPage.selectPrivacyPolicy();
        accountSuccessPage = registerPage.clickOnContinueButton();*/

        String confirmMessage = accountSuccessPage.retrieveAccountSuccessPageHeading();
        Assert.assertEquals(confirmMessage,dataProp.getProperty("accountSuccessfullyCreatedHeading"),"Account success page is not displayed");
    }

    @Test(priority = 3)
    public void verifyRegisteringAccountWithExistingEmailAddress(){

        registerPage.registerWithAllFields(dataProp.getProperty("firstName"),dataProp.getProperty("lastName"),
                prop.getProperty("validEmail"),dataProp.getProperty("telephoneNumber"),prop.getProperty(
                        "validPassword"),prop.getProperty("validPassword"));
        /*

        registerPage.enterFirstName(dataProp.getProperty("firstName"));
        registerPage.enterLastName(dataProp.getProperty("lastName"));
        registerPage.enterEmail(prop.getProperty("validEmail"));
        registerPage.enterTelephoneNumber(dataProp.getProperty("telephoneNumber"));
        registerPage.enterPassword(prop.getProperty("validPassword"));
        registerPage.enterConfirmPassword(prop.getProperty("validPassword"));
        registerPage.selectYesNewsLetterOption();
        registerPage.selectPrivacyPolicy();
        registerPage.clickOnContinueButton();

         */

        String actualWarning = registerPage.retrieveDuplicateEmailAddressWarning();
        Assert.assertTrue(actualWarning.contains(dataProp.getProperty("duplicateEmailWarning")),"Warning message" +
                " regarding duplicate email address is not displayed.");
    }

    @Test(priority = 4)
    public void verifyRegisteringAccountWithoutFillingAnyDetails(){

        registerPage.clickOnContinueButton();

        String actualPrivacyPloicy = registerPage.retrievePrivacyPolicyWarning();
        Assert.assertTrue(actualPrivacyPloicy.contains(dataProp.getProperty("privacyPolicyWarning")),"Privacy " +
                "policy Warning message not displayed");

        String actualFirstNameWarning = registerPage.retrieveFirstNameWarning();
        Assert.assertTrue(actualFirstNameWarning.contains(dataProp.getProperty("firstNameWarning")),"First " +
                "Name Warning message is not displayed");

        String actualLastNameWarning = registerPage.retrieveLastNameWarning();
        Assert.assertTrue(actualLastNameWarning.contains(dataProp.getProperty("lastNameWarning")),"Last " +
                "Name Warning message is not displayed");

        String actualEmailWarning = registerPage.retrieveEmailWarning();
        Assert.assertTrue(actualEmailWarning.contains(dataProp.getProperty("emailWarning")),"Email Warning " +
                "message is not displayed");

        String actualTelephoneWarning = registerPage.retrieveTelephoneWarning();
        Assert.assertTrue(actualTelephoneWarning.contains(dataProp.getProperty("telephoneWarning")),
                "Telephone" +
                " Warning " +
                "message is not displayed");

        String actualPasswordWarning = registerPage.retrievePasswordWarning();
        Assert.assertTrue(actualPasswordWarning.contains(dataProp.getProperty("passwordWarning")), "Password " +
                "Warning is not displayed.");
    }
}
