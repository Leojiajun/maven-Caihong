package testcase.BaseData;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import libs.BrowserType;
import libs.Browsers;
import libs.TestngRetryListener;
import libs.Wait;
import page.loginTopPage;
import utils.TopDo;
@Listeners({ TestngRetryListener.class })
public class FactoryManage {
	private WebDriver driver;
	private TopDo du;
	private Wait wait;
	private Browsers browser;
	String factoryname = "test1";
	@BeforeClass//启动火狐浏览器
	public void openFireFox(){
		Browsers browser = new Browsers(BrowserType.firefox);
		driver = browser.driver;
		wait = new Wait(driver);
	    du = new TopDo(driver);
	}
	@AfterClass
	public void release(){
		driver.quit();
	}
	
	@Test(priority=1)//登录
	public void logIn(){
		loginTopPage logintop = new loginTopPage(driver);
		logintop.openUrl("https://top-stable.sao.so/#/login");
		logintop.setUsername("hy@sina.com");
		logintop.setPassword("123qwe");
		logintop.setCaptcha("aaaa");
		logintop.pressLoginbtn();
		du.waitFor(1000);
		Assert.assertTrue(driver.getPageSource().contains("登录成功"));
		//TakeScreenshot ss = new TakeScreenshot(driver);
		//ss.takeScreenShot("CompanyManage", "logIn");
		//System.out.println("截图了");	
	}
	@Test(priority=2)//新建一个工厂
	public void addFactory(){
		du.what("basedata").click();
		du.waitFor(2000);
		du.what("factorymanage").click();
		du.waitFor(2000);
		du.what("addfactory").click();
		du.waitFor(2000);
		du.what("factoryname").sendKeys("test1");
		du.what("factoryaddress").sendKeys("上海");
		du.waitFor(2000);
		du.what("factorystyle").click();
		du.what("presscompany").click();
		du.waitFor(2000);
		du.csswhat("addfacsurebtn").click();
		wait.waitFor(2000);
		Assert.assertTrue(driver.getPageSource().contains("test1"));
	}
	@Test(priority=3)//删除一个工厂
	public void delFactory(){
		du.what("basedata").click();
		du.waitFor(2000);
		du.what("factorymanage").click();
		du.waitFor(2000);
		String strNum = (driver.findElement(By.xpath("//div/div[2]/div[2]/div[1]/div/ul/span")).getText()).replaceAll("\\s", "");/***截取文字，去除所有空格*/
		int fNum = Integer.valueOf(strNum.substring(1,2));/**截取工厂总数**/	
		du.what("delfactory").click();
		du.waitFor(2000);
		driver.findElement(By.xpath("//div[2]/div/div/div/div/div[3]/button[2]")).click();
		du.waitFor(2000);
		String strNum2 = (driver.findElement(By.xpath("//div/div[2]/div[2]/div[1]/div/ul/span")).getText()).replaceAll("\\s", "");/***截取文字，去除所有空格*/
		int fNum2 = Integer.valueOf(strNum2.substring(1,2));/**再次截取工厂总数**/
		Assert.assertEquals((fNum-fNum2)==1, true);
	}

}
