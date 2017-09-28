package testcase.BaseData;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import libs.BrowserType;
import libs.Browsers;
import libs.Do;
import libs.TestngRetryListener;
import libs.Wait;
import page.addFactory;
import page.factoryManage;
import page.loginTopPage;

@Listeners({ TestngRetryListener.class })
public class FacManage {
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
	
	@Test(priority=1)//登录
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
	
	@Test(priority=2)//新建一个工厂
	public void newFactory(){
		factoryManage fmanage = new factoryManage(driver);
		fmanage.pressbasedata();
		wait.waitFor(2000);
		fmanage.pressfacmanage();
		wait.waitFor(2000);
		fmanage.pressadd();
		wait.waitFor(2000);
		addFactory addF = new addFactory(driver);
		addF.setFname("test1");
		addF.setFaddress("上海");
		wait.waitFor(2000);
		addF.pressChoice();
		addF.pressCompany();
		wait.waitFor(2000);
		driver.findElement(By.ByCssSelector.cssSelector("div.cont-modal-footer>button")).click();
		wait.waitFor(2000);
		Assert.assertTrue(driver.getPageSource().contains("test1"));		
	}
	
	@Test(priority=3)//删除一个工厂
	public void DelFactory(){
		factoryManage fmanage = new factoryManage(driver);
		fmanage.pressbasedata();
		wait.waitFor(2000);
		fmanage.pressfacmanage();
		wait.waitFor(2000);
		String strNum = (driver.findElement(By.xpath("//div/div[2]/div[2]/div[1]/div/ul/span")).getText()).replaceAll("\\s", "");/***截取文字，去除所有空格*/
//		System.out.println(strNum);
//		int aa = strNum.indexOf("3");
//		System.out.println(aa);
		int fNum = Integer.valueOf(strNum.substring(1,2));/**截取工厂总数**/	
		fmanage.delFacy();
		driver.findElement(By.xpath("//div[2]/div/div/div/div/div[3]/button[2]")).click();
		wait.waitFor(2000);
		String strNum2 = (driver.findElement(By.xpath("//div/div[2]/div[2]/div[1]/div/ul/span")).getText()).replaceAll("\\s", "");/***截取文字，去除所有空格*/
		int fNum2 = Integer.valueOf(strNum2.substring(1,2));/**再次截取工厂总数**/
		Assert.assertEquals((fNum-fNum2)==1, true);
		}
	
}
