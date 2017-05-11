package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
//登录页面
public class LoginPage {
	private WebDriver driver;
	
	@FindBy(xpath="//input[@id='un']")//用户名输入框
	private WebElement username;
	
	@FindBy(xpath="html/body/form/fieldset[2]/input")//密码输入框
	private WebElement password;
	
	@FindBy(xpath="//input[@value='登录']")//登录按钮
	private WebElement logbtn;
	
	@FindBy(xpath="//span[@id='topUserName']")//登录后的验证元素
	private WebElement testid;
	
	public LoginPage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void openUrl(String url){
		driver.get(url);
	}
	
	public void setUsername(String name){
		username.sendKeys(name);
	}
	
	public void setPassword(String mima){
		password.sendKeys(mima);
	}
	
	public void pressLogbtn(){
		logbtn.click();
	}
	
	public  WebElement yanzheng(){
		return testid;
	}
	
	

}
