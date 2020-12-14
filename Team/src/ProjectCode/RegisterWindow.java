package ProjectCode;

import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class RegisterWindow extends JFrame  {

	private JPanel contentPane;
	private JTextField firstNameText;
	private JTextField lastNameText;
	private JTextField streetText;
	private JTextField cityText;
	private JLabel stateLabel;
	private JTextField zipText;
	private JLabel userLabel;
	private JTextField userText;
	private JLabel passLabel;
	private JTextField passText;
	private JLabel conPassLabel;
	private JTextField conPassText;
	private JLabel emailLabel;
	private JTextField emailText;
	private JTextField ssnText;
	private JLabel secQuestionLabel;
	private JTextField secQuestionText;
	private JLabel lblNewLabel;
	private JTextField secAnswerText;
	private JComboBox<String> stateBox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterWindow frame = new RegisterWindow();
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
	
	public RegisterWindow() {
		setTitle("Registration");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 420, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel firstNameLabel = new JLabel("First Name: ");
		firstNameLabel.setBounds(92, 25, 76, 16);
		contentPane.add(firstNameLabel);
		
		firstNameText = new JTextField();
		firstNameText.setBounds(180, 20, 130, 26);
		contentPane.add(firstNameText);
		firstNameText.setColumns(10);
		
		JLabel lastNameLabel = new JLabel("Last Name:");
		lastNameLabel.setBounds(92, 56, 76, 16);
		contentPane.add(lastNameLabel);
		
		lastNameText = new JTextField();
		lastNameText.setBounds(180, 51, 130, 26);
		contentPane.add(lastNameText);
		lastNameText.setColumns(10);
		
		JLabel streetLabel = new JLabel("Street Address:");
		streetLabel.setBounds(6, 84, 104, 16);
		contentPane.add(streetLabel);
		
		streetText = new JTextField();
		streetText.setBounds(122, 79, 240, 26);
		contentPane.add(streetText);
		streetText.setColumns(10);
		
		JLabel cityLabel = new JLabel("City: ");
		cityLabel.setBounds(6, 112, 33, 16);
		contentPane.add(cityLabel);
		
		cityText = new JTextField();
		cityText.setBounds(40, 107, 100, 26);
		contentPane.add(cityText);
		cityText.setColumns(10);
		
		stateLabel = new JLabel("State:");
		stateLabel.setBounds(145, 112, 35, 16);
		contentPane.add(stateLabel);
		
		stateBox = new JComboBox<String>();
		stateBox.setBounds(180, 108, 80, 27);
		stateBox.setModel(new DefaultComboBoxModel(new String[] {"AK", "AL", "AR", "AS", "AZ", "CA", "CO", "CT", "DC", "DE",
				"FL", "GA", "GU", "HI", "IA", "ID", "IL", "IN", "KS", "KY", "LA", "MA", "MD", "ME", "MI", "MN", "MO", "MP",
				"MS", "MT", "NC", "ND", "NE", "NH", "NJ", "NM", "NV", "NY", "OH", "OK", "OR", "PA", "PR", "RI", "SC", "SD",
				"TN", "TX", "UM", "UT", "VA", "VI", "VT", "WA", "WI", "WV", "WY"}));
		stateBox.setMaximumRowCount(50);
		contentPane.add(stateBox);

		JLabel zipLabel = new JLabel("Zipcode:");
		zipLabel.setBounds(260, 112, 60, 16);
		contentPane.add(zipLabel);
		
		zipText = new JTextField();
		zipText.setBounds(318, 107, 76, 26);
		contentPane.add(zipText);
		zipText.setColumns(10);
		
		userLabel = new JLabel("Username:");
		userLabel.setBounds(92, 145, 76, 16);
		contentPane.add(userLabel);
		
		userText = new JTextField();
		userText.setBounds(180, 140, 130, 26);
		contentPane.add(userText);
		userText.setColumns(10);
		
		passLabel = new JLabel("Password:");
		passLabel.setBounds(92, 173, 76, 16);
		contentPane.add(passLabel);
		
		passText = new JPasswordField();
		passText.setBounds(180, 168, 130, 26);
		contentPane.add(passText);
		passText.setColumns(10);
		
		conPassLabel = new JLabel("Confirm Password:");
		conPassLabel.setBounds(40, 201, 128, 16);
		contentPane.add(conPassLabel);
		
		conPassText = new JPasswordField();
		conPassText.setBounds(180, 196, 130, 26);
		contentPane.add(conPassText);
		conPassText.setColumns(10);
		
		emailLabel = new JLabel("Email:");
		emailLabel.setBounds(44, 229, 46, 16);
		contentPane.add(emailLabel);
		
		emailText = new JTextField();
		emailText.setBounds(102, 224, 260, 26);
		contentPane.add(emailText);
		emailText.setColumns(10);
		
		JLabel ssnLabel = new JLabel("SSN:");
		ssnLabel.setBounds(122, 262, 33, 16);
		contentPane.add(ssnLabel);
		
		ssnText = new JPasswordField();
		ssnText.setBounds(180, 257, 114, 26);
		contentPane.add(ssnText);
		
		secQuestionLabel = new JLabel("Security Question:");
		secQuestionLabel.setBounds(6, 291, 121, 16);
		contentPane.add(secQuestionLabel);
		
		secQuestionText = new JTextField();
		secQuestionText.setBounds(130, 286, 264, 26);
		contentPane.add(secQuestionText);
		secQuestionText.setColumns(10);
		
		lblNewLabel = new JLabel("Security Answer:");
		lblNewLabel.setBounds(16, 319, 112, 16);
		contentPane.add(lblNewLabel);
		
		secAnswerText = new JTextField();
		secAnswerText.setBounds(130, 314, 150, 26);
		contentPane.add(secAnswerText);
		
		JButton regButton = new JButton("Register");
		regButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection conn = DbConnection.connect();
				try {
					Customer customer = new Customer();
					
					customer.setSsn(Integer.valueOf(ssnText.getText()));
					customer.setUsername(userText.getText());
					customer.setPassword(passText.getText());
					customer.setFirstName(firstNameText.getText());
					customer.setLastName(lastNameText.getText());
					customer.setEmail(emailText.getText());
					customer.setStreetAddress(streetText.getText());
					customer.setCity(cityText.getText());
					customer.setState(String.valueOf(stateBox.getSelectedItem()));
					customer.setZipcode(Integer.valueOf(zipText.getText()));
					customer.setSecQuestion(secQuestionText.getText());
					customer.setSecAnswer(secAnswerText.getText());
					
					
					PreparedStatement ps = conn.prepareStatement("INSERT INTO Customers VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
					ps.setInt(1, customer.getSsn());
					ps.setString(2, customer.getUsername());
					ps.setString(3, customer.getPassword());
					ps.setString(4, customer.getFirstName());
					ps.setString(5, customer.getLastName());
					ps.setString(6, customer.getEmail());
					ps.setString(7, customer.getStreetAddress());
					ps.setString(8, customer.getCity());
					ps.setString(9, customer.getState());
					ps.setInt(10, customer.getZipcode());
					ps.setString(11, customer.getSecQuestion());
					ps.setString(12, customer.getSecAnswer());
					ps.executeUpdate();
					
				} catch (Exception ex) {
					System.out.println(ex);
				}
				contentPane.setVisible(false);
				dispose();
				
			}
		});
		regButton.setBounds(145, 398, 117, 29);
		contentPane.add(regButton);
		System.out.println();
		}

}

