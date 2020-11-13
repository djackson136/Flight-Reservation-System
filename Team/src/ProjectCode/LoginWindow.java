package ProjectCode;

import javax.swing.*;
import javax.swing.border.*;

public class LoginWindow extends JFrame {

	private JPanel contentPane;
	private JTextField userText;
	private JPasswordField passText;
	
	// Launch the application.
	/*public static void main(String[] args) {
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
	*/
	
	// Create the frame.
	public LoginWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(30, 30, 30, 30));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel userLabel = new JLabel("Username:");
		userLabel.setBounds(115, 106, 66, 16);
		contentPane.add(userLabel);
		
		userText = new JTextField();
		userText.setBounds(193, 104, 114, 20);
		contentPane.add(userText);
		userText.setColumns(10);
		
		JLabel passLabel = new JLabel("Password:");
		passLabel.setBounds(118, 131, 63, 16);
		getContentPane().add(passLabel);
		
		passText = new JPasswordField();
		passText.setBounds(193, 126, 114, 26);
		contentPane.add(passText);
		
		JButton loginButton = new JButton("Login");
		loginButton.setBounds(162, 158, 71, 29);
		getContentPane().add(loginButton);
		
		JButton registerButton = new JButton("Register");
		registerButton.setBounds(162, 184, 82, 29);
		getContentPane().add(registerButton);
	}
}
