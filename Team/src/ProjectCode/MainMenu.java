package ProjectCode;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Color;

public class MainMenu extends JFrame {

	private JPanel menuPane;
	private JLabel welcomeText;
	private JButton bookFlightButton;
	private JButton accountButton;
	private JButton logOutButton;


	public MainMenu() {
		
	}
	
	// Create the frame
	public MainMenu(String name) {
		setTitle("Main Menu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 494, 339);
		menuPane = new JPanel();
		menuPane.setBackground(new Color(135, 206, 235));
		menuPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(menuPane);
		menuPane.setLayout(null);
		
		String FirstName = "";

		Connection con = DbConnection.connect();
		// Selecting first name to add to welcome text
		try {
		PreparedStatement ps = con.prepareStatement("SELECT First_Name FROM Customers WHERE username = '"+name+"';");
		ResultSet rs = ps.executeQuery();
		rs.next();
		FirstName = rs.getString("First_Name");
		}catch(Exception Ex) {
			}finally {
				try {
					con.close();
				} catch (SQLException Ex) {
					Ex.printStackTrace();
				}
			}
		
		welcomeText = new JLabel("Welcome " + FirstName);
		welcomeText.setForeground(Color.WHITE);
		welcomeText.setHorizontalAlignment(SwingConstants.CENTER);
		welcomeText.setBounds(81, 65, 306, 36);
		welcomeText.setFont(new Font("Avenir Next", Font.BOLD, 28));
		menuPane.add(welcomeText);
		
		bookFlightButton = new JButton("Book Flight");
		bookFlightButton.setFont(new Font("Apple Symbols", Font.PLAIN, 20));
		bookFlightButton.setBounds(178, 135, 117, 35);
		menuPane.add(bookFlightButton);
		
		accountButton = new JButton("My Account");
		accountButton.setFont(new Font("Apple Symbols", Font.PLAIN, 20));
		accountButton.setBounds(178, 182, 117, 35);
		menuPane.add(accountButton);
		

		logOutButton = new JButton("Logout");
		logOutButton.setVerticalAlignment(SwingConstants.BOTTOM);
		logOutButton.setFont(new Font("Apple Symbols", Font.PLAIN, 17));
		logOutButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		logOutButton.setBounds(380, 269, 101, 32);
		menuPane.add(logOutButton);

		// Go to book flights window to add new flights
		bookFlightButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					FlightWindow frame = new FlightWindow(name);
					frame.setVisible(true);
					dispose();
				} catch (Exception Ex) {System.out.println(e);}
			}
		});
		// Go to my account window to check existing flights
		accountButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					MyAccountWindow frame = new MyAccountWindow(name);
					frame.setVisible(true);
					dispose();
				} catch (Exception Ex) {System.out.println(e);}
			}
		});
		// Log out and return to login page
		logOutButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					LoginWindow frame = new LoginWindow();
					frame.setVisible(true);
					dispose();
				} catch(Exception Ex) {
					System.out.println(e);
				}
			}
		});
	}
}