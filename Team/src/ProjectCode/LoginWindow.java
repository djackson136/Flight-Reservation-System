package ProjectCode;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;

import javax.swing.*;
import javax.swing.border.*;

@SuppressWarnings("serial")
public class LoginWindow extends JFrame {
	private JPanel loginPane;
	private JTextField userText;
	private JPasswordField passText;
	private static JButton loginButton;
	private JButton registerButton;
	private String username;
    private String password;
    private JLabel icon;

    // Launch the application
	public static void main(String[] args) {
		// Create and display the splash screen
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
	
	// Creates the frame
	public LoginWindow() {
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 494, 339);
		loginPane = new JPanel();
		loginPane.setBorder(new EmptyBorder(30, 30, 30, 30));
		setContentPane(loginPane);
		loginPane.setLayout(null);

		JLabel userLabel = new JLabel("Username:");
		userLabel.setFont(new Font("Avenir Next", Font.PLAIN, 14));
		userLabel.setBounds(155, 98, 82, 16);
		loginPane.add(userLabel);

		userText = new JTextField();
		userText.setBounds(236, 90, 114, 30);
		loginPane.add(userText);
		userText.setColumns(10);

		JLabel passLabel = new JLabel("Password:");
		passLabel.setFont(new Font("Avenir Next", Font.PLAIN, 14));
		passLabel.setBounds(155, 138, 71, 16);
		getContentPane().add(passLabel);

		passText = new JPasswordField();
		passText.setBounds(236, 130, 114, 30);
		loginPane.add(passText);

		loginButton = new JButton("Login");
		loginButton.setVerticalAlignment(SwingConstants.BOTTOM);
		loginButton.setFont(new Font("Apple Symbols", Font.PLAIN, 16));
		loginButton.setBounds(163, 184, 71, 32);
		getContentPane().add(loginButton);

		registerButton = new JButton("Register");
		registerButton.setVerticalAlignment(SwingConstants.BOTTOM);
		registerButton.setFont(new Font("Apple Symbols", Font.PLAIN, 16));
		registerButton.setBounds(373, 259, 82, 32);
		getContentPane().add(registerButton);
		
		JButton admButton = new JButton("Admin Login");
		admButton.setVerticalAlignment(SwingConstants.BOTTOM);
		admButton.setFont(new Font("Apple Symbols", Font.PLAIN, 16));
		admButton.setBounds(236, 184, 117, 32);
		loginPane.add(admButton);
		
		icon = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("/modern.png")).getImage();
		icon.setIcon(new ImageIcon(img));
		icon.setBounds(0, -64, 547, 328);
		loginPane.add(icon);
		
		// Go to register window
		registerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					RegisterWindow frame = new RegisterWindow();
					frame.setVisible(true);
				}
				catch (Exception Ex) {System.out.println(e);}	
				}
			});
		// Login button for admin(search admin database)
		admButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection conn = DbConnection.connect();
				try {
					username = userText.getText();
					password = passText.getText();
					Customer c1 = new Customer(username, password);
					Statement stmt = conn.createStatement();
					String sql = "SELECT * FROM Admins Where Username = '"+username+"' AND Password = '"+password+"' ";
					ResultSet rs = stmt.executeQuery(sql);
					
					if(rs.next()) {
						AdminFW frame = new AdminFW();
						frame.setVisible(true);
						dispose();
					}else {
						JOptionPane.showMessageDialog(null, "Login Failed");
						conn.close();
						System.out.println();
					}
			} catch (Exception Ex) {System.out.println(e);}
			}
			
		});
		// Login button for customers(search customers database)
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection conn = DbConnection.connect();
				try {
					username = userText.getText();
					password = passText.getText();
					Statement stmt = conn.createStatement();
					String sql = "SELECT * FROM Customers Where Username = '"+username+"' AND Password = '"+password+"' ";
					ResultSet rs = stmt.executeQuery(sql);
					
					if(rs.next()) {
						MainMenu frame = new MainMenu(username);
						frame.setVisible(true);
						dispose();
						
					}else {
						JOptionPane.showMessageDialog(null, "Login Failed");
						conn.close();
					}
			} catch (Exception Ex) {System.out.println(e);
				}finally {
					try {
						conn.close();
					} catch (SQLException Ex) {
						Ex.printStackTrace();
					}
				}
			}
		});
	}
}
				

