package WeAPI;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import libs.BrowserType;
import libs.Browsers;
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
		Assert.assertEquals("https://www.sogou.com/", CurrentPageUrl);
	}
	
	@Test/**双击按钮***/
	public void doubleClick(){
		driver.get("file:///E:/%E8%87%AA%E5%8A%A8%E5%8C%96%E6%B5%8B%E8%AF%95/html%E6%96%87%E4%BB%B6/doubleClick.html");
		WebElement inputBox = driver.findElement(By.xpath("//input[@id='inputBox']"));
		Actions builder = new Actions(driver);
		builder.doubleClick(inputBox).build().perform();
		
	}
	
	@Test
	public void baidu(){
		driver.get(url2);
		
	}
}
