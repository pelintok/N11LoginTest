package com.pelintok.n11.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {
    WebDriver webDriver;
    WebDriverWait webDriverWait;

    public BasePage(WebDriver webDriver) {
        this.webDriver = webDriver;
        this.webDriverWait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
    }

    public WebElement findElement(By element){
        return webDriver.findElement(element);
    }

    public void clickElement(By element){
        elementClickable(element);
        findElement(element).click();
    }

    public void clearElement(By element){
        findElement(element).clear();
    }

    public void sendKeys(By element, String text){
        webDriverWait.until(ExpectedConditions.visibilityOf(findElement(element)));
        findElement(element).sendKeys(text);
    }

    public String getText(By element) {
        return findElement(element).getText();
    }

    public Boolean hasCssClass(By element, String className) {
        return findElement(element).getAttribute("class").contains(className);
    }

    public void elementClickable(By element){
        webDriverWait.until(ExpectedConditions.elementToBeClickable(element));
    }
}
