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
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;

public class employee_Reg {

	private JFrame frame;
	private JTable table;
	private JTextField txtid;
	private JTextField txtname;
	private JTextField txtsalary;
	private JTextField txtnumber;
	private JTextField txteid;
	private JTextField txtrole;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					employee_Reg window = new employee_Reg();
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
	public employee_Reg() {
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
	
	
	public void table_load()
	{
		try
		{
			pst=con.prepareStatement("Select * from EMPLOYEE_DB");
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
		frame.setBounds(100, 100, 988, 668);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("COVID TEST CARE FRAMEWORK");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel.setBounds(330, 10, 316, 44);
		frame.getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Employee_Data", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(31, 56, 673, 230);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Employee id");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel_1.setBounds(57, 33, 117, 21);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Employee name");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel_2.setBounds(58, 71, 140, 21);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Employee role");
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel_3.setBounds(57, 108, 130, 21);
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Employee Salary");
		lblNewLabel_4.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel_4.setBounds(57, 147, 170, 21);
		panel.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Contact number");
		lblNewLabel_5.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel_5.setBounds(58, 185, 140, 14);
		panel.add(lblNewLabel_5);
		
		txtid = new JTextField();
		txtid.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtid.setBounds(343, 33, 170, 20);
		panel.add(txtid);
		txtid.setColumns(10);
		
		txtname = new JTextField();
		txtname.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtname.setBounds(343, 71, 170, 20);
		panel.add(txtname);
		txtname.setColumns(10);
		
		txtsalary = new JTextField();
		txtsalary.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtsalary.setBounds(343, 147, 170, 20);
		panel.add(txtsalary);
		txtsalary.setColumns(10);
		
		txtnumber = new JTextField();
		txtnumber.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtnumber.setBounds(343, 182, 170, 20);
		panel.add(txtnumber);
		txtnumber.setColumns(10);
		
		txtrole = new JTextField();
		txtrole.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtrole.setBounds(343, 109, 170, 18);
		panel.add(txtrole);
		txtrole.setColumns(10);
		
		
		JButton btnNewButton = new JButton("SAVE");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String eid,ename,erole,esalary,phno;
				
				eid=txtid.getText();
				ename=txtname.getText();
				erole=txtrole.getText();
				esalary=txtsalary.getText();
				phno=txtnumber.getText();
				try {
					pst= con.prepareStatement("INSERT INTO EMPLOYEE_DB(eid,ename,erole,esalary,phno) VALUES(?,?,?,?,?)");
					pst.setString(1, eid);
					pst.setString(2, ename);
					pst.setString(3,erole);
					pst.setString(4, esalary);
					pst.setString(5,phno);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Recorded added successfully");
					table_load();
					txtid.setText("");
					txtname.setText("");
					txtrole.setText("");
					txtsalary.setText("");
					txtnumber.setText("");
					txtid.requestFocus();
							}
				catch(SQLException e1)
				{
					e1.printStackTrace();
				}
				
			}
		});
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnNewButton.setBounds(757, 102, 99, 37);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("EXIT");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnNewButton_1.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnNewButton_1.setBounds(757, 166, 99, 37);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("CLEAR");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtid.setText("");
				txtname.setText("");
				txtrole.setText("");
				txtsalary.setText("");
				txtnumber.setText("");
				txteid.setText("");
				
			}
		});
		btnNewButton_2.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnNewButton_2.setBounds(757, 230, 99, 37);
		frame.getContentPane().add(btnNewButton_2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(31, 379, 673, 230);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Search", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(31, 309, 673, 56);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_6 = new JLabel("Employee id");
		lblNewLabel_6.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel_6.setBounds(61, 24, 133, 21);
		panel_1.add(lblNewLabel_6);
		
		txteid = new JTextField();
		txteid.addKeyListener(new KeyAdapter() {
		});
		txteid.setFont(new Font("Times New Roman", Font.BOLD, 18));
		txteid.setBounds(222, 24, 171, 20);
		panel_1.add(txteid);
		txteid.setColumns(10);
		
		JButton btnNewButton_3 = new JButton("Search");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String eid=txteid.getText();
					pst=con.prepareStatement("Select eid,ename,erole,esalary,phno from EMPLOYEE_DB WHERE eid=?");
					pst.setString(1, eid);
					ResultSet rs=pst.executeQuery();
					if(rs.next()==true)
					{
						String id=rs.getString(1);
						String ename=rs.getString(2);
						String erole=rs.getString(3);
						String esalary=rs.getString(4);
						String phno=rs.getString(5);
						
						txtid.setText(id);
						txtname.setText(ename);
						txtrole.setText(erole);
						txtsalary.setText(esalary);
						txtnumber.setText(phno);	
					}
					
					else
					{
						JOptionPane.showMessageDialog(null, "No such ID exists");
						txtid.setText("");
						txtname.setText("");
						txtrole.setText("");
						txtsalary.setText("");
						txtnumber.setText("");	
						
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnNewButton_3.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnNewButton_3.setBounds(464, 10, 133, 39);
		panel_1.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("MODIFY");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id,ename,erole,esalary,phno,eid;
				id =txtid.getText();
				ename=txtname.getText();
				erole=txtrole.getText();
				esalary=txtsalary.getText();
				phno=txtnumber.getText();
				eid=txteid.getText();
				try {
					pst= con.prepareStatement("UPDATE EMPLOYEE_DB set eid=?,ename=?,erole=?,esalary=?,phno=? WHERE eid=?");
					pst.setString(1, id);
					pst.setString(2, ename);
					pst.setString(3,erole);
					pst.setString(4, esalary);
					pst.setString(5,phno);
					pst.setString(6,eid);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Recorded Updated successfully");
					table_load();
					txtid.setText("");
					txtname.setText("");
					txtrole.setText("");
					txtsalary.setText("");
					txtnumber.setText("");
					txtid.requestFocus();
							}
				catch(SQLException e1)
				{
					e1.printStackTrace();
				}
				
			}
		});
		btnNewButton_4.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnNewButton_4.setBounds(757, 395, 118, 49);
		frame.getContentPane().add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("DELETE");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String eid;
				
				
				eid=txteid.getText();
				try {
					pst= con.prepareStatement("DELETE FROM EMPLOYEE_DB WHERE EID=?");
					pst.setString(1, eid);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Recorded Deleted");
					table_load();
					
					txtname.requestFocus();
							}
				catch(SQLException e2)
				{
					e2.printStackTrace();
				}
				txtid.setText("");
				txtname.setText("");
				txtrole.setText("");
				txtsalary.setText("");
				txtnumber.setText("");
				txteid.setText("");
			}
		});
		btnNewButton_5.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnNewButton_5.setBounds(757, 484, 118, 44);
		frame.getContentPane().add(btnNewButton_5);
	}
}
