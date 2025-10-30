package com.saucedemo.tests;
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
    
        Assert.assertTrue(loginPage.getErrorMessage().contains("not"));
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


    
}
