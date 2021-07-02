package net.codeJava;

import java.awt.EventQueue;
import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class bed_info {

	private JFrame frame;
	private JTable table;
	private JTextField txtbid;
	private JTextField txtid;
	private JTextField txtrno;
	private JTextField txtbedid;
	private JTextField txttype;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					bed_info window = new bed_info();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public bed_info() {
		initialize();
		OracleJavaTest();
		table_load();
	}
	
	
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	
	private void OracleJavaTest() {
		String dburl ="jdbc:oracle:thin:@218.248.07:1521:rdbms";
		String us = "it19737004";
		String pas ="vasavi";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection(dburl,us,pas);
			System.out.println("Connected");
		}
		catch (SQLException connectException) {
			System.out.println(connectException.getMessage());
			System.out.println(connectException.getSQLState());
			System.out.println(connectException.getErrorCode());
			System.exit(1);
			}
			catch (Exception e)
			{
			System.err.println("Unable to find and load driver");
			System.exit(1);
			}
		
	}
	private void displaySQLErrors(SQLException e) 
	{
		JOptionPane.showMessageDialog(null, "Invalid Patient ID");
	}

	
	public void table_load()
	{
		try
		{
			pst=con.prepareStatement("Select * from BED_DB");
			rs=pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 956, 464);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("COVID TEST CARE FRAMEWORK");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel.setBounds(318, 0, 303, 54);
		frame.getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "BED DATA", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 52, 394, 215);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Bed id");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel_1.setBounds(25, 31, 89, 34);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Bed Type");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel_2.setBounds(25, 90, 89, 21);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Patient id");
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel_3.setBounds(25, 186, 89, 14);
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Room no");
		lblNewLabel_4.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel_4.setBounds(25, 142, 79, 14);
		panel.add(lblNewLabel_4);
		
		txtbid = new JTextField();
		txtbid.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtbid.setBounds(160, 40, 172, 20);
		panel.add(txtbid);
		txtbid.setColumns(10);
		
		txtrno = new JTextField();
		txtrno.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtrno.setBounds(160, 140, 172, 20);
		panel.add(txtrno);
		txtrno.setColumns(10);
		
		txttype = new JTextField();
		txttype.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txttype.setBounds(160, 93, 172, 19);
		panel.add(txttype);
		txttype.setColumns(10);
		
		txtid = new JTextField();
		txtid.setBounds(160, 183, 172, 20);
		panel.add(txtid);
		txtid.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtid.setColumns(10);
		
		JButton btnNewButton = new JButton("SUBMIT");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String bid,bed_type,roomno,id;
				
				bid=txtbid.getText();
				bed_type=txttype.getText();
				roomno=txtrno.getText();
				id=txtid.getText();
				try {
					pst= con.prepareStatement("INSERT INTO BED_DB(bid,bed_type,roomno,pid) VALUES(?,?,?,?)");
					pst.setString(1, bid);
					pst.setString(2, bed_type);
					pst.setString(3,roomno);
					pst.setString(4, id);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Recorded added successfully");
					table_load();
					txtbid.setText("");
					txttype.setText("");
					txtrno.setText("");
					txtid.setText("");
					txtbid.requestFocus();
							}
				catch (SQLException insertException) 
				{
				displaySQLErrors(insertException);
				}
			}
		});
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnNewButton.setBounds(28, 290, 108, 35);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("EXIT");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnNewButton_1.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnNewButton_1.setBounds(165, 289, 89, 37);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("CLEAR");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtbid.setText("");
				txttype.setText("");
				txtrno.setText("");
				txtid.setText("");
				txtbedid.setText("");
			}
		});
		btnNewButton_2.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnNewButton_2.setBounds(283, 289, 103, 37);
		frame.getContentPane().add(btnNewButton_2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(440, 53, 480, 214);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnNewButton_4 = new JButton("MODIFY");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String bid,bed_type,roomno,pid,id;
				
				bid=txtbid.getText();
				bed_type=txttype.getText();
				roomno=txtrno.getText();
				pid=txtid.getText();
				id=txtbedid.getText();
				
				try {
					pst= con.prepareStatement("UPDATE BED_DB set bid=?,bed_type=?,roomno=?,pid=? WHERE bid=?");
					pst.setString(1, bid);
					pst.setString(2, bed_type);
					pst.setString(3,roomno);
					pst.setString(4, pid);
					pst.setString(5, id);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Recorded Updated successfully");
					table_load();
					txtbid.setText("");
					txttype.setText("");
					txtrno.setText("");
					txtid.setText("");
					txtbedid.setText("");
					txtbid.requestFocus();
							}
				catch(SQLException e2)
				{
					e2.printStackTrace();
				}
				
				
			}
		});
		btnNewButton_4.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnNewButton_4.setBounds(568, 290, 126, 35);
		frame.getContentPane().add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("DELETE");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id;
				
				id=txtbedid.getText();
				try {
					pst= con.prepareStatement("DELETE FROM BED_DB WHERE bid=?");
					pst.setString(1, id);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Record deleted");
					table_load();
					txtbid.setText("");
					txttype.setText("");
					txtrno.setText("");
					txtid.setText("");
					txtbedid.setText("");
					txtbid.requestFocus();
							}
				catch(SQLException e2)
				{
					e2.printStackTrace();
				}
			}
		});
		btnNewButton_5.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnNewButton_5.setBounds(748, 289, 125, 35);
		frame.getContentPane().add(btnNewButton_5);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "SEARCH", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 345, 403, 82);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_5 = new JLabel("Bed ID");
		lblNewLabel_5.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel_5.setBounds(30, 29, 111, 32);
		panel_1.add(lblNewLabel_5);
		
		txtbedid = new JTextField();
		txtbedid.setFont(new Font("Times New Roman", Font.BOLD, 18));
		txtbedid.setBounds(118, 30, 121, 32);
		panel_1.add(txtbedid);
		txtbedid.setColumns(10);
		
		JButton btnNewButton_3 = new JButton("SEARCH");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String id=txtbedid.getText();
					pst=con.prepareStatement("Select bid,bed_type,roomno,pid from BED_DB WHERE bid=?");
					pst.setString(1, id);
					ResultSet rs=pst.executeQuery();
					if(rs.next()==true)
					{
						String bid=rs.getString(1);
						String bed_type=rs.getString(2);
						String roomno=rs.getString(3);
						String id1=rs.getString(4);
						
						txtbid.setText(bid);
						txttype.setText(bed_type);
						txtrno.setText(roomno);
						txtid.setText(id1);	
					}
					
					else
					{
						JOptionPane.showMessageDialog(null, "No such ID exists");
						txtbid.setText("");
						txttype.setText("");
						txtrno.setText("");
						txtid.setText("");
						txtbedid.setText("");
						
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnNewButton_3.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnNewButton_3.setBounds(276, 28, 111, 35);
		panel_1.add(btnNewButton_3);
	}
}
