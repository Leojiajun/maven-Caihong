package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class addFactory {
	private WebDriver driver;
	
	@FindBy(xpath="//input[@placeholder='请输入工厂名称']")
	private WebElement fname;
	
	@FindBy(xpath="//input[@placeholder='请输入工厂地址']")
	private WebElement faddress;
	
	@FindBy(xpath="html/body/div[3]/div[2]/div/div/div[1]/div/form/div[3]/div/div/div[1]/input")
	private WebElement choice;
	
	@FindBy(xpath="html/body/div[3]/div[2]/div/div/div[1]/div/form/div[3]/div/div/div[2]/ul[2]/li[3]")
	private WebElement company;
	
	@FindBy(xpath="html/body/div[3]/div[2]/div/div/div[2]/div/button")
	private WebElement OK;
	
	public addFactory(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void setFname(String name){
		fname.sendKeys(name);
	}
	
	public void setFaddress(String adrs){
		faddress.sendKeys(adrs);
	}
	public void pressChoice(){
		choice.click();
	}
	public void pressCompany(){
		company.click();
	}
	public void pressOKbtn(){
		OK.click();
	}
}
