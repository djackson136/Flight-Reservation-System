package ProjectCode;

import java.awt.EventQueue;
import java.sql.*;

public class Main {
	static Connection connection = null;
	static String databaseName = "";
	static String url = "jdbc:mysql://localhost:3306/" + databaseName;
	
	static String username = "root";
	static String password = "development";
	
	void createConnection() {
		
	}
	
	public static void main(String[] args) {
		SplashScreen splash = new SplashScreen();
		splash.setVisible(true);
		SplashScreen.showProgress(splash);
		
		
		splash.setVisible(false);
		splash.dispose();
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginWindow frame = new LoginWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		/*
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(url, username, password);
			PreparedStatement ps = connection.prepareStatement("INSERT INTO customer.logincustomer (username, password) VALUES (?, ?);");
			ps.setString(1, x);
			ps.setString(2, x);
			int x = ps.executeUpdate();
			if (x > 0)
				System.out.println("Login Successful");
			else
				System.out.println("Login Failed");
		} catch (Exception e) {
			System.out.println(e);
		}
		*/

	}

}
