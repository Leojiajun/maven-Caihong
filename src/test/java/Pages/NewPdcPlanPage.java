package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
//在生产批次中新建产品页
public class NewPdcPlanPage {
	private WebDriver driver;
	
	@FindBy(xpath="//input[@id='DTE_Field_batchCode']")//产品批次输入框
	private WebElement pdcnumber;
	
	@FindBy(xpath="html/body/div[3]/div/div/div/div[4]/div[3]/button")//确定按钮
	private WebElement confirm;
	
	public NewPdcPlanPage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}	
	
	public void setPlanNumber(String num){
		pdcnumber.sendKeys(num);
	}
	
	public void pressconfirmbtn(){
		confirm.click();
	}
}
