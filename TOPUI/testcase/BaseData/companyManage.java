package testcase.BaseData;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import libs.BrowserType;
import libs.Browsers;
import libs.Wait;
import page.loginTopPage;
import utils.TopDo;

public class companyManage {
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
	
	@Test(priority=2)
	public void newCompany(){
		TopDo du = new TopDo(driver);
		du.what("basedata").click();
		du.waitFor(2000);
		du.what("companymanage").click();
		du.waitFor(2000);
		du.csswhat("addcompany").click();
		du.waitFor(2000);
		SimpleDateFormat sdf = new SimpleDateFormat();
		String layout = "yyyyMMddHHmmss";
		sdf.applyPattern(layout);
		Calendar c1 = Calendar.getInstance();
		String companycode = sdf.format(c1.getTime());
		System.out.println(companycode);
		du.what("cname").sendKeys("n"+companycode.substring(5, 13));
		du.what("ccode").sendKeys("a"+companycode.substring(5, 13));
		du.what("contactname").sendKeys("ccontact");
		du.what("contactphone").sendKeys("13812345678");
		du.what("contactcard").sendKeys("320681198942556232");
		du.what("licence").sendKeys("123455432112345");
		du.what("productnum").clear();
		du.what("productnum").sendKeys("30");
		du.what("usernum").clear();
		du.what("usernum").sendKeys("10");
		du.what("terminalnum").clear();
		du.what("terminalnum").sendKeys("2");
		du.what("QRcodenum").clear();
		du.what("QRcodenum").sendKeys("2000000");
		du.what("ckeep").click();
		du.waitFor(1000);
		Assert.assertTrue(driver.getPageSource().contains("保存成功"));
		du.waitFor(2000);
		du.what("review").click();
		du.csswhat("doadd").click();
		du.waitFor(2000);
		du.what("awaken").click();
		du.what("awakencompanysure").click();
	}
	
	@Test(priority=3)
	public void delCompany(){
		TopDo du = new TopDo(driver);
		du.what("basedata").click();
		du.waitForElementIsEnable("companymanage");
		du.what("companymanage").click();
		du.waitFor(2000);
		String strNum3=du.what("companytotal").getText().replaceAll("\\s", "");
		System.out.println(strNum3);
		int companyNum1=Integer.valueOf(strNum3.substring(1,3));
		du.waitFor(2000);
		du.what("deletecompany").click();
		du.waitForElementPresent("delcompanysurebtn");
		du.what("delcompanysurebtn").click();
		du.waitFor(2000);
		String strNum2=du.what("companytotal").getText().replaceAll("\\s", "");
		int companyNum2=Integer.valueOf(strNum2.substring(1,3));
		Assert.assertEquals(companyNum1-companyNum2==1, true);
	}
}
