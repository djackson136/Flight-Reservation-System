package ProjectCode;

import java.awt.EventQueue;

public class Main {
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
