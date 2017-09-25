package systemplatform;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import libs.BrowserType;
import libs.Browsers;
import libs.Wait;
import page.loginTopPage;
import utils.TopDo;

public class RoleManage {
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
	@Test(priority=2)//新建角色
	public void newRole(){
		TopDo du = new TopDo(driver);
		du.what("platform").click();
		du.waitFor(2000);
		du.what("rolemanage").click();
		du.waitFor(2000);
		du.csswhat("addrole").click();
		du.waitFor(2000);
		du.what("rolename").sendKeys(du.getRandomString(5));
		du.what("choiceactivity").click();
		du.what("pulldown").click();
		du.what("choiceCRM").click();
		du.csswhat("addrolesurebtn").click();
		du.waitFor(1000);
		Assert.assertTrue(driver.getPageSource().contains("创建成功"));
	}
	
	@Test(priority=3)//删除一个角色
	public void delRole(){
		TopDo du = new TopDo(driver);
		du.what("platform").click();
		du.waitFor(2000);
		du.what("rolemanage").click();
		du.waitFor(2000);
		du.what("delrole").click();
		du.waitFor(2000);
		du.what("delrolesurebtn").click();
		du.waitFor(1000);
		Assert.assertTrue(driver.getPageSource().contains("删除成功"));
	}
}
