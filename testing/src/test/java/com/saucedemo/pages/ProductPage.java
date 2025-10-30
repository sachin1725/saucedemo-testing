package com.saucedemo.pages;

import java.time.Duration;
import java.util.Collections;
import java.util.List;
import java.util.Queue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductPage {

	WebDriver driver;
	WebDriverWait wait;
	
	public ProductPage(WebDriver driver){
		this.driver = driver;
		this.wait = new WebDriverWait(driver,Duration.ofSeconds(10));
	}
	
	private By product = By.className("inventory_item");
	private By unaddedProducts = By.xpath("//button[text()='Add to cart']/ancestor::div[@data-test='inventory-item']");
	private By menu = By.xpath("//*[@data-test='open-menu']/preceding-sibling::button");
	private By logout = By.xpath("//*[@data-test='logout-sidebar-link']");
	private By cart = By.xpath("//*[@data-test='shopping-cart-link]");
	private By cartBadge = By.xpath("//*[@data-test='shopping-cart-badge']");
	
	public int getCartCount() {
		 List<WebElement> badges = driver.findElements(cartBadge);
		if (badges.isEmpty()) return 0;   
		return Integer.parseInt(wait.until(ExpectedConditions.visibilityOfElementLocated(cartBadge)).getText());
	}
	public void openMenu() {
	    	driver.findElement(menu).click();
	    }
	
	public void clickLogout() {
	    	driver.findElement(logout).click();
	    }
	
	public List<WebElement> getUnaddedProducts(){
		//wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(unaddedProducts));
		wait.until(ExpectedConditions.elementToBeClickable(unaddedProducts));
		List<WebElement> products = driver.findElements(product);
		return products;
	}
	
//	public WebElement getProduct(String label) {
//		By requiredProduct = By.xpath("//*[text()='"+label+"']/ancestor::div[@data-test='inventory-item']");
//		wait.till(ExpectedConditions.visibilityOfElementsIdentifedBy(requriedProduct));
//		return driver.findElement(requiredProduct);
//	}
//
	
	public void  addRandomProductsToCart(int count) {	
		List<WebElement> products = getUnaddedProducts();
		if(products.size() < count) return;
		//Collections.shuffle(products);
		for(int i=0;i<count;i++) {
			WebElement cproduct = products.get(i);
			cproduct.findElement(By.tagName("button")).click();
		}
		products.clear();
	}
	
	
	
	
}
