package ProjectCode;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

public class RegisterWindow extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JLabel lblNewLabel_4;

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
		setBounds(100, 100, 450, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("First Name: ");
		lblNewLabel.setBounds(55, 61, 76, 16);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(143, 56, 130, 26);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Last Name:");
		lblNewLabel_1.setBounds(55, 89, 76, 16);
		contentPane.add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		textField_1.setBounds(143, 84, 130, 26);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Street Address:");
		lblNewLabel_2.setBounds(27, 117, 104, 16);
		contentPane.add(lblNewLabel_2);
		
		textField_2 = new JTextField();
		textField_2.setBounds(143, 112, 209, 26);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("City: ");
		lblNewLabel_3.setBounds(27, 145, 33, 16);
		contentPane.add(lblNewLabel_3);
		
		textField_3 = new JTextField();
		textField_3.setBounds(72, 140, 90, 26);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		lblNewLabel_4 = new JLabel("State:");
		lblNewLabel_4.setBounds(174, 145, 35, 16);
		contentPane.add(lblNewLabel_4);
		
		JList list = new JList();
		list.setBounds(220, 159, 53, -8);
		contentPane.add(list);
		
		JList stateText = new JList();
		stateText.setVisibleRowCount(50);
		stateText.setBounds(221, 145, 33, 16);
		contentPane.add(stateText);
	}
}
