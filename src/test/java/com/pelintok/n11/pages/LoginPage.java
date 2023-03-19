package com.pelintok.n11.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    By buttonLoginPage = By.className("btnSignIn");
    By inputEmail = By.id("email");
    By inputPassword = By.id("password");
    By buttonFormLogin = By.id("loginButton");
    By textEmailError = By.cssSelector(".errorMessage[data-errormessagefor='email'] .errorText");
    By textPasswordError = By.cssSelector(".errorMessage[data-errormessagefor='password'] .errorText");

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void clickLoginPageButton() {
        clickElement(buttonLoginPage);
    }

    public void setEmail(String email){
        clickElement(inputEmail);
        clearElement(inputEmail);
        sendKeys(inputEmail, email);
    }

    public void setPassword(String password){
        clickElement(inputPassword);
        clearElement(inputPassword);
        sendKeys(inputPassword,password);
    }

    public void clickFormLoginButton() {
        clickElement(buttonFormLogin);
    }

    public String getEmailErrorMessage() {
        return getText(textEmailError);
    }

    public String getPasswordErrorMessage() {
        return getText(textPasswordError);
    }

    public Boolean hasEmailErrorClass() {
        return hasCssClass(inputEmail, "val-error");
    }

    public Boolean hasPasswordErrorClass() {
        return hasCssClass(inputPassword, "val-error");
    }
}
