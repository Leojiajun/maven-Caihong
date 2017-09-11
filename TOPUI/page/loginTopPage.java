package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class loginTopPage {
	private WebDriver driver;
	
	@FindBy(xpath="//input[@placeholder='请输入账号']")
	private WebElement username;
	
	@FindBy(xpath="//input[@placeholder='请输入密码']")
	private WebElement password;
	
	@FindBy(xpath="//input[@placeholder='请输入验证码']")
	private WebElement CAPTCHA;
	
	@FindBy(xpath="//button[@type='button']")
	private WebElement loginbtn;
	
	@FindBy(xpath="html/body/div[1]/div/div[1]/div[2]/div[1]/div/div[1]/div/div")
	private WebElement forTestLog;
	
	public loginTopPage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void openUrl(String url){
		driver.get(url);
	}
	
	public void setUsername(String name){
		username.sendKeys(name);
	}
	
	public void setPassword(String pass){
		password.sendKeys(pass);
	}
	
	public void setCaptcha(String tt){
		CAPTCHA.sendKeys(tt);	
	}
	public void pressLoginbtn(){
		loginbtn.click();
	}
	public WebElement testEle(){
		return forTestLog;
	}
}
