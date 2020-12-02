package ProjectCode;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;

import java.sql.*;

@SuppressWarnings("serial")
public class FlightWindow extends JFrame {

	private JPanel contentPane;
	private JTextField depText;
	private JTable table;
	static Connection conn = null;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JTextField arrText;
	private JTextField dateText;
	private JTextField timeText;
	private JButton bookButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FlightWindow frame = new FlightWindow();
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
	public FlightWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 718, 588);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(135, 206, 235));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setToolTipText("");
		scrollPane.setViewportBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		scrollPane.setBounds(118, 248, 494, 223);
		contentPane.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

		JLabel lblNewLabel = new JLabel("Find Flights");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("DIN Alternate", Font.BOLD | Font.ITALIC, 35));
		lblNewLabel.setBounds(275, 17, 187, 62);
		contentPane.add(lblNewLabel);

		lblNewLabel_1 = new JLabel("Departure City");
		lblNewLabel_1.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		lblNewLabel_1.setForeground(new Color(0, 0, 0));
		lblNewLabel_1.setBounds(42, 114, 111, 16);
		contentPane.add(lblNewLabel_1);

		lblNewLabel_2 = new JLabel("Arrival City");
		lblNewLabel_2.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(216, 114, 86, 16);
		contentPane.add(lblNewLabel_2);

		depText = new JTextField();
		depText.setBounds(38, 130, 135, 38);
		contentPane.add(depText);
		depText.setColumns(10);

		arrText = new JTextField();
		arrText.setBounds(211, 130, 135, 38);
		contentPane.add(arrText);
		arrText.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("Date");
		lblNewLabel_3.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		lblNewLabel_3.setBounds(392, 114, 40, 16);
		contentPane.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("Time");
		lblNewLabel_4.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		lblNewLabel_4.setBounds(564, 114, 61, 16);
		contentPane.add(lblNewLabel_4);

		dateText = new JTextField();
		dateText.setBounds(389, 130, 130, 38);
		contentPane.add(dateText);
		dateText.setColumns(10);

		timeText = new JTextField();
		timeText.setBounds(559, 130, 130, 38);
		contentPane.add(timeText);
		timeText.setColumns(10);

		JButton searchButton = new JButton("Search");
		searchButton.setVerticalAlignment(SwingConstants.BOTTOM);
		searchButton.setHorizontalAlignment(SwingConstants.LEADING);
		searchButton.setFont(new Font("Apple Symbols", Font.PLAIN, 20));
		searchButton.setBounds(327, 183, 90, 35);
		contentPane.add(searchButton);

		bookButton = new JButton("Book Flight");
		bookButton.setFont(new Font("Apple Symbols", Font.PLAIN, 20));
		bookButton.setBounds(548, 505, 117, 35);
		contentPane.add(bookButton);

		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection conn = DbConnection.connect();
				DefaultTableModel model = new DefaultTableModel();
				model.addColumn("Departure City");
				model.addColumn("Arrival City");
				model.addColumn("Departure Date");
				model.addColumn("Departure Time");
				table.setModel(model);
				table.setAutoResizeMode(0);
				table.getColumnModel().getColumn(0).setPreferredWidth(120);
				table.getColumnModel().getColumn(1).setPreferredWidth(120);
				table.getColumnModel().getColumn(2).setPreferredWidth(120);
				table.getColumnModel().getColumn(3).setPreferredWidth(120);
				String query = "";

				try {
					String departure = depText.getText();
					String arrival = arrText.getText();
					String date = dateText.getText();
					String time = timeText.getText();

					if (time.isBlank() && !date.isBlank()) {
						query = "SELECT Dep_City, Arr_City, Dep_Date, Dep_Time FROM Flights WHERE Dep_City = '"
								+ departure + "' " + "AND Arr_City = '" + arrival + "' " + "AND Dep_Date = '" + date
								+ "' ";
					} else if (time.isBlank() && date.isBlank()) {
						query = "SELECT Dep_City, Arr_City, Dep_Date, Dep_Time FROM Flights WHERE Dep_City = '"
								+ departure + "' " + "AND Arr_City = '" + arrival + "' ";
					} else {
						query = "SELECT Dep_City, Arr_City, Dep_Date, Dep_Time FROM Flights WHERE Dep_City = '"
								+ departure + "' " + "AND Arr_City = '" + arrival + "' " + "AND Dep_Date = '" + date
								+ "' " + "AND Dep_Time = '" + time + "' ";
					}
					PreparedStatement pst = conn.prepareStatement(query);
					ResultSet rs = pst.executeQuery();

					while (rs.next()) {
						model.addRow(new Object[] { rs.getString("Dep_City"), rs.getString("Arr_City"),
								rs.getString("Dep_Date"), rs.getString("Dep_Time"), });
					}

					rs.close();
					pst.close();
					conn.close();

				} catch (Exception ex) {
					System.out.println("error: " + ex);
				}
			}
		});
	}
}
