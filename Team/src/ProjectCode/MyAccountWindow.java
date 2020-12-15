package ProjectCode;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 400);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(135, 206, 235));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(28, 79, 494, 223);
		scrollPane.setToolTipText("");
		scrollPane.setViewportBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

		showButton = new JButton("Show Flights");
		showButton.setVerticalAlignment(SwingConstants.BOTTOM);
		showButton.setFont(new Font("Apple Symbols", Font.PLAIN, 17));
		showButton.setBounds(386, 314, 136, 32);
		contentPane.add(showButton);

		JLabel Header = new JLabel("Booked Flights");
		Header.setForeground(Color.WHITE);
		Header.setHorizontalAlignment(SwingConstants.CENTER);
		Header.setFont(new Font("DIN Alternate", Font.BOLD, 26));
		Header.setBounds(168, 22, 221, 40);
		contentPane.add(Header);
		
		JButton deleteButton = new JButton("Delete Flight");
		deleteButton.setVerticalAlignment(SwingConstants.BOTTOM);
		deleteButton.setFont(new Font("Apple Symbols", Font.PLAIN, 17));
		deleteButton.setBounds(38, 313, 117, 32);
		contentPane.add(deleteButton);
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
		scrollPane.setBounds(28, 30, 494, 223);
		scrollPane.setToolTipText("");
		scrollPane.setViewportBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

		showButton = new JButton("Show Flights");
		showButton.setBounds(386, 265, 136, 29);
		contentPane.add(showButton);

		JButton backButton = new JButton("Back to Main Menu");
		backButton.setBounds(6, 6, 147, 29);
		contentPane.add(backButton);

		JButton deleteButton = new JButton("Delete Flight");
		deleteButton.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		deleteButton.setBounds(38, 313, 117, 29);
		contentPane.add(deleteButton);

		// go back to main menu
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

		// shows all flights booked by the logged in customer
		showButton.addActionListener(new ActionListener() {
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

				try {
					// selecting the details of the flights booked by logged in customer from
					// BookedFlights database
					String query = "SELECT Dep_City, Arr_City, Dep_Date, Dep_Time FROM BookedFlights WHERE Username = '"
							+ name + "';";
					PreparedStatement pst = conn.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					// adding selected values to the model to show the user
					while (rs.next()) {
						model.addRow(new Object[] { rs.getString("Dep_City"), rs.getString("Arr_City"),
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
							
							DefaultTableModel model = (DefaultTableModel) table.getModel();
							int i = table.getSelectedRow();
							
							if (table.getSelectedRowCount() == 1) {
								// finds the Flight ID of selected row
								PreparedStatement ps = conn.prepareStatement("DELETE FROM BookedFlights WHERE username = '" + name + "';");
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
