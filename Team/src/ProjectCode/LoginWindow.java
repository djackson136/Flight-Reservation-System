package ProjectCode;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class LoginWindow extends JFrame {

	private JPanel loginPane;
	private JTextField userText;
	private JPasswordField passText;
	private static JButton loginButton;
	private JButton registerButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
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

	/**
	 * Create the frame.
	 */
	public LoginWindow() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		loginPane = new JPanel();
		loginPane.setBorder(new EmptyBorder(30, 30, 30, 30));
		setContentPane(loginPane);
		loginPane.setLayout(null);
		
		JLabel userLabel = new JLabel("Username:");
		userLabel.setBounds(115, 106, 66, 16);
		loginPane.add(userLabel);
		
		userText = new JTextField();
		userText.setBounds(193, 104, 114, 20);
		loginPane.add(userText);
		userText.setColumns(10);
		
		JLabel passLabel = new JLabel("Password:");
		passLabel.setBounds(118, 131, 63, 16);
		getContentPane().add(passLabel);
		
		passText = new JPasswordField();
		passText.setBounds(193, 126, 114, 26);
		loginPane.add(passText);
		
		loginButton = new JButton("Login");
		loginButton.setBounds(162, 158, 71, 29);
		getContentPane().add(loginButton);
		
		registerButton = new JButton("Register");
		registerButton.setBounds(162, 184, 82, 29);
		getContentPane().add(registerButton);
		
		ActionListener loginButtonListener = new RegisterButtonListener();
		registerButton.addActionListener(loginButtonListener);
	}
	public class RegisterButtonListener implements ActionListener {
		@Override
	public void actionPerformed(ActionEvent e)
	{
		RegisterWindow registerPane = new RegisterWindow();
			
		registerButton = (JButton)e.getSource();		
		loginPane.setVisible(false);
		registerPane.setVisible(true);
	}
	}
}

