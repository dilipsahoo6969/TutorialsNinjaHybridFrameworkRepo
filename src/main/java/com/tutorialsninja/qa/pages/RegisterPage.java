package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {

    WebDriver driver;

    //Objects
    @FindBy(id = "input-firstname")
    private WebElement firstNameField;

    @FindBy(id = "input-lastname")
    private WebElement lastNameField;

    @FindBy(id = "input-email")
    private WebElement emailField;

    @FindBy(id = "input-telephone")
    private WebElement telephoneField;

    @FindBy(id = "input-password")
    private WebElement passwordField;

    @FindBy(id = "input-confirm")
    private WebElement passwordConfirmField;

    @FindBy(xpath = "//input[@name='agree']")
    private WebElement privacyPolicyField;

    @FindBy(xpath = "//input[@type='submit']")
    private WebElement continueButton;

    @FindBy(xpath = "//input[@name='newsletter' and @value='1']")
    private WebElement yesNewsLetterOption;

    @FindBy(xpath = "//div[contains(@class, 'alert-dismissible')]")
    private WebElement duplicateEmailAddressWarning;

    @FindBy(xpath = "//div[contains(@class,'alert-dismissible')]")
    private WebElement privacyPolicyWarning;

    @FindBy(xpath = "//input[@id='input-firstname']/following-sibling::div")
    private WebElement firstNameWarning;

    @FindBy(xpath = "//input[@id='input-lastname']/following-sibling::div")
    private WebElement lastNameWarning;

    @FindBy(xpath = "//input[@id='input-email']/following-sibling::div")
    private WebElement emailWarning;

    @FindBy(xpath = "//input[@id='input-telephone']/following-sibling::div")
    private WebElement telephoneWarning;

    @FindBy(xpath = "//input[@id='input-password']/following-sibling::div")
    private WebElement passwordWarning;

    //Use Of Constructor
    public RegisterPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    //Actions
    public void enterFirstName(String firstNameText){
        firstNameField.sendKeys(firstNameText);
    }

    public void enterLastName(String lastNameText){
        lastNameField.sendKeys(lastNameText);
    }

    public void enterEmail(String emailText){
        emailField.sendKeys(emailText);
    }

    public void enterTelephoneNumber(String telephoneNumberText){
        telephoneField.sendKeys(telephoneNumberText);
    }

    public void enterPassword(String passwordText){
        passwordField.sendKeys(passwordText);
    }

    public void enterConfirmPassword(String confirmPasswordText){
        passwordConfirmField.sendKeys(confirmPasswordText);
    }

    public void selectPrivacyPolicy(){
        privacyPolicyField.click();
    }

    public AccountSuccessPage clickOnContinueButton(){
        continueButton.click();
        return new AccountSuccessPage(driver);
    }

    public void selectYesNewsLetterOption(){
        yesNewsLetterOption.click();
    }

    public String retrieveDuplicateEmailAddressWarning(){
        return duplicateEmailAddressWarning.getText();
    }

    public String retrievePrivacyPolicyWarning(){
        return privacyPolicyWarning.getText();
    }

    public String retrieveFirstNameWarning(){
        return firstNameWarning.getText();
    }

    public String retrieveLastNameWarning(){
        return lastNameWarning.getText();
    }

    public String retrieveEmailWarning(){
        return emailWarning.getText();
    }

    public String retrieveTelephoneWarning(){
        return telephoneWarning.getText();
    }

    public String retrievePasswordWarning(){
        return passwordWarning.getText();
    }

    public AccountSuccessPage registerWithMandatoryField(String firstNameText, String lastNameText, String emailText,
                                        String telePhoneNumberText,
                                       String passwordText, String confirmPasswordText){
        firstNameField.sendKeys(firstNameText);
        lastNameField.sendKeys(lastNameText);
        emailField.sendKeys(emailText);
        telephoneField.sendKeys(telePhoneNumberText);
        passwordField.sendKeys(passwordText);
        passwordConfirmField.sendKeys(confirmPasswordText);
        privacyPolicyField.click();
        continueButton.click();
        return new AccountSuccessPage(driver);
    }

    public AccountSuccessPage registerWithAllFields(String firstNameText, String lastNameText, String emailText,
                                                    String telePhoneNumberText,
                                                    String passwordText, String confirmPasswordText){
        firstNameField.sendKeys(firstNameText);
        lastNameField.sendKeys(lastNameText);
        emailField.sendKeys(emailText);
        telephoneField.sendKeys(telePhoneNumberText);
        passwordField.sendKeys(passwordText);
        passwordConfirmField.sendKeys(confirmPasswordText);
        yesNewsLetterOption.click();
        privacyPolicyField.click();
        continueButton.click();
        return new AccountSuccessPage(driver);
    }
}
