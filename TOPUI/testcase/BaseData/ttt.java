package testcase.BaseData;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import libs.BrowserType;
import libs.Browsers;
import libs.Wait;
import page.loginTopPage;
import utils.TopDo;

public class ttt {
	private WebDriver driver;
	private TopDo du;
	private Wait wait;
	private Browsers browser;
	String factoryname = "test1";
	@BeforeClass//启动火狐浏览器
	public void openFireFox(){
		Browsers browser = new Browsers(BrowserType.chrome);
		driver = browser.driver;
		wait = new Wait(driver);
		TopDo du = new TopDo(driver);
	}
	
	@Test//登录
	public void logIn(){
		TopDo du = new TopDo(driver);
		loginTopPage logintop = new loginTopPage(driver);
		logintop.openUrl("https://top-stable.sao.so/#/login");
		driver.findElement(By.xpath("//input[@placeholder='请输入账号']")).click();
		driver.findElement(By.xpath("//input[@placeholder='请输入密码']")).click();
		driver.findElement(By.xpath("//input[@placeholder='请输入密码']")).sendKeys("111111");
		Assert.assertTrue(driver.getPageSource().contains("用户名不能为空"));
	}
	@Test//登录
	public void nopassword(){
		TopDo du = new TopDo(driver);
		loginTopPage logintop = new loginTopPage(driver);
		logintop.openUrl("https://top-stable.sao.so/#/login");
		driver.findElement(By.xpath("//input[@placeholder='请输入账号']")).sendKeys("hy@sina.com");
		driver.findElement(By.xpath("//input[@placeholder='请输入密码']")).clear();
		du.waitFor(2000);
		driver.findElement(By.xpath("//input[@placeholder='请输入密码']")).click();
		du.waitFor(2000);
		driver.findElement(By.xpath("//input[@placeholder='请输入验证码']")).click();
		du.waitFor(2000);
		//driver.findElement(By.xpath("//button[@type='button']")).click();
		Assert.assertTrue(driver.getPageSource().contains("请填写密码"));
	}
}
