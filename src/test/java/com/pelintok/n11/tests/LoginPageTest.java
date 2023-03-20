package com.pelintok.n11.tests;

import com.pelintok.n11.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.*;

public class LoginPageTest extends BaseTest {

    //Required field validation for email and password fields
    @Test
    public void emptyCredentials() {
        LoginPage loginPage = new LoginPage(driver);

        loginPage.clickLoginPageButton();
        loginPage.clickFormLoginButton();

        Assert.assertEquals(loginPage.getEmailErrorMessage(), "Lütfen e-posta adresinizi girin.");
        Assert.assertTrue(loginPage.hasEmailErrorClass());
        Assert.assertEquals(loginPage.getPasswordErrorMessage(), "Bu alanın doldurulması zorunludur.");
        Assert.assertTrue(loginPage.hasPasswordErrorClass());
    }

    //Empty password error validation
    @Test
    public void emptyPassword() {
        LoginPage loginPage = new LoginPage(driver);

        loginPage.clickLoginPageButton();
        loginPage.setEmail("n11logintest@gmail.com");
        loginPage.clickFormLoginButton();

        Assert.assertEquals(loginPage.getEmailErrorMessage(), "");
        Assert.assertFalse(loginPage.hasEmailErrorClass());
        Assert.assertEquals(loginPage.getPasswordErrorMessage(), "Bu alanın doldurulması zorunludur.");
        Assert.assertTrue(loginPage.hasPasswordErrorClass());
    }

    //Empty mail error validation
    @Test
    public void emptyEmail() {
        LoginPage loginPage = new LoginPage(driver);

        loginPage.clickLoginPageButton();
        loginPage.setPassword("123456");
        loginPage.clickFormLoginButton();

        Assert.assertEquals(loginPage.getEmailErrorMessage(), "Lütfen e-posta adresinizi girin.");
        Assert.assertTrue(loginPage.hasEmailErrorClass());
        Assert.assertEquals(loginPage.getPasswordErrorMessage(), "");
        Assert.assertFalse(loginPage.hasPasswordErrorClass());
    }

    //Invalid email format validation
    @Test
    public void invalidEmail() {
        LoginPage loginPage = new LoginPage(driver);

        loginPage.clickLoginPageButton();
        loginPage.setEmail("asd");
        loginPage.setPassword("123456");
        loginPage.clickFormLoginButton();

        Assert.assertEquals(loginPage.getEmailErrorMessage(), "Lütfen geçerli bir e-posta adresi girin.");
        Assert.assertTrue(loginPage.hasEmailErrorClass());
        Assert.assertEquals(loginPage.getPasswordErrorMessage(), "");
        Assert.assertFalse(loginPage.hasPasswordErrorClass());
    }


    //Password length must be at least 6 characters
    @Test
    public void minimumPasswordLength() {
        LoginPage loginPage = new LoginPage(driver);

        loginPage.clickLoginPageButton();
        loginPage.setEmail("n11logintest@gmail.com");
        loginPage.setPassword("12345");
        loginPage.clickFormLoginButton();

        Assert.assertEquals(loginPage.getEmailErrorMessage(), "");
        Assert.assertFalse(loginPage.hasEmailErrorClass());
        Assert.assertEquals(loginPage.getPasswordErrorMessage(), "Girilen değer en az 6 karakter olmalıdır.");
        Assert.assertTrue(loginPage.hasPasswordErrorClass());
    }
    //Password length must be maximum of 15 characters
    @Test
    public void maximumPasswordLength() {
        LoginPage loginPage = new LoginPage(driver);

        loginPage.clickLoginPageButton();
        loginPage.setEmail("n11logintest@gmail.com");
        loginPage.setPassword("1234567890123456");
        loginPage.clickFormLoginButton();

        Assert.assertEquals(loginPage.getEmailErrorMessage(), "");
        Assert.assertFalse(loginPage.hasEmailErrorClass());
        Assert.assertEquals(loginPage.getPasswordErrorMessage(), "Girilen değer en fazla 15 karakter olmalıdır.");
        Assert.assertTrue(loginPage.hasPasswordErrorClass());
    }

    //Email must not contain Turkish characters.
    @Test
    public void specialCharacterEmail() {
        LoginPage loginPage = new LoginPage(driver);

        loginPage.clickLoginPageButton();
        loginPage.setEmail("n11logıntest@gmail.comm");
        loginPage.setPassword("123456");
        loginPage.clickFormLoginButton();

        Assert.assertEquals(loginPage.getEmailErrorMessage(), "Lütfen geçerli bir e-posta adresi girin.");
        Assert.assertTrue(loginPage.hasEmailErrorClass());
        Assert.assertEquals(loginPage.getPasswordErrorMessage(), "");
        Assert.assertFalse(loginPage.hasPasswordErrorClass());
    }

    //Incorrect password must not be login
    @Test
    public void failedPassword() {
        LoginPage loginPage = new LoginPage(driver);

        loginPage.clickLoginPageButton();
        loginPage.setEmail("n12logintest@gmail.com");
        loginPage.setPassword("123456");
        loginPage.clickFormLoginButton();

        Assert.assertEquals(loginPage.getPasswordErrorMessage(), "E-posta adresiniz veya şifreniz hatalı");
    }

    //Login with an e-mail address where there is no registered user
    @Test
    public void emailNoneExist() {
        LoginPage loginPage = new LoginPage(driver);

        loginPage.clickLoginPageButton();
        loginPage.setEmail("n13logintest@gmail.com");
        loginPage.setPassword("123456");
        loginPage.clickFormLoginButton();

        Assert.assertEquals(loginPage.getPasswordErrorMessage(), "E-posta adresiniz veya şifreniz hatalı");
    }

    //Successful login with the registered user information
    @Test
    public void successfulLogin() {
        LoginPage loginPage = new LoginPage(driver);

        loginPage.clickLoginPageButton();
        loginPage.setEmail("n11logintest@gmail.com");
        loginPage.setPassword("username11");
        loginPage.clickFormLoginButton();
        loginPage.waitHomePageLoading();

        Assert.assertEquals(driver.getCurrentUrl(), "https://www.n11.com/");
        Assert.assertEquals(loginPage.getUsername(), "TU");
    }

}
