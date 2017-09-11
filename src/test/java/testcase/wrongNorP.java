package testcase;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Test;

import Pages.LoginPage;
import libs.BrowserType;
import libs.Browsers;
import libs.Wait;
import libs.driverByExcel;

public class wrongNorP {
	private WebDriver driver;
	private Wait wait;
	
	@BeforeClass
	public void startfirefox(){
		Browsers browser = new Browsers(BrowserType.firefox);
		driver = browser.driver;
		wait = new Wait(driver);
	}
	
	//设置数据源，读取指定文件下的数据
	@DataProvider(name="testData")
	public  Object[][] words() throws IOException{
		driverByExcel dBE = new driverByExcel();
		return dBE.getTestData("D:\\testdata","uandp.xls","Sheet1");	
	}
	
	//使用数据源
//	@Test(dataProvider = "testData")
//	public void login(String username,String password,String Result){
//		driver.get("http://192.168.8.7:8080/rainbow/pages/login.html");
//		driver.findElement(By.xpath("//input[@id='un']")).sendKeys(username);
//		driver.findElement(By.xpath("//input[@id='password']")).sendKeys(password);
//		driver.findElement(By.xpath("//input[@type='submit']")).click();
//		Assert.assertTrue(driver.getPageSource().contains(Result));
//		
//	}
	
	@Test(dataProvider = "testData")
	public void login(String username,String password,String Result){
		LoginPage login = new LoginPage(driver);
		login.openUrl("http://192.168.8.7:8080/rainbow/pages/login.html");
		login.setUsername(username);
		login.setPassword(password);
		login.pressLogbtn();
		Assert.assertTrue(driver.getPageSource().contains(Result));
				
				
	}
	
	
	@AfterClass
	public void release(){
		driver.quit();
	}
}
