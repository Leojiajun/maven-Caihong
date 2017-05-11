package testcase;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Pages.LoginPage;
import libs.BrowserType;
import libs.Browsers;
import libs.TestDataCsv;
import libs.Wait;

public class loginCaihongs {
	WebDriver driver;
	private Wait wait;
	private Browsers browser;
	
	@BeforeClass
	//启动firefox浏览器
	public void openFirefox(){
		Browsers browser = new Browsers(BrowserType.firefox);
		driver = browser.driver;
		wait =  new Wait(driver);
	}
	
	@Test
	public void Login(){
		LoginPage loginpage = new LoginPage(driver); //new一个登录页面
		loginpage.openUrl("http://192.168.8.7:8080/rainbow/pages/login.html");//输入url
		for(int i=1;i<=2;i++){

			TestDataCsv data = new TestDataCsv(System.getProperty("user.dir")+"\\src\\test\\java\\testData\\login.csv");

			wait.waitFor(2000);
			loginpage.setUsername(data.getTestData("username", "1"));//输入用户名
			loginpage.setPassword(data.getTestData("password", "1"));//输入密码
			loginpage.pressLogbtn();//点击登录
			
		}
	}
}
