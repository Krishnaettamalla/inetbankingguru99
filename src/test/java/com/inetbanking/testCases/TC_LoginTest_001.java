package com.inetbanking.testCases;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.inetbanking.pageObjects.LoginPage;

public class TC_LoginTest_001 extends BaseClass{
	@Test
	public void LoginTest() throws IOException {
		
		logger.info("url is opened");
		
		LoginPage lp=new LoginPage(driver);
		lp.setUserName(username);
		logger.info("username entered");
		lp.setPassword(password);
		logger.info("password entered");
		lp.clickSubmit();
		logger.info("clicked on submit button");
		System.out.println(driver.getTitle());
		if(driver.getTitle().equals("aaaaaaGuru99 Bank Manager HomePage"))
		{
			Assert.assertTrue(true);
			logger.info("login test passed");
		}
		else
		{
			captureScreen(driver,"LoginTest");
			System.out.println("not equals");
			logger.info("login test failed");
			Assert.assertTrue(false);
		}
	}
}
