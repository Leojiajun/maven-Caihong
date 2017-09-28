package testcase.BaseData;

import java.sql.SQLException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import libs.BrowserType;
import libs.Browsers;
import libs.DataMysql;
import libs.Do;
import libs.TestngRetryListener;
import libs.Wait;
import page.loginTopPage;
@Listeners({ TestngRetryListener.class })
public class Loginbysql {
	private WebDriver driver;
	private Do du;
	private Wait wait;
	private Browsers browser;
	String factoryname = "test1";
	@BeforeClass
	public void openFireFox(){
		Browsers browser = new Browsers(BrowserType.firefox);
		driver = browser.driver;
		wait = new Wait(driver);
	}
	@AfterClass
	public void release(){
		driver.quit();
	}
	@DataProvider(name="testData")
	public static Object[][] words() throws ClassNotFoundException, SQLException{
		DataMysql dms = new DataMysql();
		return dms.getdbData("topuser");
	}
	
	@Test(dataProvider = "testData")
	public void login(String name,String password){
		loginTopPage logintop = new loginTopPage(driver);
		logintop.openUrl("https://top-stable.sao.so/#/login");
		logintop.setUsername(name);
		logintop.setPassword(password);
		logintop.setCaptcha("aaaa");
		logintop.pressLoginbtn();
		
	}
}
