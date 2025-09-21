package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountSuccessPage {

    WebDriver driver;

    //Object
    @FindBy(xpath = "//div[@id='content']/h1")
    private WebElement accountSuccessPageHeading;

    //Constructor
    public AccountSuccessPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    //Actions
    public String retrieveAccountSuccessPageHeading(){
        return accountSuccessPageHeading.getText();
    }
}
