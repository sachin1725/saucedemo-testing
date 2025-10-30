package com.saucedemo.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.saucedemo.base.BaseTest;
import com.saucedemo.pages.ProductPage;

public class CartTest extends BaseTest {

	
	@Test(groups= {"cart","smoke"})
	public void testAddToCartCount() throws Exception{
		ProductPage productPage = performLogin("standard_user", "secret_sauce");
		int itemsInCart = productPage.getCartCount();
		int newItems = 2;
		productPage.addRandomProductsToCart(newItems);
		int currentItemsInCart = productPage.getCartCount();
		Assert.assertEquals(currentItemsInCart, itemsInCart+newItems);;
	}
}
