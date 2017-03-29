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
		loginpage.openUrl("http://192.168.8.7:8080/rainbow/pages/login.html");//输入url
		loginpage.setUsername("wujiajun");//�����û���
		loginpage.setPassword("111111");//��������
		loginpage.prssLogbtn();//�����¼
		wait.waitFor(5000);//�ȴ�5��
		Assert.assertEquals(loginpage.yanzheng().isDisplayed(), true);//��֤�Ƿ��¼�ɹ�������ĳԪ���򷵻�true
	}
	
	@Test(dependsOnMethods = {"Login"})
	public void pdcManage(){
		ProductPage productpage = new ProductPage(driver);
		productpage.pressPmanage();//�����������ť
		wait.waitFor(3000);//�ȴ�3��
		productpage.pressPplan();//��������ƻ���ť
		wait.waitFor(3000);
		productpage.presswuhan();//����人�ֹ�˾
		productpage.pressnew();//����½�
		wait.waitFor(5000);//�ȴ�5��
		Assert.assertEquals(productpage.yanzheng().isDisplayed(), true);//��֤�Ƿ񵯳�ҳ��ɹ����ڵ���ҳ��ȡһ��Ԫ�أ��ж����Ƿ����

	
	}
	
	@Test(dependsOnMethods = {"pdcManage"})
	public void newPlan(){
		String Pnumber = null;
		WebElement dd1 = driver.findElement(By.xpath("html/body/div[1]/div[3]/section[2]/div/div[2]/div/div[2]/div/div[3]/div[2]/div"));
		String s1 = dd1.getText();
		int sub1 = Integer.valueOf(s1.substring(17, s1.length()-1).trim());//��ȡ�ַ����е�һ��������Ϊint����
		wait.waitFor(3000);

		NewPdcPlanPage nppp = new NewPdcPlanPage(driver);//newһ���½������ƻ��ĵ�ҳ��
		
		//��ȡ��ǰʱ�䣬������������Ϊ�����ƻ����κţ���������ÿ�����κŶ���һ��
		SimpleDateFormat sdf = new SimpleDateFormat();
		String layout = "yyyyMMddHHmmss";
		sdf.applyPattern(layout);
		Calendar c1 = Calendar.getInstance();
		Pnumber = sdf.format(c1.getTime());
		
		nppp.setPlanNumber("WH"+Pnumber);//�������κ����롰WH+ʱ�䣨�������룩��
		
		//����ѡ��
		WebElement ss = driver.findElement(By.xpath(".//select[@id='DTE_Field_workshopCode']"));
		Select sel = new Select(ss);
		sel.selectByValue("00002");
		
		nppp.pressconfirmbtn();//
		
		wait.waitFor(5000);
		WebElement dd2 = driver.findElement(By.xpath("html/body/div[1]/div[3]/section[2]/div/div[2]/div/div[2]/div/div[3]/div[2]/div"));
		String s2 = dd2.getText();
		int sub2 = Integer.valueOf(s2.substring(17, s2.length()-1).trim());//�ٴν�ȡ������ַ����������е�������Ϊint����
		int a = sub2-sub1;//���ε��������������Ҫ����1
		System.out.println("*********"+a);
		Assert.assertEquals(a==1, true);//�жϵ���1˵���½��ɹ�
	}
	
	@AfterClass
	public void release(){
		driver.quit();
	}
}
