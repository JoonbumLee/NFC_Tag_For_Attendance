import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

//This method is for getting data from database.
//Using mysql query to get data from database. 
public class getDataFromDB {
	//This method is for average.
	public static ArrayList<DataModel> getData() {
		ArrayList<DataModel> data = new ArrayList<DataModel>();
		try {
			Class.forName ("com.mysql.jdbc.Driver");
			Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/termproject", "root", "gudwls12");
	
			String queryStatement = "select avg(D1.data) AS AVGDATA, D1.city_num, D2.city_name from fine_dust_data D1" 
									+" LEFT OUTER JOIN"
									+" fine_dust_city D2"
									+" ON D1.city_num = D2.city_num"
									+" GROUP BY city_num";
			PreparedStatement psmt = con.prepareStatement(queryStatement);
					
			ResultSet rs = psmt.executeQuery();
			
			while(rs.next() ) {
				DataModel d = new DataModel();
				d.city = rs.getString("city_name");
				d.data = rs.getDouble("AVGDATA");
				System.out.println( "avg " + d.data );
				data.add(d);
			}
					
		}
		catch(Exception e) {
			e.printStackTrace();
		}
				
		return data;
	}
	//This method is for max.
	public static ArrayList<DataModel> getMAXData() {
		ArrayList<DataModel> data = new ArrayList<DataModel>();
		try {
			Class.forName ("com.mysql.jdbc.Driver");
			Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/termproject", "root", "gudwls12");
	
			String queryStatement2 = "select max(D1.data) AS MAXDATA, D1.city_num, D2.city_name from fine_dust_data D1"
					 +" LEFT OUTER JOIN"
					 +" fine_dust_city D2"
					 +" ON D1.city_num = D2.city_num"
					 +" GROUP BY city_num";
			
			PreparedStatement psmt2 = con.prepareStatement(queryStatement2);
			
			ResultSet rs2= psmt2.executeQuery();
			
			while(rs2.next() ){
				DataModel d2 = new DataModel();
				d2.city = rs2.getString("city_name");
				d2.data = rs2.getDouble("MAXDATA");
				
				data.add(d2);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return data;
	}
	//this method is for city data for during 4years.
	public static ArrayList<DataModel2> getCityData(String cityName) {
		ArrayList<DataModel2> data = new ArrayList<DataModel2>();
		try {
			Class.forName ("com.mysql.jdbc.Driver");
			Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/termproject", "root", "gudwls12");
	
			String queryStatement2 = "select city_num,city_name,year,month,data"
					+" from fine_dust_city a natural join fine_dust_data c natural join"
					+" fine_dust_date b where a.city_num = c.city_num and b.num = c.date_num"
					+" and a.city_num =(select city_num from fine_dust_city"
					+" where city_name = '"+cityName+"');";
			
			
			PreparedStatement psmt3 = con.prepareStatement(queryStatement2);
			
			ResultSet rs3= psmt3.executeQuery();
			
			while(rs3.next() ){
				DataModel2 d3 = new DataModel2();
				d3.city_name = rs3.getString("city_name");
				d3.data = rs3.getDouble("data");
				d3.city_num = rs3.getInt("city_num");
				d3.year = rs3.getString("year");
				d3.month = rs3.getString("month");
						
				data.add(d3);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return data;
	}
}

