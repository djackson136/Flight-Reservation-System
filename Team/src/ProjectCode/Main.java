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
	}
}
