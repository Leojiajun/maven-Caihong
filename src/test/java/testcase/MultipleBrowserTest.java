package testcase;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.beust.jcommander.Parameter;

import libs.BrowserType;
import libs.Browsers;
import libs.Wait;

public class MultipleBrowserTest {
	private WebDriver driver = null;
	private Browsers browser;
	private Wait wait;
	WebElement element;
	
	@Parameters({"platform"})
	@BeforeClass
	public void inital(String platformtest){
		if(platformtest.equals("FF"))
			browser = new Browsers(BrowserType.firefox);
		else if(platformtest.equals("chrome"))
			browser = new Browsers(BrowserType.chrome);
		else browser = new Browsers(BrowserType.ie);
		driver = browser.driver;
		wait = new Wait(driver);
	}
	
	@Test
	public void testCaihong(){
//		driver.get("http://www.baidu.com");
//		driver.findElement(By.xpath("//input[@id='kw']")).sendKeys("自动化测试");
//		driver.findElement(By.xpath("//input[@id='su']")).click();
//		wait.waitFor(3000);
		driver.get("http://192.168.8.7:8080/rainbow/pages/login.html");
		wait.waitFor(2000);
		Assert.assertEquals((driver.findElement(By.xpath("//input[@id='un']")).isDisplayed()), true);
	}
	
	@AfterClass
	public void release(){
		driver.quit();
	}
}
