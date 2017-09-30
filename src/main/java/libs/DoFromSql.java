package libs;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DoFromSql {
	private WebDriver driver;
	private Wait waiter;
	private DBOp test = new DBOp("TOPDATA");
	
	
	public DoFromSql(WebDriver driver){
		this.driver = driver;
		waiter = new Wait(driver);
		test.connect();
		System.out.println("连接数据库成功");
	}
	
	
	public WebElement what(String locatorname){		

		return driver.findElement(By.xpath(test.getLocatorElement(locatorname)));	
	}
	
	public WebElement csswhat(String locatorname){

		return driver.findElement(By.ByCssSelector.cssSelector(test.getLocatorElement(locatorname)));	
	}
	
}
