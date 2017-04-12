package testcase;

import java.text.SimpleDateFormat;
import java.util.Calendar;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Pages.LoginPage;
import Pages.NewPdcPlanPage;
import Pages.ProductPage;
import libs.BrowserType;
import libs.Browsers;
import libs.DBOp;
import libs.Do;
import libs.TestDataCsv;
import libs.Wait;

public class loginCaihong {
	private WebDriver driver;
	private Do du;
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
		TestDataCsv data = new TestDataCsv(System.getProperty("user.dir")+"\\src\\test\\java\\testData\\aaaa.csv");
		loginpage.openUrl("http://192.168.8.7:8080/rainbow/pages/login.html");//输入url
		wait.waitFor(2000);
		loginpage.setUsername(data.getTestData("username", "1"));//输入用户名
		loginpage.setPassword(data.getTestData("password", "1"));//输入密码
		loginpage.prssLogbtn();//点击登录
		wait.waitFor(5000);//等待5秒
		Assert.assertEquals(loginpage.yanzheng().isDisplayed(), true);//断点判断某元素是否出现，出现则返回true
	}
	
	@Test(dependsOnMethods = {"Login"})
	public void pdcManage(){
		ProductPage productpage = new ProductPage(driver);
		productpage.pressPmanage();
		wait.waitFor(3000);
		productpage.pressPplan();
		wait.waitFor(3000);
		productpage.presswuhan();
		productpage.pressnew();
		wait.waitFor(5000);
		Assert.assertEquals(productpage.bPoint().isDisplayed(), true);//断点验证是否弹出创建框

	}
	
	@Test(dependsOnMethods = {"pdcManage"})
	public void newPlan(){
		String Pnumber = null;
		WebElement dd1 = driver.findElement(By.xpath("html/body/div[1]/div[3]/section[2]/div/div[2]/div/div[2]/div/div[3]/div[2]/div"));
		String s1 = dd1.getText();
		int sub1 = Integer.valueOf(s1.substring(17, s1.length()-1).trim());//截取当前生产计划的总数
		wait.waitFor(3000);

		NewPdcPlanPage nppp = new NewPdcPlanPage(driver);
		
		//将当前时间，“年月日时分秒”作为生产批次号，这样保证每次都不一样
		SimpleDateFormat sdf = new SimpleDateFormat();
		String layout = "yyyyMMddHHmmss";
		sdf.applyPattern(layout);
		Calendar c1 = Calendar.getInstance();
		Pnumber = sdf.format(c1.getTime());
		
		nppp.setPlanNumber("WH"+Pnumber);//输入生产计划名称
		
		//下拉操作
		WebElement ss = driver.findElement(By.xpath(".//select[@id='DTE_Field_workshopCode']"));
		Select sel = new Select(ss);
		sel.selectByValue("00002");
		
		nppp.pressconfirmbtn();//
		
		wait.waitFor(5000);
		WebElement dd2 = driver.findElement(By.xpath("html/body/div[1]/div[3]/section[2]/div/div[2]/div/div[2]/div/div[3]/div[2]/div"));
		String s2 = dd2.getText();
		int sub2 = Integer.valueOf(s2.substring(17, s2.length()-1).trim());//再次获取总数
		int a = sub2-sub1;
		System.out.println("*********"+a);
		Assert.assertEquals(a==1, true);//两次总数相减等于1，说明新建成功
	}
	
	//在生产批次中添加产品
	@Test(dependsOnMethods={"newPlan"})
	public void newPtoPlan(){
		ProductPage productpage = new ProductPage(driver);
		productpage.pressnewtoP();
		wait.waitFor(3000);
		driver.findElement(By.xpath("//span[contains(text(),'新建')]")).click();
		wait.waitFor(3000);
		WebElement ss2 = driver.findElement(By.xpath("//select[@id='DTE_Field_productCode']"));
		Select se2 = new Select(ss2);
		se2.selectByValue("123344");
		driver.findElement(By.xpath("//input[@id='DTE_Field_planNumber']")).sendKeys("150");
		driver.findElement(By.xpath("//button[@tabindex='0']")).click();
		System.out.println("*********b");
	}
	
	@AfterClass
	public void release(){
		driver.quit();
	}
}
