package com.inetbanking.testCases;

import java.io.IOException;


import org.apache.log4j.Logger;
import org.openqa.selenium.NoAlertPresentException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.inetbanking.pageObjects.LoginPage;
import com.inetbanking.utilities.XLUtils;
//be careful this class name is having two underscore before 002

public class TC_LoginDDT__002 extends BaseClass{
	@Test(dataProvider="LoginData")
	public void loginDDT(String user,String pwd) throws Exception {
		
		LoginPage lp=new LoginPage(driver);
		lp.setUserName(user);
		logger.info("username is provided");
		lp.setPassword(pwd);
		logger.info("password is provided");
		lp.clickSubmit();
		Thread.sleep(30000);
		
		logger.info("clicked on submit button");
		if(isAlertPresent()==true) {
			
			driver.switchTo().alert().accept();//close the alert
			driver.switchTo().defaultContent();//focus on main content
			Assert.assertTrue(false);
			logger.warn("login failed");
		}
		else {
			Assert.assertTrue(true);
			logger.info("login passed");
			Thread.sleep(30000);
			lp.clickLogout();
			driver.switchTo().alert().accept();//close the logout alert
			driver.switchTo().defaultContent();//focus on main content log in
		}
		
	}
	
	//user defined method to check alert is present or not
	public boolean isAlertPresent()
	{
		try {
		driver.switchTo().alert();
		return true;
		}
		catch(NoAlertPresentException e) {
			return false;
		}
	}
	
	
	
	@DataProvider(name="LoginData")
	String[][] getData() throws IOException{
		String path=System.getProperty("user.dir")+"/src/test/java/com/inetbanking/testData/LoginData.xlsx";
		int rownum=XLUtils.getRowCount(path,"Sheet1");
		int colcount=XLUtils.getCellCount(path,"Sheet1",1);
		//2D array
		String loginData[][]=new String[rownum][colcount];
		
		//for loop to get data
		for(int i=1;i<=rownum;i++)//row num
		{
			for(int j=0;j<colcount;j++)
			{
				loginData[i-1][j]=XLUtils.getCellData(path,"Sheet1", i,j);
			}
		}
		
		return loginData;
	}

}
