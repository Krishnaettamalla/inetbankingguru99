package com.inetbanking.testCases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.inetbanking.pageObjects.AddCustomerPage;
import com.inetbanking.pageObjects.LoginPage;



public class TC_AddCustomerTest_003 extends BaseClass
{
	@Test
	 public void addNewCustomer() throws Exception {
		//creating and using object of LoginPage
		 LoginPage lp=new LoginPage(driver);
		 lp.setUserName(username);
		 logger.info("username entered");
		 lp.setPassword(password);
		 logger.info("password entered");
		 lp.clickSubmit();
			logger.info("clicked on submit button");
		 
		 Thread.sleep(3000);
		 
		 //creating and using object of AddCustomerPage
		 AddCustomerPage addcust=new AddCustomerPage(driver);
		 addcust.clickAddNewCustomer();
			logger.info("providing customer details");
		//driver.switchTo().alert().accept();
			//Thread.sleep(3000);
		       WebElement frame1 = driver.findElement(By.id("google_ads_iframe_/24132379/INTERSTITIAL_DemoGuru99_0"));
		       driver.switchTo().frame(frame1);
		       WebElement frame2 = driver.findElement(By.id("ad_iframe"));
		       driver.switchTo().frame(frame2);
		       driver.findElement(By.xpath("//div[@id='dismiss-button']/div/span")).click();
		       driver.switchTo().defaultContent();
		       Thread.sleep(3000);
		 addcust.custName("krishna");
		 addcust.custgender("male");
		 addcust.custdob("01", "01", "1996");
		 Thread.sleep(3000);
		 addcust.custaddress("INDIA");
		 addcust.custcity("hyderabad");
		 addcust.custstate("Telangana");
		 addcust.custpinno("500000");
		 addcust.custtelephoneno("1231231234");
		
		 String email=randomestring()+"@gmail.com";
		 addcust.custemailid(email);
		 addcust.custpassword("abcdef");
		 addcust.custsubmit();
		 Thread.sleep(3000);
		 
		 //now validate
		 boolean res=driver.getPageSource().contains("Customer Registered Successfully!!!");
			logger.info("validation started");
		 if(res==true) {
			 Assert.assertTrue(true);
				logger.info("test case is passed");
		 }
		 else
		 {
			 captureScreen(driver, "addNewCustomer");
			 Assert.assertTrue(false);
				logger.info("test case is failed");
		 }	
		
	 }
}
