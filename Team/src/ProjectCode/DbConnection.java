package ProjectCode;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnection {
	public static Connection connect() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://35.202.73.103:3306/Customer";
			String username = "daiv";
			String password = "project";
			Connection conn = DriverManager.getConnection(url, username, password);
			
			return conn;
		} catch (Exception e) {
			System.out.println("Connection Failed");
		}
		return null;
	}
}
