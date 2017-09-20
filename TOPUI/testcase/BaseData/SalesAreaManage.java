package testcase.BaseData;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.tools.ant.taskdefs.WaitFor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import libs.BrowserType;
import libs.Browsers;
import libs.Wait;
import page.loginTopPage;
import utils.TopDo;

public class SalesAreaManage {
	private WebDriver driver;
	private TopDo du;
	private Browsers browser;
	@BeforeClass//启动火狐浏览器
	public void openFireFox(){
		Browsers browser = new Browsers(BrowserType.firefox);
		driver = browser.driver;
		TopDo du = new TopDo(driver);
	}
	@AfterClass
	public void release(){
		driver.quit();
	}
	
	@Test(priority=1)//登录
	public void logIn(){
		TopDo du = new TopDo(driver);
		loginTopPage logintop = new loginTopPage(driver);
		logintop.openUrl("https://top-stable.sao.so/#/login");
		logintop.setUsername("hy@sina.com");
		logintop.setPassword("123qwe");
		logintop.setCaptcha("aaaa");
		logintop.pressLoginbtn();
		du.waitFor(1000);
		Assert.assertTrue(driver.getPageSource().contains("登录成功"));
	}
	
	@Test(priority=2)//新建
	public void addSalesArea(){
		TopDo du = new TopDo(driver);
		du.what("basedata").click();
		du.waitFor(2000);
		du.what("salesmanage").click();
		du.waitFor(2000);
		du.what("addsales").click();
		
		SimpleDateFormat sdf = new SimpleDateFormat();
		String layout = "yyyyMMddHHmmss";
		sdf.applyPattern(layout);
		Calendar c1 = Calendar.getInstance();
		String code = sdf.format(c1.getTime());
		du.what("salescode").sendKeys(code);
		du.what("salesareaname").sendKeys("江苏"+code);
		du.what("jiangsu").click();
		du.what("newsalesurebtn").click();
		du.waitFor(1000);
		Assert.assertTrue(driver.getPageSource().contains("添加成功"));
	}
	
	@Test(priority=3)//导入
	public void leadinSalesarea() throws IOException{
		TopDo du = new TopDo(driver);
		driver.navigate().refresh();
		du.waitFor(2000);
		du.what("salesarealeadin").click();
		du.waitFor(2000);
		driver.findElement(By.ByCssSelector.cssSelector("i.ivu-icon-ios-cloud-upload")).click();
		Runtime.getRuntime().exec("D:/uploadrun/salesarea.exe");
		du.waitFor(20000);
		driver.findElement(By.xpath("//div/div/div[2]/div/button[2]")).click();
		//Assert.assertTrue(driver.getPageSource().contains("上传成功"));
	}

}
