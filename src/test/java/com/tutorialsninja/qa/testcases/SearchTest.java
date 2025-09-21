package com.tutorialsninja.qa.testcases;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.SearchPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


//Updated Comment- Added more details.


public class SearchTest extends Base {

    HomePage homePage;
    SearchPage searchPage;

    public SearchTest(){
        super();
    }

    public WebDriver driver;

    @BeforeMethod
    public void setup(){
        driver = initializeBrowserAndOpenApplicationURL(prop.getProperty("browser"));
        homePage = new HomePage(driver);
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }

    @Test(priority = 1)
    public void verifySearchWithValidProduct() {

        searchPage =  homePage.searchForAProduct(dataProp.getProperty("validProduct"));
       /*
        homePage.enterProductIntoSearchBoxField(dataProp.getProperty("validProduct"));
        searchPage = homePage.clickOnSearchButton();

        */

        Assert.assertTrue(searchPage.displayStatusOfHPValidProduct(),"Valid Product HP is not displayed");
    }

    @Test(priority = 2)
    public void verifySearchWithInvalidProduct(){

        searchPage =  homePage.searchForAProduct(dataProp.getProperty("InvalidProduct"));
        /*
        homePage.enterProductIntoSearchBoxField(dataProp.getProperty("InvalidProduct"));
        searchPage = homePage.clickOnSearchButton();
        */

        String actualSearchMessage = searchPage.retrieveNoProductMessageText();
        Assert.assertEquals(actualSearchMessage,"invalid","No Product " +
                "message in search result is not displayed");
    }

    @Test(priority = 3,dependsOnMethods = {"verifySearchWithValidProduct","verifySearchWithInvalidProduct"})
    public void verifySearchWithoutAnyProduct(){
        searchPage = homePage.clickOnSearchButton();

        String actualSearchMessage = searchPage.retrieveNoProductMessageText();
        Assert.assertEquals(actualSearchMessage,dataProp.getProperty("noProductTest"),"No Product " +
                "message in search result is not displayed");
    }
    
}
