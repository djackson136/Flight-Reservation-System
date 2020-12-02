package ProjectCode;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;

import javax.swing.*;
import javax.swing.border.*;

@SuppressWarnings("serial")
public class LoginWindow extends JFrame {

	/**
	 * 
	 */
	private JPanel loginPane;
	private JTextField userText;
	private JPasswordField passText;
	private static JButton loginButton;
	private JButton registerButton;
	private static String username;
    private static String password;

	static Connection conn = null;

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
		loginButton.setBounds(115, 159, 71, 29);
		getContentPane().add(loginButton);

		registerButton = new JButton("Register");
		registerButton.setBounds(362, 243, 82, 29);
		getContentPane().add(registerButton);
		
		JButton admButton = new JButton("Admin Login");
		admButton.setBounds(193, 159, 117, 29);
		loginPane.add(admButton);
		
		registerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					RegisterWindow frame = new RegisterWindow();
					frame.setVisible(true);
				}
				catch (Exception Ex) {System.out.println(e);}	
				}
			});
		
		admButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection conn = DbConnection.connect();
				try {
					String user = userText.getText();
					String pass = passText.getText();
					setUsername(user);
					Statement stmt = conn.createStatement();
					String sql = "SELECT * FROM Admin Where Username = '"+user+"' AND Password = '"+pass+"' ";
					ResultSet rs = stmt.executeQuery(sql);
					
					if(rs.next()) {
						JOptionPane.showMessageDialog(null, "Login Successful");
					}else {
						JOptionPane.showMessageDialog(null, "Login Failed");
						conn.close();
					}
			} catch (Exception Ex) {System.out.println(e);}
			}
		});

		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection conn = DbConnection.connect();
				try {
					String user = userText.getText();
					String pass = passText.getText();
					Statement stmt = conn.createStatement();
					String sql = "SELECT * FROM RegisterCustomer Where Username = '"+user+"' AND Password = '"+pass+"' ";
					ResultSet rs = stmt.executeQuery(sql);
					
					if(rs.next()) {
						MainMenu frame = new MainMenu(user);
						frame.setVisible(true);
					}else {
						JOptionPane.showMessageDialog(null, "Login Failed");
						conn.close();
					}
			} catch (Exception Ex) {System.out.println(e);}
			}
		});
	}
}

