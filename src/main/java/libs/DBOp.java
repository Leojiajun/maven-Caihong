package libs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBOp {
	private Statement stat = null;
	private ResultSet rsq = null;
	private String tablename = null;
	
	public DBOp(String tablename){
		this.tablename=tablename;
	}
	
	public void connect(){
		try {
			Class.forName("org.sqlite.JDBC");
			Connection conn;
			try {
				//自己在Tools下新建一个数据库mylocator.sqlite，用火狐中的sqlite去链接它
				conn = DriverManager.getConnection("jdbc:sqlite:"+System.getProperty("user.dir")+"\\Tools\\mylocator.sqlite");
				stat = conn.createStatement();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
	public String getLocatorElement(String locatorname){
		String element = null;
		try {
			rsq = stat.executeQuery("select * from "+tablename+" where WebElementName = '"+locatorname+"';");
			while(rsq.next()){
				element = rsq.getString("XpathOrCss");//获取“XpathOrCss”字段的值
			}
			rsq.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return element;
		
	}
	
	//select * from LoginPage where WebElementName='username'
	public static void main(String[] args){
		DBOp test = new DBOp("LoginPage");
		test.connect();
		System.out.println(test.getLocatorElement("username"));
	}

}

