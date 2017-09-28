package systemplatform;

import org.openqa.selenium.By;
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

public class UserManage {
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
   } 
	
	@Test(priority=2)
	public void newUser(){
		du.what("platform").click();
		du.waitFor(2000);
		du.what("usermanage").click();
		du.waitFor(2000);
		du.csswhat("adduser").click();
		du.what("loginname").sendKeys(du.getRandomString(5)+"@163.com");
		du.what("loginpassword").sendKeys("ab111111");
		du.what("loginpasswordagain").sendKeys("ab111111");
		du.what("fullname").sendKeys("测试a");
		du.what("mobile").sendKeys("13812345678");
		du.what("adduserchoicecompany").click();
		du.waitFor(1000);
		du.what("adduserchoicecompanyone").click();
		du.waitFor(1000);
		du.what("adduserchoiceleader").click();
		du.waitFor(1000);
		du.what("adduserchoiceleaderone").click();
		du.waitFor(1000);
		du.what("adduserchoicerole").click();
		du.waitFor(1000);
		du.what("adduserchoiceroleone").click();
		du.waitFor(2000);
		du.what("addusersurebtn").click();
		du.waitFor(1000);
		Assert.assertTrue(driver.getPageSource().contains("保存成功"));
	}
	
	@Test(priority=3)
	public void alterUser(){
		//driver.navigate().refresh();
		du.what("platform").click();
		du.waitFor(2000);
		du.what("usermanage").click();
		du.waitFor(2000);
	    du.what("useralter").click();
	    du.what("fullname").clear();
	    du.what("fullname").sendKeys("测试b");
	    du.what("mobile").clear();
	    du.what("mobile").sendKeys("13887654321");
	    du.what("alterusersurebtn").click();
	    du.waitFor(1000);
		Assert.assertTrue(driver.getPageSource().contains("修改成功"));
	}
	
	@Test(priority=4)
	public void stopUser(){
		//driver.navigate().refresh();
		du.what("platform").click();
		du.waitFor(2000);
		du.what("usermanage").click();
		du.waitFor(2000);
	    du.what("userstop").click();
	    du.waitFor(2000);
		du.what("stopusersurebtn").click();
		du.waitFor(2000);
		String qiyong = driver.findElement(By.xpath("//div/div[2]/div[2]/div[1]/div/table/tbody/tr[1]/td[8]/a[2]")).getText();
		Assert.assertTrue(qiyong.contains("启用"));
    }
}
