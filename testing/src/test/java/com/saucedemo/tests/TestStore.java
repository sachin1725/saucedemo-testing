package com.saucedemo.tests;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.saucedemo.base.BaseTest;
import com.saucedemo.pages.LoginPage;
import com.saucedemo.pages.ProductPage;


public class TestStore extends BaseTest {

    private final String VALID_USER = "standard_user";
    private final String VALID_PASS = "secret_sauce";

    @Test(priority=1, groups={"auth", "smoke"})
    public void testLogin_Positive(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigateToLogin();
        
        loginPage.login(VALID_USER, VALID_PASS);
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("inventory.html"),"Login failed: not on Products page!");
    }

    @Test(priority = 2, groups={"smoke","auth"})
    public void testLogin_Negative(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigateToLogin();
        loginPage.login(VALID_USER, "WrongPassword");
    
        Assert.assertTrue(loginPage.getErrorMessageText().contains("not"));
    }
    
    @Test(priority=3, groups= {"smoke", "auth"})
    public void testLogout(){
    	
    	LoginPage loginPage = new LoginPage(driver);
    	loginPage.navigateToLogin();
    	loginPage.login(VALID_USER, VALID_PASS);
    	ProductPage productPage = new ProductPage(driver);
    	productPage.openMenu();
    	productPage.clickLogout();
    	Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/");
    }
    
    @Test
    public void testLoginUsernameRequired() {
    	LoginPage loginPage = new LoginPage(driver);
    	loginPage.navigateToLogin();
    	loginPage.login("","");
    	String errorMsg = loginPage.getErrorMessageText();
    	Assert.assertTrue(errorMsg.contains("Username") && errorMsg.contains("required"));
    }
    
    @Test
    public void testLoginPasswordRequired() {
    	LoginPage loginPage = new LoginPage(driver);
    	loginPage.navigateToLogin();
    	loginPage.login("username","");
    	String errorMsg = loginPage.getErrorMessageText();
    	Assert.assertTrue(errorMsg.contains("Password") && errorMsg.contains("required"));
    }

    @Test
    public void testLoginErrorMessageStyle() throws Exception{
    	LoginPage loginPage = new LoginPage(driver);
    	loginPage.navigateToLogin();
        loginPage.login("wrong", "wrong");
        
        WebElement errorMsg = loginPage.getErrorMessage();
        
        String color = errorMsg.getCssValue("background-color");
        String fontSize = errorMsg.getCssValue("font-size");
        Assert.assertEquals(color, "rgba(226, 35, 26, 1)");
        Assert.assertEquals(fontSize, "14px");
    }



    
}
