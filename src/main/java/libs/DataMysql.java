package libs;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;



public class DataMysql {
	 public static Object[][] getdbData(String tableName) throws ClassNotFoundException, SQLException{
		    //设定mysql驱动
		    Class.forName("com.mysql.jdbc.Driver");
		    System.out.println("加载驱动成功");
		    //建立数据库连接
		    Connection conn=DriverManager.getConnection("jdbc:mysql://196.168.22.73:3306/testbase&autoReconnect=true&failOverReadOnly=false  ","root","123456");
		    System.out.println("链接数据库成功");
		    //判断数据库连接是否成功
		    if(!conn.isClosed()){
		      System.out.println("数据库连接成功");
		    }else {
		      System.out.println("数据库连接失败");
		    }
		       //创建Statement对象可以用对应的方法executeQuery(sql语句)获取测试数据
		    Statement sta=conn.createStatement();
		    //创建一个结果集存放数据库执行完sql的数据
		    ResultSet rs=sta.executeQuery("select* from "+tableName);
		       //声明存放泛型string数组的list对象
		    List<Object[]>listStr=new ArrayList<Object[]>();
		    int colNum=rs.getMetaData().getColumnCount();
		    while(rs.next()){
		      String[]str=new String[colNum];
		      for (int i = 0; i < str.length; i++) {
		        System.out.println(rs.getString(i+1).toString());
		        //resultSet数据集的getString下标是从1开始的
		        str[i]=rs.getString(i+1);
		      }
		      listStr.add(str);
		    } 
		    //关闭数据集
		    rs.close();
		    //关闭连接
		    conn.close();
		       //将list对象数据转换成二维数组
		    Object[][]results=new Object[listStr.size()][];
		    for(int i=0;i<listStr.size();i++){
		      results[i]=listStr.get(i);
		    }
		    return results;
		      }
	 
	 
		public static void main(String[] args) throws ClassNotFoundException, SQLException{
			DataMysql test = new DataMysql();
			System.out.println(test.getdbData("user"));
			
		}
}
