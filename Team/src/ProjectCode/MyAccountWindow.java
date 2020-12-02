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
import javax.swing.JButton;

public class MyAccountWindow extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JButton showButton;

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
		scrollPane.setBounds(28, 30, 494, 223);
		scrollPane.setToolTipText("");
		scrollPane.setViewportBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);
		
		showButton = new JButton("Show Flights");
		showButton.setBounds(386, 265, 136, 29);
		contentPane.add(showButton);
	}
public MyAccountWindow(String name) {
		
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
				String query = "";
				try {
					query = "SELECT Dep_City, Arr_City, Dep_Date, Dep_Time FROM Accounts WHERE Username = '"
								+ name + "' ";
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
