package testcase.BaseData;

import java.text.SimpleDateFormat;
import java.util.Calendar;

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
public class WordbookManage {
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
		wait.waitFor(1000);
		Assert.assertTrue(driver.getPageSource().contains("登录成功"));
		//Assert.assertEquals(logintop.testEle().isDisplayed(), true);
	}
	
	@Test(priority=2)
	public void newData(){
		du.what("basedata").click();
		wait.waitFor(2000);
		du.what("datamanage").click();
		du.waitFor(2000);
		du.what("adddata").click();
		SimpleDateFormat sdf = new SimpleDateFormat();
		String layout = "yyyyMMddHHmmss";
		sdf.applyPattern(layout);
		Calendar c1 = Calendar.getInstance();
		String Dcode = sdf.format(c1.getTime());
		du.what("datacode").sendKeys(Dcode);
		du.what("dataclass").click();
		du.what("productclass").click();
		du.what("dataname").sendKeys("test"+Dcode);
		du.waitFor(2000);
		du.csswhat("newdatasurebtn").click();
		du.waitFor(1000);
		Assert.assertTrue(driver.getPageSource().contains("添加成功"));	
	}
	
	@Test(priority=3)
	public void delData(){
		du.what("deldata").click();
		du.waitFor(2000);
		du.csswhat("deldatasurebtn").click();
		du.waitFor(1000);
		Assert.assertTrue(driver.getPageSource().contains("删除成功"));
	}
}
