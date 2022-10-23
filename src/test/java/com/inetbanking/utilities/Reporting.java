package com.inetbanking.utilities;

//this is listener class used to generte Extent reports
import java.io.File;
import java.io.IOException;
import java.time.LocalTime;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Reporting extends TestListenerAdapter{
	public ExtentHtmlReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest logger;
	
	public void onStart(ITestContext testContext)
	{
		//String timeStamp=new SimpleDataFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()); //timestamp
		LocalTime timeStamp = LocalTime.now();
		
		String ts=timeStamp.toString();
		String repName=timeStamp+".html";
		
		//htmlReporter=new ExtentHtmlReporter(System.getProperty("user.dir")+"/test-output/"+repName);//specify location
		htmlReporter=new ExtentHtmlReporter(System.getProperty("user.dir")+"/test-output/Test-reports.html");
		htmlReporter.loadXMLConfig(System.getProperty("user.dir")+"/extent-config.xml");
		
		extent=new ExtentReports();
		
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Host name","localhost");
		extent.setSystemInfo("Environment","QA");
		extent.setSystemInfo("user","pavan");
		
		htmlReporter.config().setDocumentTitle("InetBanking Test Project");//Title of report
		htmlReporter.config().setReportName("Functional Test Automation Report"); //name of the report
		htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);//Location of the chart
		htmlReporter.config().setTheme(Theme.DARK);
		
	}
	public void onTestSuccess(ITestResult tr)
	{
		logger=extent.createTest(tr.getName()); //create the new entry in the report
		logger.log(Status.PASS,MarkupHelper.createLabel(tr.getName(),ExtentColor.GREEN));//send the passed information
	}
	public void onTestFailure(ITestResult tr)
	{
		logger=extent.createTest(tr.getName()); //create the new entry in the report
		logger.log(Status.PASS,MarkupHelper.createLabel(tr.getName(),ExtentColor.RED));//send the passed information
		
		String screenshotPath=System.getProperty("user.dir")+"/Screenshots/"+tr.getName()+".png";
		
		File f=new File(screenshotPath);
		
		if(f.exists())
		{
			try {
				logger.fail("Screenshot is below:"+logger.addScreenCaptureFromPath(screenshotPath));
				}
			catch(IOException e)
			{
				e.printStackTrace();
			}
		}
	}
	public void onTestSkipped(ITestResult tr)
	{
		logger=extent.createTest(tr.getName()); //create the new entry in the report
		logger.log(Status.PASS,MarkupHelper.createLabel(tr.getName(),ExtentColor.ORANGE));//send the passed information
	}
	public void onFinish(ITestContext testConext)
	{
		extent.flush();
	}
}
