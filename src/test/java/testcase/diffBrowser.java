package testcase;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Pages.LoginPage;
import libs.BrowserType;
import libs.Browsers;
import libs.Wait;

public class diffBrowser {
	private WebDriver driver = null;
	private Browsers browser = null;
	private Wait wait = null;
	
	@Parameters({"platform"})//将xml文件中的platform的值传入下面的platfromtest
	@BeforeMethod(groups="browsers")
	public void inital(String platformtest){
		if(platformtest.equals("FF"))
			browser = new Browsers(BrowserType.firefox);
		else if(platformtest.equals("chrome"))
			browser = new Browsers(BrowserType.chrome);
		else browser = new Browsers(BrowserType.ie);
		driver = browser.driver;
	}
	
	@Test(groups="module1")
	public void module1(){
		LoginPage loginpage = new LoginPage(driver);
		loginpage.openUrl("http://192.168.8.7:8080/rainbow/pages/login.html");//输入url
		wait.waitFor(3000);
//		loginpage.setUsername("wujiajun");//输入用户名
//		loginpage.setPassword("111111");//输入密码
//		loginpage.prssLogbtn();//点击登录
//		wait.waitFor(5000);//等待5秒
//		Assert.assertEquals(loginpage.yanzheng().isDisplayed(), true);
	}
	
	@Test(groups="module2")
	public void module2(){
		driver.get("https://www.baidu.com/");
		wait.waitFor(3000);
		
	}
	
	@AfterMethod(groups="browsers")
	public void release(){
		driver.quit();
	}
}

