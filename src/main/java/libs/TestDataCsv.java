package libs;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import au.com.bytecode.opencsv.CSVReader;

public class TestDataCsv {
	private String filepath;
	private String nextLine[];
	List<String[]> approvalList = new ArrayList<String[]>();
	CSVReader reader;
	
	private String value;
	
	public TestDataCsv(String filepath){
		this.filepath = filepath;
	}
	
	public String getTestData(String columnName,String lineName){
	
			try {
				reader = new CSVReader(new FileReader(filepath));
				try {
					while((nextLine = reader.readNext()) !=null){
						approvalList.add(nextLine);
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	
			
			int count = 0;
			int index = 0;
			for(String j:approvalList.get(0)){
				count++;
				if(j.equals(columnName)){
					index = count-1;
				}
			}
			
			for(String[] i : approvalList){
				if(i[0].equals(lineName))
					value = i[index].toString();
			}
			}catch (FileNotFoundException e){
				e.printStackTrace();
			}
			return value;
}	
	public static void main(String[] args){
		TestDataCsv a = new TestDataCsv(System.getProperty("user.dir")+"\\src\\test\\java\\testData\\aaaa.csv");
		System.out.println(a.getTestData("password", "3"));
		
	}
}				

