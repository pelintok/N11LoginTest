package com.pelintok.n11.tests;

import com.pelintok.n11.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.*;

public class LoginPageTest extends BaseTest {

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

    @Test
    public void emptyPassword() {
        LoginPage loginPage = new LoginPage(driver);

        loginPage.clickLoginPageButton();
        loginPage.setEmail("pelintok18@gmail.com");
        loginPage.clickFormLoginButton();

        Assert.assertEquals(loginPage.getEmailErrorMessage(), "");
        Assert.assertFalse(loginPage.hasEmailErrorClass());
        Assert.assertEquals(loginPage.getPasswordErrorMessage(), "Bu alanın doldurulması zorunludur.");
        Assert.assertTrue(loginPage.hasPasswordErrorClass());
    }

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

    @Test
    public void minimumPasswordLength() {
        LoginPage loginPage = new LoginPage(driver);

        loginPage.clickLoginPageButton();
        loginPage.setEmail("pelintok18@gmail.com");
        loginPage.setPassword("12345");
        loginPage.clickFormLoginButton();

        Assert.assertEquals(loginPage.getEmailErrorMessage(), "");
        Assert.assertFalse(loginPage.hasEmailErrorClass());
        Assert.assertEquals(loginPage.getPasswordErrorMessage(), "Girilen değer en az 6 karakter olmalıdır.");
        Assert.assertTrue(loginPage.hasPasswordErrorClass());
    }

    @Test
    public void maximumPasswordLength() {
        LoginPage loginPage = new LoginPage(driver);

        loginPage.clickLoginPageButton();
        loginPage.setEmail("pelintok18@gmail.com");
        loginPage.setPassword("1234567890123456");
        loginPage.clickFormLoginButton();

        Assert.assertEquals(loginPage.getEmailErrorMessage(), "");
        Assert.assertFalse(loginPage.hasEmailErrorClass());
        Assert.assertEquals(loginPage.getPasswordErrorMessage(), "Girilen değer en fazla 15 karakter olmalıdır.");
        Assert.assertTrue(loginPage.hasPasswordErrorClass());
    }

    @Test
    public void specialCharacterEmail() {
        LoginPage loginPage = new LoginPage(driver);

        loginPage.clickLoginPageButton();
        loginPage.setEmail("pelıntok18@gmail.com");
        loginPage.setPassword("123456");
        loginPage.clickFormLoginButton();

        Assert.assertEquals(loginPage.getEmailErrorMessage(), "Lütfen geçerli bir e-posta adresi girin.");
        Assert.assertTrue(loginPage.hasEmailErrorClass());
        Assert.assertEquals(loginPage.getPasswordErrorMessage(), "");
        Assert.assertFalse(loginPage.hasPasswordErrorClass());
    }

    @Test
    public void failedPassword() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);

        loginPage.clickLoginPageButton();
        loginPage.setEmail("pelintok18@gmail.com");
        loginPage.setPassword("123456");
        loginPage.clickFormLoginButton();

        Assert.assertEquals(loginPage.getPasswordErrorMessage(), "E-posta adresiniz veya şifreniz hatalı");
    }

    @Test
    public void emailNoneExist() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);

        loginPage.clickLoginPageButton();
        loginPage.setEmail("pelintok18@test.com");
        loginPage.setPassword("123456");
        loginPage.clickFormLoginButton();

        Assert.assertEquals(loginPage.getPasswordErrorMessage(), "E-posta adresiniz veya şifreniz hatalı");
    }

    @Test
    public void successfulLogin() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);

        loginPage.clickLoginPageButton();
        loginPage.setEmail("pelintok18@gmail.com");
        loginPage.setPassword("123456");
        loginPage.clickFormLoginButton();

        //TODO
    }

}
