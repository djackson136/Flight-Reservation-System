package ProjectCode;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.SwingConstants;

public class MainMenu extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainMenu frame = new MainMenu();
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
	public MainMenu() {
		
	}
	public MainMenu(String name) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		String FirstName = "";

		Connection con = DbConnection.connect();
		//selecting first name to add to welcome text
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
		
		JLabel welcomeText = new JLabel("Welcome " + FirstName);
		welcomeText.setHorizontalAlignment(SwingConstants.CENTER);
		welcomeText.setBounds(78, 48, 269, 25);
		welcomeText.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		contentPane.add(welcomeText);
		
		JButton bookFlightButton = new JButton("Book Flight");
		bookFlightButton.setBounds(152, 104, 117, 29);
		contentPane.add(bookFlightButton);
		
		JButton accountButton = new JButton("My Account");
		accountButton.setBounds(152, 145, 117, 29);
		contentPane.add(accountButton);
		

		JButton logOutButton = new JButton("Logout");
		logOutButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		logOutButton.setBounds(327, 243, 117, 29);
		contentPane.add(logOutButton);
		
		//go to book flights window to add new flights
		bookFlightButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					FlightWindow frame = new FlightWindow(name);
					frame.setVisible(true);
					dispose();
				} catch (Exception Ex) {System.out.println(e);}
			}
		});
		//go to my account window to check existing flights
		accountButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					MyAccountWindow frame = new MyAccountWindow(name);
					frame.setVisible(true);
					dispose();
				} catch (Exception Ex) {System.out.println(e);}
			}
		});
		//log out and return to login page
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