package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
//生产计划页
public class ProductPage {
	private WebDriver driver;
	
	@FindBy(xpath="html/body/div[1]/div[2]/aside/section/ul/li[3]/a/span[1]")//生产管理按钮
	private WebElement pdcmanage;
	
	@FindBy(xpath=".//*[@id='productionPlanNavLink']")//生产计划按钮
	private WebElement pdcplan;
	
	@FindBy(xpath="html/body/div[1]/div[3]/section[2]/div/div[1]/ul/li/ul/li[5]/a")//武汉分公司按钮
	private WebElement wuhan;
	
	@FindBy(xpath="html/body/div[1]/div[3]/section[2]/div/div[2]/div/div[2]/div/div[1]/div[1]/div/a[1]/span")//新建按钮
	private WebElement newone;
	
	@FindBy(xpath="//tr[1]/td[8]/a")//添加产品按钮
	private WebElement newonetoP;
	
	@FindBy(xpath="html/body/div[3]/div/div/div/div[3]/div/form/div/div[1]/label")//断点，判断是否弹出创建框
	private WebElement test;
	
	public ProductPage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void pressPmanage(){
		pdcmanage.click();
	}
	public void pressPplan(){
		pdcplan.click();
	}
	public void presswuhan(){
		wuhan.click();
	}
	public void pressnew(){
		newone.click();
	}
	public void pressnewtoP(){
		newonetoP.click();
	}
	public WebElement bPoint(){
		return test;
	}
}
