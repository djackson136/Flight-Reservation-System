package ProjectCode;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.SwingConstants;
import javax.swing.ScrollPaneConstants;
import java.awt.event.MouseEvent;

public class AdminFW extends JFrame implements ActionListener {
	private static JTable table;
	private static DefaultTableModel model;
	private JPanel contentPane;
	private JScrollPane scrollPane;
	private JTextField capacityText;
	private JTextField depCityText;
	private JTextField arrCityText;
	private JTextField dateText;
	private JTextField timeText;
	private JTextField idText;
	private JButton addButton;
	private JButton updateButton;
	private JButton deleteButton;
	private JButton logoutButton;
	private Flights flights;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminFW frame = new AdminFW();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public AdminFW() throws SQLException {
		new Flights();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1144, 603);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(135, 206, 235));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(452, 104, 646, 378);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setEnabled(false);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setToolTipText("");
		scrollPane.setViewportBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(scrollPane);

		JLabel title = new JLabel("Flight Database");
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setForeground(new Color(255, 255, 255));
		title.setFont(new Font("DIN Alternate", Font.BOLD | Font.ITALIC, 35));
		title.setBounds(480, 21, 263, 62);
		contentPane.add(title);

		addButton = new JButton("Add");
		addButton.setFont(new Font("Apple Symbols", Font.PLAIN, 20));
		addButton.setBounds(144, 431, 125, 35);
		addButton.setFont(new Font("Apple Symbols", Font.PLAIN, 20));
		contentPane.add(addButton);


		addButton.addActionListener(this);

		updateButton = new JButton("Update");
		updateButton.setFont(new Font("Apple Symbols", Font.PLAIN, 20));

		updateButton.setBounds(144, 473, 125, 35);
		updateButton.setFont(new Font("Apple Symbols", Font.PLAIN, 20));
		contentPane.add(updateButton);


		deleteButton = new JButton("Delete");
		deleteButton.setFont(new Font("Apple Symbols", Font.PLAIN, 20));

		deleteButton.setBounds(144, 515, 125, 35);
		deleteButton.setFont(new Font("Apple Symbols", Font.PLAIN, 20));
		contentPane.add(deleteButton);

		table = new JTable();
		scrollPane.setViewportView(table);

		String[] columns = { "Flight ID", "Capacity", "Departure City", "Arrival City", "Departure Date",
				"Departure Time" };
		model = new DefaultTableModel();
		model.setColumnIdentifiers(columns);

		table.setModel(model);
		table.setAutoResizeMode(0);
		table.getColumnModel().getColumn(0).setPreferredWidth(100);
		table.getColumnModel().getColumn(1).setPreferredWidth(100);
		table.getColumnModel().getColumn(2).setPreferredWidth(115);
		table.getColumnModel().getColumn(3).setPreferredWidth(115);
		table.getColumnModel().getColumn(4).setPreferredWidth(105);
		table.getColumnModel().getColumn(5).setPreferredWidth(105);

		// Displays all the flights in the database
		Connection conn = DbConnection.connect();
		try {
			String query = "SELECT * FROM Flights;";
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				model.addRow(
						new Object[] { rs.getString("Flight_ID"), rs.getString("Capacity"), rs.getString("Dep_City"),
								rs.getString("Arr_City"), rs.getString("Dep_Date"), rs.getString("Dep_Time"), });
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Could not populate flights table");
		}

		capacityText = new JTextField();
		capacityText.setBounds(233, 156, 160, 38);
		contentPane.add(capacityText);
		capacityText.setColumns(10);

		depCityText = new JTextField();
		depCityText.setBounds(233, 207, 160, 38);
		contentPane.add(depCityText);
		depCityText.setColumns(10);

		arrCityText = new JTextField();
		arrCityText.setBounds(233, 257, 160, 38);
		contentPane.add(arrCityText);
		arrCityText.setColumns(10);

		dateText = new JTextField();
		dateText.setBounds(233, 310, 160, 38);
		contentPane.add(dateText);
		dateText.setColumns(10);

		timeText = new JTextField();
		timeText.setBounds(233, 363, 160, 38);
		contentPane.add(timeText);
		timeText.setColumns(10);

		idText = new JTextField();
		idText.setBounds(233, 105, 160, 38);
		contentPane.add(idText);
		idText.setColumns(10);

		JLabel flightLabel = new JLabel("Flight ID");
		flightLabel.setBounds(52, 112, 89, 26);
		flightLabel.setFont(new Font("Avenir Next", Font.PLAIN, 19));
		contentPane.add(flightLabel);

		JLabel capacityLabel = new JLabel("Capacity");
		capacityLabel.setBounds(52, 164, 107, 26);
		capacityLabel.setFont(new Font("Avenir Next", Font.PLAIN, 19));
		contentPane.add(capacityLabel);

		JLabel depLabel = new JLabel("Departure City");
		depLabel.setBounds(52, 210, 178, 38);
		depLabel.setFont(new Font("Avenir Next", Font.PLAIN, 19));
		contentPane.add(depLabel);

		JLabel arrLabel = new JLabel("Arrival City");
		arrLabel.setBounds(52, 260, 148, 37);
		arrLabel.setFont(new Font("Avenir Next", Font.PLAIN, 19));
		contentPane.add(arrLabel);

		JLabel dateLabel = new JLabel("Departure Date");
		dateLabel.setBounds(52, 310, 169, 38);
		dateLabel.setFont(new Font("Avenir Next", Font.PLAIN, 19));
		contentPane.add(dateLabel);

		JLabel timeLabel = new JLabel("Departure Time");
		timeLabel.setBounds(52, 360, 169, 46);
		timeLabel.setFont(new Font("Avenir Next", Font.PLAIN, 19));
		contentPane.add(timeLabel);

		JButton logoutButton = new JButton("Logout");
		logoutButton.setBounds(1013, 540, 125, 35);
		contentPane.add(logoutButton);
		
		logoutButton.addActionListener(new ActionListener() {
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

	
		// click button to add flights
		addButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String error = "";
				try {
					flights.setFlightID(Integer.valueOf(idText.getText()));
					flights.setCapacity(Integer.valueOf(capacityText.getText()));
					flights.setDepCity(depCityText.getText());
					flights.setArrCity(arrCityText.getText());
					flights.setDepDate(dateText.getText());
					flights.setDepTime(timeText.getText());

					PreparedStatement ps = conn.prepareStatement("INSERT INTO Flights VALUES (?, ?, ?, ?, ?, ?);");
					
					ps.setInt(1, flights.getFlightID());
					ps.setInt(2, flights.getCapacity());
					ps.setString(3, flights.getDepCity());
					ps.setString(4, flights.getArrCity());
					ps.setString(5, flights.getDepDate());
					ps.setString(6, flights.getDepTime());

					ps.executeUpdate();
					System.out.println(ps.toString());
					model.addRow(new Object[] { flights.getFlightID(), flights.getCapacity(), flights.getDepCity(),
							flights.getArrCity(), flights.getDepDate(), flights.getDepTime(),
					});
				}

				catch (Exception ex) {
					System.out.println("Not working");
					System.out.println(error);
				}
			}

		});

		// click button to delete flights
		deleteButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// deletes selected flight from Flights database
				try {
					flights.setFlightID(Integer.valueOf(idText.getText()));
					
					DefaultTableModel model = (DefaultTableModel) table.getModel();
					int i = table.getSelectedRow();
					
					if (table.getSelectedRowCount() == 1) {
						// finds the Flight ID of selected row
						int flightid = flights.getFlightID();
						PreparedStatement ps = conn.prepareStatement("DELETE FROM Flights WHERE Flight_ID = '" + flightid + "';");
						// removes row from database and then the table
						ps.executeUpdate();
						System.out.println(ps.toString());
						model.removeRow(i);
					} else
						JOptionPane.showMessageDialog(null, "Select only 1 row");

				} catch (Exception ex) {
					ex.printStackTrace();
				}

			}

		});
		
		logoutButton = new JButton("Logout");
		logoutButton.setBounds(1013, 540, 125, 35);
		contentPane.add(logoutButton);
		
		logoutButton.addActionListener(new ActionListener() {
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
		// click on row to show in text fields
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i = table.getSelectedRow();
				DefaultTableModel model = (DefaultTableModel) table.getModel();

				// displays selected row in text fields
				idText.setText(model.getValueAt(i, 0).toString());
				capacityText.setText(model.getValueAt(i, 1).toString());
				depCityText.setText(model.getValueAt(i, 2).toString());
				arrCityText.setText(model.getValueAt(i, 3).toString());
				dateText.setText(model.getValueAt(i, 4).toString());
				timeText.setText(model.getValueAt(i, 5).toString());

			}
		});

		// click button to update flights
		updateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				flights = new Flights();

				flights.setFlightID(Integer.valueOf(idText.getText()));
				flights.setCapacity(Integer.valueOf(capacityText.getText()));
				flights.setDepCity(depCityText.getText());
				flights.setArrCity(arrCityText.getText());
				flights.setDepDate(dateText.getText());
				flights.setDepTime(timeText.getText());

				int i = table.getSelectedRow();
				if (table.getSelectedRowCount() == 1) {
					// changes the values of data in table
					model.setValueAt(flights.getFlightID(), i, 0);
					model.setValueAt(flights.getCapacity(), i, 1);
					model.setValueAt(flights.getDepCity(), i, 2);
					model.setValueAt(flights.getArrCity(), i, 3);
					model.setValueAt(flights.getDepDate(), i, 4);
					model.setValueAt(flights.getDepTime(), i, 5);
				} else
					JOptionPane.showMessageDialog(null, "Select only 1 row");

				try {
					Connection conn = DbConnection.connect();
					// updates selected flight in Flights database
					String query = "UPDATE Flights SET Capacity = ?, Dep_City = ?, Arr_City = ?, Dep_Date = ?, Dep_Time = ? WHERE Flight_ID = ?;";

					PreparedStatement ps = conn.prepareStatement(query);

					ps.setInt(1, flights.getCapacity());
					ps.setString(2, flights.getDepCity());
					ps.setString(3, flights.getArrCity());
					ps.setString(4, flights.getDepDate());
					ps.setString(5, flights.getDepTime());
					ps.setInt(6, flights.getFlightID());

					int x = ps.executeUpdate();
					if (x > 0)
						JOptionPane.showMessageDialog(null, "Flight Updated");
					else

						JOptionPane.showMessageDialog(null, "Flight Update Failed");

				} catch (Exception ex) {
					ex.printStackTrace();
				}

			}
		});

		// click button to delete flights
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// deletes selected flight from Flights database
				try {
					flights = new Flights();
					flights.setFlightID(Integer.valueOf(idText.getText()));

					DefaultTableModel model = (DefaultTableModel) table.getModel();
					int i = table.getSelectedRow();

					if (table.getSelectedRowCount() == 1) {
						// finds the Flight ID of selected row
						int flightid = flights.getFlightID();
						PreparedStatement ps = conn
								.prepareStatement("DELETE FROM Flights WHERE Flight_ID = '" + flightid + "';");
						// removes row from database and then the table
						model.removeRow(i);
						int x = ps.executeUpdate();
						
						if (x > 0)
							JOptionPane.showMessageDialog(null, "Flight Deleted");
						else
							JOptionPane.showMessageDialog(null, "Delete Flight Failed");
					} else
						JOptionPane.showMessageDialog(null, "Select only 1 row");

				} catch (Exception ex) {
					ex.printStackTrace();
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

	// Click button to add flights
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == addButton) {
			Connection conn = DbConnection.connect();
			try {
				flights.setFlightID(Integer.valueOf(idText.getText()));
				flights.setCapacity(Integer.valueOf(capacityText.getText()));
				flights.setDepCity(depCityText.getText());
				flights.setArrCity(arrCityText.getText());
				flights.setDepDate(dateText.getText());
				flights.setDepTime(timeText.getText());

				PreparedStatement ps = conn.prepareStatement("INSERT INTO Flights VALUES (?, ?, ?, ?, ?, ?);");

				ps.setInt(1, flights.getFlightID());
				ps.setInt(2, flights.getCapacity());
				ps.setString(3, flights.getDepCity());
				ps.setString(4, flights.getArrCity());
				ps.setString(5, flights.getDepDate());
				ps.setString(6, flights.getDepTime());

				int x = ps.executeUpdate();
		
				if (x > 0)
					JOptionPane.showMessageDialog(null, "Flight Added");
				else
					JOptionPane.showMessageDialog(null, "Add Flight Failed");
		
				model.addRow(new Object[] { flights.getFlightID(), flights.getCapacity(), flights.getDepCity(),
						flights.getArrCity(), flights.getDepDate(), flights.getDepTime(),
				});
				ps.close();
			}

			catch (Exception ex) {
				System.out.println("Not working");
			} finally {
				
			}
		}
	}

}
