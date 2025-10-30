package com.saucedemo.pages;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



//POM of the login page
public class LoginPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By userNameField = By.id("user-name");
    private By passwordField = By.id("password");
    private By loginButton = By.id("login-button");
    private By loggedInUsername = By.id("userName-value");
    private By errorMsg = By.xpath("//*[@data-test='error']");
  
    public LoginPage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void navigateToLogin(){
        driver.get("https://www.saucedemo.com/");
    }

    public void login(String username, String password){
        wait.until(ExpectedConditions.visibilityOfElementLocated(userNameField)).sendKeys(username);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(loginButton).click();
    }
    
    public ProductPage performLogin(String userName, String password) {
    	navigateToLogin();
    	login(userName, password);
        return new ProductPage(driver);
    }

    public String getErrorMessage(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(errorMsg)).getText();
    }

    public String getLoggedInUsername(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(loggedInUsername)).getText();
    }
    
  
}
