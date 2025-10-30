package com.saucedemo.base;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;

import com.saucedemo.pages.LoginPage;
import com.saucedemo.pages.ProductPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
    protected  WebDriver driver;
    
    @BeforeClass
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        options.addArguments("--disable-save-password-bubble");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-infobars");
        options.addArguments("--no-default-browser-check");
        options.addArguments("--disable-password-generation");

        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        options.setExperimentalOption("prefs", prefs);

        driver = new ChromeDriver(options);

        driver.manage().window().maximize();
        //driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        
    }

//    //@BeforeMethod
//    public void clearCookies(){
//        driver.manage().deleteAllCookies();
//    }

    @AfterClass
    public void tearDown(){
        if(driver!=null)
                driver.quit();
    }
    
    public ProductPage performLogin(String username, String password) {
    	LoginPage loginPage = new LoginPage(driver);
    	return loginPage.performLogin(username, password);
    }
}
