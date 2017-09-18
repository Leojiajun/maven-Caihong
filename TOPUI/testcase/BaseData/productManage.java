package testcase.BaseData;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.openqa.selenium.Alert;
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

public class productManage {
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
	}
	
	@Test(priority=1)//登录
	public void logIn(){
		loginTopPage logintop = new loginTopPage(driver);
		logintop.openUrl("https://top-stable.sao.so/#/login");
		logintop.setUsername("hy@sina.com");
		logintop.setPassword("123qwe");
		logintop.setCaptcha("aaaa");
		logintop.pressLoginbtn();
		wait.waitFor(1000);
		Assert.assertTrue(driver.getPageSource().contains("登录成功"));
		//Assert.assertEquals(logintop.testEle().isDisplayed(), true);
	}
	
	@Test(priority=2)//新建一个产品
	public void newProduct(){
		TopDo du = new TopDo(driver);
		du.what("basedata").click();
		wait.waitFor(2000);
		du.what("productmanage").click();
		wait.waitFor(2000);
		du.what("addproduct").click();
        //获取当前时间作为产品代码	
		SimpleDateFormat sdf = new SimpleDateFormat();
		String layout = "yyyyMMddHHmmss";
		sdf.applyPattern(layout);
		Calendar c1 = Calendar.getInstance();
		String Pnumber = sdf.format(c1.getTime());
		
		du.what("productcoding").sendKeys("PD"+Pnumber);
		du.what("productname").sendKeys("PN"+Pnumber);
		du.what("classification").click();
		du.what("wine").click();
		du.what("preserve").click();
		wait.waitFor(2000);
		Assert.assertTrue(driver.getPageSource().contains(Pnumber));	
	}
	
	@Test(priority=3)//删除一个产品
	public void delProduct(){
		TopDo du = new TopDo(driver);
//		du.what("basedata").click();
//		wait.waitFor(2000);
//		du.what("productmanage").click();
		driver.navigate().refresh();
		du.waitFor(2000);
		String strNum1 = du.what("producttotal").getText().replaceAll("\\s", "");
		int pNum1 = Integer.valueOf(strNum1.substring(1,3));//获取产品总数
		du.what("Pdelete").click();
		wait.waitFor(2000);
		du.csswhat("deleteproductmakesureBtn").click();
		wait.waitFor(2000);
		String strNum2 = du.what("producttotal").getText().replaceAll("\\s", "");
		int pNum2 = Integer.valueOf(strNum2.substring(1,3));//获取产品总数
		System.out.println(pNum1);
		System.out.println(pNum2);
		Assert.assertEquals(pNum1-pNum2==1, true);
	}
	
	@Test(priority=4)//导入产品
	public void leadinProduct() throws IOException{
		TopDo du = new TopDo(driver);
//		du.what("basedata").click();
//		wait.waitFor(2000);
//		du.what("productmanage").click();
		driver.navigate().refresh();
		wait.waitFor(2000);
		du.what("leadin").click();
		wait.waitFor(2000);
		//driver.findElement(By.className("ivu-upload-drag")).click();//使用Xpath定位
		//driver.findElement(By.ByCssSelector.cssSelector("i.ivu-icon-ios-cloud-upload")).click();//使用css定位
		du.csswhat("uploadchoicecss").click();
		wait.waitFor(2000);
		//使用autoIt编写的脚本上传文件
		Runtime.getRuntime().exec("D:/uploadrun/product.exe");
		wait.waitFor(30000);
		driver.findElement(By.ByCssSelector.cssSelector("div.cont-modal-footer > button.ivu-btn-primary")).click();//CSS定位。点击上传
		du.waitFor(5000);
	}
}
