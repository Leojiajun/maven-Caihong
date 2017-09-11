package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class factoryManage {
	private WebDriver driver;
	
	@FindBy(xpath="html/body/div[1]/div/div[1]/div[1]/ul/li[7]/div/div[1]/span")
	private WebElement baseData;
	
	@FindBy(xpath="html/body/div[1]/div/div[1]/div[1]/ul/li[7]/div/div[2]/ul/li[2]/div")
	private WebElement factory;
	
	
	@FindBy(xpath="html/body/div[1]/div/div[1]/div[2]/div[2]/div/div/div[2]/div/button")
	private WebElement add;
	
	@FindBy(xpath="html/body/div[1]/div/div[1]/div[2]/div[2]/div/div/div[1]/div/div/i")
	private WebElement searchbtn;
	
	@FindBy(xpath="html/body/div[1]/div/div[1]/div[2]/div[2]/div/div/div[1]/div/div/input")
	private WebElement searchinput;
	
	@FindBy(xpath="html/body/div[1]/div/div[1]/div[2]/div[2]/div/table/tbody/tr[1]/td[5]/a[2]")
	private WebElement delBtn;
	
	public factoryManage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void pressbasedata(){
		baseData.click();
	}
	
	public void pressfacmanage(){
		factory.click();
	}
	
	public void pressadd(){
		add.click();
	}
	public void presssearch(){
		searchbtn.click();	
	}
	public void setsearch(String hh){
		searchinput.sendKeys(hh);
	}
	
	public void delFacy(){
		delBtn.click();
	}
}
