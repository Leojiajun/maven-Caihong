package testcase;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class testBaidu {
	@Test
	public static void test(){
		FirefoxDriver driver = new FirefoxDriver();
		driver.get("http://www.baidu.com");
		
	}

	
}
