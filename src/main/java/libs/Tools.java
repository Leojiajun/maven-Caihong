package libs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import org.openqa.selenium.*;

public class Tools {
	private DBOp dbdata = new DBOp("TOPDATA");
	private WebDriver driver;
	private ParseProperties locator = new ParseProperties(System.getProperty("user.dir")+"/Tools/TopLocators.properties");

	
	public Tools(WebDriver driver){
		this.driver = driver;
		dbdata.connect();
	}
	
	//文本框输入内容
	public void input(String locatorname,String value){
		
		try{
		WebElement input=driver.findElement(By.xpath(dbdata.getLocatorElement(locatorname)));
		input.sendKeys(Keys.BACK_SPACE);
		input.sendKeys(value);
		}catch(NoSuchElementException e){
		    WebElement input=driver.findElement(By.ByCssSelector.cssSelector(dbdata.getLocatorElement(locatorname)));
			input.sendKeys(Keys.BACK_SPACE);
			input.sendKeys(value);
		}
		  }
	
	//按钮点击
	public  void button(String locatorname){

		try{
		WebElement button=driver.findElement(By.xpath(dbdata.getLocatorElement(locatorname)));
		button.click();
		}catch(NoSuchElementException e){
		  WebElement button=driver.findElement(By.ByCssSelector.cssSelector(dbdata.getLocatorElement(locatorname)));
		  button.click();
		}
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
	
		// 从数据库里边获取数据
		  public static String getdbData(String localtername, String tablename)
		      throws ClassNotFoundException, SQLException {
		    String elementpath = null;
		    // 设定mysql驱动
		    Class.forName("com.mysql.jdbc.Driver");
		    // 建立数据库连接
		    Connection conn = DriverManager.getConnection(
		        "jdbc:mysql://127.0.0.1:3306/testdb", "root", "123456");
		    // 判断数据库连接是否成功
		    if (!conn.isClosed()) {
		      System.out.println("数据库连接成功");
		    } else {
		      System.out.println("数据库连接失败");
		    }
		    // 创建Statement对象可以用对应的方法executeQuery(sql语句)获取测试数据
		    Statement sta = conn.createStatement();
		    // 创建一个结果集存放数据库执行完sql的数据
		    ResultSet rs = sta.executeQuery("select * from " + tablename
		        + " where elementname = '" + localtername + "';");

		    while (rs.next()) {
		      elementpath = rs.getString("path");// 获取“XpathOrCss”字段的值
		    }

		    // 关闭数据集
		    rs.close();
		    // 关闭连接
		    conn.close();

		    return elementpath;

		  }

}
