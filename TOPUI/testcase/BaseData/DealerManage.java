package testcase.BaseData;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

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
import page.loginTopPage;
import utils.TopDo;
@Listeners({ TestngRetryListener.class })
public class DealerManage {
	private WebDriver driver;
	private TopDo du;
	private Browsers browser;
	@BeforeClass//启动火狐浏览器
	public void openFireFox(){
		Browsers browser = new Browsers(BrowserType.firefox);
		driver = browser.driver;
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
	}
	
	@Test(priority=2)//新建一个经销商
	public void newLeader(){
		du.what("basedata").click();
		du.waitFor(2000);
		du.what("leadermanage").click();
		du.waitFor(2000);
		du.what("addleader").click();
		SimpleDateFormat sdf = new SimpleDateFormat();
		String layout = "yyyyMMddHHmmss";
		sdf.applyPattern(layout);
		Calendar c1 = Calendar.getInstance();
		String leadertime = sdf.format(c1.getTime());
		du.what("leadercode").sendKeys("LC"+leadertime);
		du.what("leadername").sendKeys("LN"+leadertime);
		du.what("bussinessNum").sendKeys("BN"+leadertime);
		du.what("leadercontact").sendKeys("contact");
		du.what("leaderphoneNum").sendKeys("13855556666");
		du.waitFor(2000);
		du.csswhat("choiceArea").click();
		du.waitFor(2000);
		du.what("beijing").click();
		du.waitFor(2000);
		du.csswhat("addleadersurebtn").click();
		du.waitFor(1000);
		Assert.assertTrue(driver.getPageSource().contains("添加成功"));	
	}
	
	@Test(priority=3)//删除一个经销商
	public void delLeader(){
		du.what("deleteleaser").click();
		du.waitFor(1000);
		du.csswhat("deleteleadersurebtn").click();
		du.waitFor(1000);
		Assert.assertTrue(driver.getPageSource().contains("成功"));	
	}
	
	@Test(priority=4)//导入一批经销商
	public void loadin() throws IOException{
		du.what("leaderloadinbtn").click();
		driver.findElement(By.ByCssSelector.cssSelector("i.ivu-icon-ios-cloud-upload")).click();
		Runtime.getRuntime().exec("D:/uploadrun/franchiser.exe");
		du.waitFor(20000);
		driver.findElement(By.xpath("//div[2]/div/div/div[2]/div/button[2]")).click();
//		du.waitFor(5000);
//		Assert.assertTrue(driver.getPageSource().contains("成功"));	
	}
}
