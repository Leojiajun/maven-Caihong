package libs;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import org.openqa.selenium.*;

public class Tools {
	//文本框输入内容
	public static void input(By by,String value,WebDriver driver){
		WebElement input=driver.findElement(by);
		input.sendKeys(Keys.BACK_SPACE);
		input.sendKeys(value);
		  }
	
	//按钮点击
	public static void button(By by,WebDriver driver){
		WebElement button=driver.findElement(by);
		button.click();
		  }
	//当前时间加1天
		public static String getNetDay(Date date){
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar calendar=Calendar.getInstance();
			calendar.setTime(date);
			calendar.add(Calendar.DAY_OF_MONTH, +1);//+1今天的时间加一天
			date=calendar.getTime();
			return df.format(date);
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
