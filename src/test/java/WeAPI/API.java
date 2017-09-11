package WeAPI;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.os.WindowsUtils;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import libs.BrowserType;
import libs.Browsers;
import libs.Switch;
import libs.Wait;

public class API {
	private WebDriver driver=null;
	private Browsers browser;
	private Wait wait;
	private String url1="https://www.sogou.com/";
	private String url2="https://www.baidu.com/";
	
	@BeforeClass
	public  void openChrome(){
		Browsers browser = new Browsers(BrowserType.chrome);
		driver = browser.driver;
		wait = new Wait(driver);
	}
	@Test /**访问一个网站**/
	public void visitUrl(){
		driver.get(url1);
	}
	
	@Test  /**返回上一个访问的网页**/
	public void visitRecentURl(){
		driver.navigate().to(url1);
		driver.navigate().to(url1);
		driver.navigate().back();
	}
	
	@Test/**获取当前页面的URL**/
	public void getCurrentOageUrl(){
		driver.get(url1);
		String CurrentPageUrl = driver.getCurrentUrl();
		System.out.println(CurrentPageUrl);
		//断言地址是否为URL1
		Assert.assertEquals(CurrentPageUrl, "https://www.sogou.com/");
	}
	
	@Test/**双击按钮***/
	public void doubleClick(){
		driver.get("file:///E:/%E8%87%AA%E5%8A%A8%E5%8C%96%E6%B5%8B%E8%AF%95/html%E6%96%87%E4%BB%B6/doubleClick.html");
		WebElement inputBox = driver.findElement(By.xpath("//input[@id='inputBox']"));
		Actions builder = new Actions(driver);
		builder.doubleClick(inputBox).build().perform();
		
	}
	
	@Test/**杀掉浏览器进程**/
	public void operateWindowsProcess(){
		WindowsUtils.tryToKillByName("firefox.exe");//杀掉firefox进程
		WindowsUtils.tryToKillByName("iexplore.exe");//杀掉IE进程
		WindowsUtils.tryToKillByName("chrome.exe");//杀掉chrome进程		
	}
	
	@Test/**截图**/
	public void screen(){
		driver.get(url1);
		//调用getScreenshotAs方法把当前浏览器打开的页面进行截图，保存到一个File对象中
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(scrFile, new File("d:\\test\\test.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	@Test/**显示等待**/
	public void testExplicitWait(){
		driver.get("E:/自动化测试/html文件/ExplicitWait.html");
		WebDriverWait wait = new WebDriverWait(driver,10);//声明WebDriverWait对象，设定触发条件的最长的等待时间为10秒
		wait.until(ExpectedConditions.titleContains("水果"));//调用ExpectedConditions的titleContains方法判断页面title属性是否包含水果两字
		Assert.assertEquals(driver.getTitle(), "你喜欢的水果");
	    }	
	@Test/**页面切换**/
	public void windows(){
		driver.get("E:/自动化测试/html文件/popupwindows.html");
		WebElement sougouLink= driver.findElement(By.xpath("html/body/a"));
		sougouLink.click();
		Switch sw = new Switch(driver);//调用switch
		sw.toSpecificWindow("搜狗");//切换到页面标题包含“搜狗”的页面
		driver.findElement(By.xpath("html/body/header/div/div/div/div[2]/nav/div/div/div/div/div/ul/li[3]/a")).click();
		}
		
	
	
	
}
