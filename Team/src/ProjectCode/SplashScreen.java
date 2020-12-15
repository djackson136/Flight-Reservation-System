package ProjectCode;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

@SuppressWarnings("serial")
public class SplashScreen extends JFrame {

	private JLabel progress;
	private JProgressBar progressBar;
	private JPanel contentPane;

	public static void showProgress(SplashScreen s) {
		try {
			for (int i = 0; i <= 100; i++) {
				// Delays the execution time for 70 milliseconds
				Thread.sleep(80);
				// Displays the loading number
				s.progress.setText(Integer.toString(i) + "%");
				s.progressBar.setValue(i);
				
			}
		} catch (Exception e) {
		}
	}
	
	public SplashScreen() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 501, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(135, 206, 235));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel title = new JLabel("Flight Reservation System");
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setForeground(new Color(255, 255, 255));
		title.setFont(new Font("DIN Alternate", Font.BOLD, 28));
		title.setBounds(81, 52, 342, 70);
		contentPane.add(title);

		progressBar = new JProgressBar();
		progressBar.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		progressBar.setForeground(new Color(255, 255, 255));
		progressBar.setBounds(150, 157, 212, 55);
		contentPane.add(progressBar);

		progress = new JLabel("");
		progress.setHorizontalAlignment(SwingConstants.CENTER);
		progress.setFont(new Font("Lao Sangam MN", Font.PLAIN, 22));
		progress.setForeground(new Color(105, 105, 105));
		progress.setBounds(194, 125, 125, 35);
		contentPane.add(progress);
	}
}
