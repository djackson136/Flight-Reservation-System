package ProjectCode;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.SwingConstants;

public class MyAccountWindow extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JButton showButton;
	private JButton backButton;
	private JLabel title;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MyAccountWindow frame = new MyAccountWindow();
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
	
	public MyAccountWindow() {	
	}
	
	public MyAccountWindow(String name) {
		
		Connection conn = DbConnection.connect();
		Flights flight = new Flights();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 400);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(135, 206, 235));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(36, 91, 482, 220);
		scrollPane.setToolTipText("");
		scrollPane.setViewportBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);
		
		showButton = new JButton("Show Flights");
		showButton.setFont(new Font("Apple Symbols", Font.PLAIN, 18));
		showButton.setBounds(422, 321, 108, 34);
		contentPane.add(showButton);
		
		JButton backButton = new JButton("Back to Main Menu");
		backButton.setVerticalAlignment(SwingConstants.BOTTOM);
		backButton.setFont(new Font("Apple Symbols", Font.PLAIN, 17));
		backButton.setBounds(6, 6, 147, 32);
		contentPane.add(backButton);
		
		JButton deleteButton = new JButton("Delete Flight");
		deleteButton.setFont(new Font("Apple Symbols", Font.PLAIN, 18));
		deleteButton.setBounds(36, 323, 108, 34);
		contentPane.add(deleteButton);

		title = new JLabel("Flight Reservations");
		title.setForeground(Color.WHITE);
		title.setFont(new Font("DIN Alternate", Font.BOLD, 26));
		title.setBounds(177, 29, 236, 61);
		contentPane.add(title);
		
		// go back to main menu
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					MainMenu frame = new MainMenu(name);
					frame.setVisible(true);
					dispose();
				} catch (Exception Ex) {
					System.out.println(e);
				}finally {
					try {
						conn.close();
					} catch (SQLException Ex) {
						Ex.printStackTrace();
					}
				}
			}
		});
		
		// shows all flights booked by the logged in customer
		showButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
				
				try {
					//selecting the details of the flights booked by logged in customer from BookedFlights database
					String query = "SELECT Flight_ID, Dep_City, Arr_City, Dep_Date, Dep_Time FROM BookedFlights WHERE Username = '"+name+"';";
					PreparedStatement pst = conn.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					
					// adding selected values to the model to show the user
					while (rs.next()) {
						model.addRow(new Object[] { rs.getString("Flight_ID"), rs.getString("Dep_City"), rs.getString("Arr_City"),
								rs.getString("Dep_Date"), rs.getString("Dep_Time"), });
					}

					rs.close();
					pst.close();

				} catch (Exception ex) {
					System.out.println("error: " + ex);
				}
			}
		});
		
		// click button to delete flights
				deleteButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {

						// deletes selected flight from Flights database
						try {
							String FlightID = (String) table.getValueAt(table.getSelectedRow(), 0);
							
							DefaultTableModel model = (DefaultTableModel) table.getModel();
							int i = table.getSelectedRow();
							
							if (table.getSelectedRowCount() == 1) {
								// finds the Flight ID of selected row
								PreparedStatement ps = conn.prepareStatement("DELETE FROM BookedFlights WHERE Flight_ID = '"+FlightID+"';");
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
	}
}
