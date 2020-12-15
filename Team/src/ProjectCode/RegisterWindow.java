package ProjectCode;

import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class RegisterWindow extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField firstNameText;
	private JTextField lastNameText;
	private JTextField streetText;
	private JTextField cityText;
	private JTextField zipText;
	private JTextField userText;
	private JTextField passText;
	private JTextField conPassText;
	private JTextField emailText;
	private JTextField ssnText;
	private JTextField secQuestionText;
	private JTextField secAnswerText;
	private JComboBox<String> stateBox;
	private JButton regButton;

	
	// Launch the application
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

	
	// Creates the frame
	public RegisterWindow() {
		setTitle("Registration");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 410, 455);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel firstNameLabel = new JLabel("First Name: ");
		firstNameLabel.setFont(new Font("Avenir Next", Font.PLAIN, 14));
		firstNameLabel.setBounds(90, 25, 88, 16);
		contentPane.add(firstNameLabel);

		firstNameText = new JTextField();
		firstNameText.setBounds(180, 20, 130, 26);
		contentPane.add(firstNameText);
		firstNameText.setColumns(10);

		JLabel lastNameLabel = new JLabel("Last Name:");
		lastNameLabel.setFont(new Font("Avenir Next", Font.PLAIN, 14));
		lastNameLabel.setBounds(92, 56, 76, 16);
		contentPane.add(lastNameLabel);

		lastNameText = new JTextField();
		lastNameText.setBounds(180, 51, 130, 26);
		contentPane.add(lastNameText);
		lastNameText.setColumns(10);

		JLabel streetLabel = new JLabel("Street Address:");
		streetLabel.setFont(new Font("Avenir Next", Font.PLAIN, 14));
		streetLabel.setBounds(14, 84, 104, 16);
		contentPane.add(streetLabel);

		streetText = new JTextField();
		streetText.setBounds(122, 79, 240, 26);
		contentPane.add(streetText);
		streetText.setColumns(10);

		JLabel cityLabel = new JLabel("City: ");
		cityLabel.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		cityLabel.setBounds(9, 112, 33, 16);
		contentPane.add(cityLabel);

		cityText = new JTextField();
		cityText.setBounds(40, 107, 100, 26);
		contentPane.add(cityText);
		cityText.setColumns(10);

		JLabel stateLabel = new JLabel("State:");
		stateLabel.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		stateLabel.setBounds(145, 112, 35, 16);
		contentPane.add(stateLabel);

		stateBox = new JComboBox<String>();
		stateBox.setBounds(180, 108, 80, 27);
		stateBox.setModel(new DefaultComboBoxModel(new String[] { "AK", "AL", "AR", "AS", "AZ", "CA", "CO", "CT", "DC",
				"DE", "FL", "GA", "GU", "HI", "IA", "ID", "IL", "IN", "KS", "KY", "LA", "MA", "MD", "ME", "MI", "MN",
				"MO", "MP", "MS", "MT", "NC", "ND", "NE", "NH", "NJ", "NM", "NV", "NY", "OH", "OK", "OR", "PA", "PR",
				"RI", "SC", "SD", "TN", "TX", "UM", "UT", "VA", "VI", "VT", "WA", "WI", "WV", "WY" }));
		stateBox.setMaximumRowCount(50);
		contentPane.add(stateBox);

		JLabel zipLabel = new JLabel("Zipcode:");
		zipLabel.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		zipLabel.setBounds(260, 112, 60, 16);
		contentPane.add(zipLabel);

		zipText = new JTextField();
		zipText.setBounds(318, 107, 76, 26);
		contentPane.add(zipText);
		zipText.setColumns(10);

		JLabel userLabel = new JLabel("Username:");
		userLabel.setFont(new Font("Avenir Next", Font.PLAIN, 14));
		userLabel.setBounds(92, 145, 76, 16);
		contentPane.add(userLabel);

		userText = new JTextField();
		userText.setBounds(180, 140, 130, 26);
		contentPane.add(userText);
		userText.setColumns(10);

		JLabel passLabel = new JLabel("Password:");
		passLabel.setFont(new Font("Avenir Next", Font.PLAIN, 14));
		passLabel.setBounds(92, 173, 76, 16);
		contentPane.add(passLabel);

		passText = new JPasswordField();
		passText.setBounds(180, 168, 130, 26);
		contentPane.add(passText);
		passText.setColumns(10);

		JLabel conPassLabel = new JLabel("Confirm Password:");
		conPassLabel.setFont(new Font("Avenir Next", Font.PLAIN, 14));
		conPassLabel.setBounds(40, 201, 128, 16);
		contentPane.add(conPassLabel);

		conPassText = new JPasswordField();
		conPassText.setBounds(180, 196, 130, 26);
		contentPane.add(conPassText);
		conPassText.setColumns(10);

		JLabel emailLabel = new JLabel("Email:");
		emailLabel.setFont(new Font("Avenir Next", Font.PLAIN, 14));
		emailLabel.setBounds(44, 229, 46, 16);
		contentPane.add(emailLabel);

		emailText = new JTextField();
		emailText.setBounds(102, 224, 260, 26);
		contentPane.add(emailText);
		emailText.setColumns(10);


		JLabel ssnLabel = new JLabel("SSN (no dashes):");
		ssnLabel.setFont(new Font("Avenir Next", Font.PLAIN, 14));
		ssnLabel.setBounds(40, 262, 115, 16);
		contentPane.add(ssnLabel);

		ssnText = new JPasswordField();
		ssnText.setBounds(167, 257, 114, 26);
		contentPane.add(ssnText);

		JLabel secQuestionLabel = new JLabel("Security Question:");
		secQuestionLabel.setFont(new Font("Avenir Next", Font.PLAIN, 14));
		secQuestionLabel.setBounds(6, 291, 121, 16);
		contentPane.add(secQuestionLabel);

		secQuestionText = new JTextField();
		secQuestionText.setBounds(130, 286, 264, 26);
		contentPane.add(secQuestionText);
		secQuestionText.setColumns(10);

		JLabel secAnswerLabel = new JLabel("Security Answer:");
		secAnswerLabel.setFont(new Font("Avenir Next", Font.PLAIN, 14));
		secAnswerLabel.setBounds(16, 319, 112, 16);
		contentPane.add(secAnswerLabel);

		secAnswerText = new JTextField();
		secAnswerText.setBounds(130, 314, 150, 26);
		contentPane.add(secAnswerText);


		regButton = new JButton("Register");
		regButton.setVerticalAlignment(SwingConstants.BOTTOM);
		regButton.setFont(new Font("Apple Symbols", Font.PLAIN, 16));
		regButton.setBounds(156, 366, 104, 32);
		contentPane.add(regButton);
		
		// Adds ActionListener to register button
		regButton.addActionListener(this);

	}
	
	// Registers a customer into the Customers SQL database
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == regButton) {
			Connection conn = DbConnection.connect();
			try {
				Customer customer = new Customer();
				// Sets user inputs into data fields (from Customer class)
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
				// Finds the values for each question mark in the PreparedStatement
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
				
				// Inserts user inputs into the database
				ps.executeUpdate();
			} catch (Exception ex) {
				System.out.println(ex);
			}
			contentPane.setVisible(false);
			dispose();
			LoginWindow frame = new LoginWindow();
			frame.setVisible(true);
		}
	}

}
