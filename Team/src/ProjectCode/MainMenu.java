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
import java.sql.ResultSet;
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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel welcomeText = new JLabel("Welcome");
		welcomeText.setHorizontalAlignment(SwingConstants.CENTER);
		welcomeText.setBounds(78, 48, 269, 25);
		welcomeText.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		contentPane.add(welcomeText);
		
		JButton bookFlightButton = new JButton("Book a Flight");
		bookFlightButton.setBounds(152, 104, 117, 29);
		contentPane.add(bookFlightButton);
		
		JButton accountButton = new JButton("My Account");
		accountButton.setBounds(152, 145, 117, 29);
		contentPane.add(accountButton);
		
		bookFlightButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection conn = DbConnection.connect();
				try {
					FlightWindow frame = new FlightWindow();
					frame.setVisible(true);
				} catch (Exception Ex) {System.out.println(e);}
			}
		});
		accountButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					MyAccountWindow frame = new MyAccountWindow();
					frame.setVisible(true);
				} catch(Exception Ex) {
					System.out.println(e);
				}
			}
		});
	}
	public MainMenu(String name) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel welcomeText = new JLabel("Welcome " + name);
		welcomeText.setHorizontalAlignment(SwingConstants.CENTER);
		welcomeText.setBounds(78, 48, 269, 25);
		welcomeText.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		contentPane.add(welcomeText);
		
		JButton bookFlightButton = new JButton("Book a Flight");
		bookFlightButton.setBounds(152, 104, 117, 29);
		contentPane.add(bookFlightButton);
		
		JButton accountButton = new JButton("My Account");
		accountButton.setBounds(152, 145, 117, 29);
		contentPane.add(accountButton);
		
		bookFlightButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection conn = DbConnection.connect();
				try {
					FlightWindow frame = new FlightWindow();
					frame.setVisible(true);
				} catch (Exception Ex) {System.out.println(e);}
			}
		});
		accountButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					MyAccountWindow frame = new MyAccountWindow();
					frame.setVisible(true);
				} catch (Exception Ex) {System.out.println(e);}
			}
		});
	}
}