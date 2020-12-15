package ProjectCode;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;

import java.sql.*;

@SuppressWarnings("serial")
public class FlightWindow extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField depText;
	private JTable table;
	static Connection conn = null;
	private JLabel depCityLabel;
	private JLabel arrCityLabel;
	private JTextField arrText;
	private JTextField dateText;
	private JTextField timeText;

	private JButton btnNewButton;
	private static String departure;
	private static String arrival;
	private static String date;
	private static String time;
	private JButton bookButton;
	private JButton backButton;
	private JButton searchButton;

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

	public FlightWindow() {

	}

	public FlightWindow(String name) {
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

		JLabel titleLabel = new JLabel("Find Flights");
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setForeground(new Color(255, 255, 255));
		titleLabel.setFont(new Font("DIN Alternate", Font.BOLD | Font.ITALIC, 35));
		titleLabel.setBounds(275, 17, 187, 62);
		contentPane.add(titleLabel);

		depCityLabel = new JLabel("Departure City");
		depCityLabel.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		depCityLabel.setForeground(new Color(0, 0, 0));
		depCityLabel.setBounds(42, 114, 111, 16);
		contentPane.add(depCityLabel);

		arrCityLabel = new JLabel("Arrival City");
		arrCityLabel.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		arrCityLabel.setBounds(216, 114, 86, 16);
		contentPane.add(arrCityLabel);

		depText = new JTextField();
		depText.setBounds(38, 130, 135, 38);
		contentPane.add(depText);
		depText.setColumns(10);

		arrText = new JTextField();
		arrText.setBounds(211, 130, 135, 38);
		contentPane.add(arrText);
		arrText.setColumns(10);

		JLabel dateLabel = new JLabel("Date (mm-dd-yyyy)");
		dateLabel.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		dateLabel.setBounds(392, 114, 140, 16);
		contentPane.add(dateLabel);

		JLabel timeLabel = new JLabel("Time");
		timeLabel.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		timeLabel.setBounds(564, 114, 65, 16);
		contentPane.add(timeLabel);

		dateText = new JTextField();
		dateText.setBounds(389, 130, 130, 38);
		contentPane.add(dateText);
		dateText.setColumns(10);

		timeText = new JTextField();
		timeText.setBounds(559, 130, 130, 38);
		contentPane.add(timeText);
		timeText.setColumns(10);

		searchButton = new JButton("Search");
		searchButton.setVerticalAlignment(SwingConstants.BOTTOM);
		searchButton.setHorizontalAlignment(SwingConstants.LEADING);
		searchButton.setFont(new Font("Apple Symbols", Font.PLAIN, 20));
		searchButton.setBounds(327, 183, 90, 35);
		contentPane.add(searchButton);

		searchButton.addActionListener(this);

		JButton bookButton = new JButton("Book Flight");
		bookButton.setFont(new Font("Apple Symbols", Font.PLAIN, 20));
		bookButton.setBounds(548, 505, 117, 35);
		contentPane.add(bookButton);

		JButton backButton = new JButton("Back to Main Menu");
		backButton.setVerticalAlignment(SwingConstants.BOTTOM);
		backButton.setFont(new Font("Apple Symbols", Font.PLAIN, 17));
		backButton.setBounds(6, 6, 147, 32);
		contentPane.add(backButton);

		//go back to main menu
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					MainMenu frame = new MainMenu(name);
					frame.setVisible(true);
					dispose();
				} catch (Exception Ex) {
					System.out.println(e);
				}
			}
		});
		// Select and book flight
		bookButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (table.getSelectedRowCount() == 1) {
						// Stores the values for each column of the selected flight
						String FlightID = (String) table.getValueAt(table.getSelectedRow(), 0);
						String FWDepCity = (String) table.getValueAt(table.getSelectedRow(), 1);
						String FWArrCity = (String) table.getValueAt(table.getSelectedRow(), 2);
						String FWDepDate = (String) table.getValueAt(table.getSelectedRow(), 3);
						String FWDepTime = (String) table.getValueAt(table.getSelectedRow(), 4);
						String ssn = "";
						String username = "";
						String firstName = "";
						String lastName = "";
						int count = 0;
						int capacity;
						PreparedStatement ps;

					Connection con = DbConnection.connect();
					Statement statement = con.createStatement();
		            //selecting database values using username used at login
		            ResultSet rs = statement.executeQuery("SELECT SSN,Username,First_Name,Last_Name FROM Customers WHERE Username = '"+name+"';");
		          
		            //assigning result database values to variables
		            while(rs.next()) {
		            	ssn = rs.getString("SSN");
		            	username = rs.getString("Username");
		            	firstName = rs.getString("First_Name");
		            	lastName = rs.getString("Last_Name");
		            }
		            //selecting and assigning capacity of the selected flight
		            ps = con.prepareStatement("SELECT Capacity FROM Flights WHERE Flight_ID = '"+FlightID+"';");
		            rs = ps.executeQuery();
		            rs.next();
		            capacity = rs.getInt("Capacity");
		            //selecting and assigning the number of booked passengers on selected flight
		            ps = con.prepareStatement("SELECT COUNT(Flight_ID) FROM BookedFlights WHERE Flight_ID = '"+FlightID+"';");
		            rs = ps.executeQuery();
		            rs.next();
		            count = rs.getInt("COUNT(Flight_ID)");
		            //if statement comparing bookings to capacity of the flight to prevent overbooking of a flight
		            if(count < capacity) {
		            //inserting selected flight into booked flights after being eligible
		            ps = con.prepareStatement("INSERT INTO BookedFlights(Flight_ID,SSN,First_Name,Last_Name,Username,Dep_City,Arr_City,Dep_Time,Dep_Date) VALUES(?,?,?,?,?,?,?,?,?);");
		            //setting the values that need to be inserted 
		            ps.setString(1, FlightID);
		            ps.setString(2, ssn);
		            ps.setString(3, firstName);
		            ps.setString(4, lastName);
		            ps.setString(5, username);
		            ps.setString(6, FWDepCity);
		            ps.setString(7, FWArrCity);
		            ps.setString(8, FWDepTime);
		            ps.setString(9, FWDepDate);
		            int x = ps.executeUpdate();
					if (x > 0)
						JOptionPane.showMessageDialog(null, "Flight booked!");
					else
						JOptionPane.showMessageDialog(null, "Unable to book!");
					
				}else {
					JOptionPane.showMessageDialog(null, "Flight is full!");

				}

				}else 
					JOptionPane.showMessageDialog(null, "Select only 1 row");
		        } catch (SQLException e1) {
		        	JOptionPane.showMessageDialog(null, "Flight is already booked!");
		        }
		    }
		});
		}


	// Click this button to search for flights
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == searchButton) {
			Connection conn = DbConnection.connect();
			DefaultTableModel model = new DefaultTableModel();
			model.addColumn("Flight Number");
			model.addColumn("Departure City");
			model.addColumn("Arrival City");
			model.addColumn("Departure Date");
			model.addColumn("Departure Time");
			// adds model to the table
			table.setModel(model);
			table.setAutoResizeMode(0);
			table.getColumnModel().getColumn(0).setPreferredWidth(96);
			table.getColumnModel().getColumn(1).setPreferredWidth(96);
			table.getColumnModel().getColumn(2).setPreferredWidth(96);
			table.getColumnModel().getColumn(3).setPreferredWidth(96);
			table.getColumnModel().getColumn(4).setPreferredWidth(96);
			String query = "";

			try {
				String departure = depText.getText();
				String arrival = arrText.getText();
				String date = dateText.getText();
				String time = timeText.getText();

				// Allows customer to search for flights without inputting the time
				if (time.isBlank() && !date.isBlank()) {
					query = "SELECT Flight_ID, Dep_City, Arr_City, Dep_Date, Dep_Time FROM Flights WHERE Dep_City = '"
							+ departure + "' " + "AND Arr_City = '" + arrival + "' " + "AND Dep_Date = '" + date + "' ";
					// Allows customer to search for flights without inputting the departure date
					// and time
				} else if (time.isBlank() && date.isBlank()) {
					query = "SELECT Flight_ID, Dep_City, Arr_City, Dep_Date, Dep_Time FROM Flights WHERE Dep_City = '"
							+ departure + "' " + "AND Arr_City = '" + arrival + "' ";
					// Allows customer to search for flights if all textboxes are filled
				} else {
					query = "SELECT Flight_ID, Dep_City, Arr_City, Dep_Date, Dep_Time FROM Flights WHERE Dep_City = '"
							+ departure + "' " + "AND Arr_City = '" + arrival + "' " + "AND Dep_Date = '" + date + "' "
							+ "AND Dep_Time = '" + time + "' ";
				}
				PreparedStatement pst = conn.prepareStatement(query);
				ResultSet rs = pst.executeQuery();

				while (rs.next()) {
					model.addRow(new Object[] { rs.getString("Flight_ID"), rs.getString("Dep_City"),
							rs.getString("Arr_City"), rs.getString("Dep_Date"), rs.getString("Dep_Time"), });
				}

			} catch (Exception ex) {
				System.out.println("error: " + ex);
			}
		}
	}

}
