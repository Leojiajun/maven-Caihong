package testcase.BaseData;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import libs.BrowserType;
import libs.Browsers;
import libs.Do;
import libs.Wait;
import libs.driverByExcel;
import page.addFactory;
import page.factoryManage;
import page.loginTopPage;

public class newFactorys {
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
	
	@DataProvider(name="testData")
	public  Object[][] words() throws IOException{
		driverByExcel dBE = new driverByExcel();
		return dBE.getTestData("D:\\testdata","addfctory.xls","Sheet1");	
	}

	
	@Test//登录
	public void logIn(){
		loginTopPage logintop = new loginTopPage(driver);
		logintop.openUrl("https://top-stable.sao.so/#/login");
		logintop.setUsername("hy@sina.com");
		logintop.setPassword("123qwe");
		logintop.setCaptcha("aaaa");
		logintop.pressLoginbtn();
		wait.waitFor(3000);
		Assert.assertEquals(logintop.testEle().isDisplayed(), true);
	}
	

	
	@Test(dataProvider = "testData",dependsOnMethods={"logIn"})
	public void addFactory(String name,String address){
		factoryManage fmanage = new factoryManage(driver);
		wait.waitFor(2000);
		fmanage.pressbasedata();
		wait.waitFor(2000);
		fmanage.pressfacmanage();
		wait.waitFor(2000);
		fmanage.pressadd();
		wait.waitFor(2000);
		addFactory addF = new addFactory(driver);
		addF.setFname(name);
		addF.setFaddress(address);
		//wait.waitFor(2000);
		//addF.pressChoice();
		//addF.pressCompany();
		wait.waitFor(2000);
		addF.pressOKbtn();
		wait.waitFor(2000);
		Assert.assertTrue(driver.getPageSource().contains(name));
	}
	

}
