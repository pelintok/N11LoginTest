package com.pelintok.n11.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.openqa.selenium.By.*;

public class LoginPage extends BasePage {

    By buttonLoginPage = className("btnSignIn");
    By inputEmail = id("email");
    By inputPassword = id("password");
    By buttonFormLogin = id("loginButton");
    By textEmailError = cssSelector(".errorMessage[data-errormessagefor='email'] .errorText");
    By textPasswordError = cssSelector(".errorMessage[data-errormessagefor='password'] .errorText");
    By textUsername = cssSelector(".user");

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void clickLoginPageButton() {
        clickElement(buttonLoginPage);
        webDriverWait.until(ExpectedConditions.urlToBe("https://www.n11.com/giris-yap"));
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

    public String getUsername() {
        return getText(textUsername);
    }

    public Boolean hasEmailErrorClass() {
        return hasCssClass(inputEmail, "val-error");
    }

    public Boolean hasPasswordErrorClass() {
        return hasCssClass(inputPassword, "val-error");
    }

    public void waitHomePageLoading() {
        webDriverWait.until(ExpectedConditions.urlToBe("https://www.n11.com/"));
    }
}
