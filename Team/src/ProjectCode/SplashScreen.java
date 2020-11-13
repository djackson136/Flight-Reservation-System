package ProjectCode;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class SplashScreen extends JFrame {
	
	private JLabel progress;
	private JProgressBar progressBar;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		SplashScreen splash = new SplashScreen();
		splash.setVisible(true);
		
		/*EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});*/
		
		try {
			for (int i = 0; i <= 100; i++) {
				Thread.sleep(60);
				splash.progressBar.setValue(i);
				splash.progress.setText(Integer.toString(i) + "%");
			}
		} catch(Exception e) {
		}
		
		splash.dispose();
	}
	
	

	/**
	 * Create the frame.
	 */
	public SplashScreen() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 501, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(72, 209, 204));
		panel.setForeground(new Color(72, 209, 204));
		panel.setBounds(0, 0, 501, 278);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Flight Reservation System");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Avenir Next", Font.PLAIN, 24));
		lblNewLabel.setBounds(108, 38, 290, 70);
		panel.add(lblNewLabel);
		
		progressBar = new JProgressBar();
		progressBar.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		progressBar.setForeground(new Color(255, 255, 255));
		progressBar.setBounds(149, 164, 212, 55);
		panel.add(progressBar);
		
		progress = new JLabel("");
		progress.setHorizontalAlignment(SwingConstants.CENTER);
		progress.setFont(new Font("Lao Sangam MN", Font.PLAIN, 22));
		progress.setForeground(new Color(105, 105, 105));
		progress.setBounds(193, 130, 125, 35);
		panel.add(progress);
	}
}
