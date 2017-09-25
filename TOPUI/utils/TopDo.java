package utils;

import java.io.UnsupportedEncodingException;
import java.sql.Driver;
import java.sql.DriverManager;
import java.util.Enumeration;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import libs.ParseProperties;
import libs.Wait;

public class TopDo {
	private static WebDriver driver;
	private ParseProperties locator = new ParseProperties(System.getProperty("user.dir")+"/Tools/TopLocators.properties");
	private Wait waiter;
    
	
	public static WebDriver getDriver() {  
        return driver;  
    } 
	
	public TopDo(WebDriver driver){
		this.driver = driver;
		waiter = new Wait(driver);
	}
	public WebElement what(String locatorname){
		try {
			return driver.findElement(By.xpath(new String(locator.getValue(locatorname).getBytes(),"utf-8")));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return null;
	}
	
	public WebElement csswhat(String locatorname){
		try {
			return driver.findElement(By.ByCssSelector.cssSelector(new String(locator.getValue(locatorname).getBytes(),"utf-8")));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return null;
	}
	public List<WebElement> whats(String locatorname){
		return driver.findElements(By.xpath(locator.getValue(locatorname)));
	}
	public void waitForElementPresent(String locatorname){
		waiter.waitForElementPresent(locator.getValue(locatorname));
	}
	public void waitForElementIsEnable(String locatoename){
		waiter.waitForElementIsEnable(locator.getValue(locatoename));
	}
	public void waitFor(long timeout){
		waiter.waitFor(timeout);
	}
	
    //随机生成字符串
    public static String getRandomString(int length) {//length表示生成字符串的长度
      String base = "abcdefghijklmnopqrstuvwxyz0123456789";
      Random random = new Random();
      StringBuffer sb = new StringBuffer();
      for (int i = 0; i < length; i++) {
        int number = random.nextInt(base.length());
        sb.append(base.charAt(number));
      }
      return sb.toString();
    }
    
}
