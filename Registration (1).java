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

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Registration {

	private JFrame frame;
	private JTextField txtname;
	private JTextField txtage;
	private JTextField txtadnumber;
	private JTextField txtreport;
	private JTable table;
	private JTextField txtpid;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Registration window = new Registration();
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
	public Registration() {
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
			pst=con.prepareStatement("Select * from PATIENT_DB");
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
		frame.setBounds(100, 100, 940, 655);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("COVID TEST CARE FRAMEWORK");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel.setBounds(291, 0, 325, 58);
		frame.getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "REGISTRATION", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(83, 75, 611, 254);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("PATIENT NAME");
		lblNewLabel_2.setBounds(30, 41, 178, 21);
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 18));
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("AGE");
		lblNewLabel_3.setBounds(30, 94, 178, 21);
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.BOLD, 18));
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("AADHAR NUMBER");
		lblNewLabel_4.setBounds(30, 150, 178, 14);
		lblNewLabel_4.setFont(new Font("Times New Roman", Font.BOLD, 18));
		panel.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("COVID REPORT");
		lblNewLabel_5.setBounds(30, 198, 178, 21);
		lblNewLabel_5.setFont(new Font("Times New Roman", Font.BOLD, 18));
		panel.add(lblNewLabel_5);
		
		txtname = new JTextField();
		txtname.setBounds(299, 44, 155, 20);
		panel.add(txtname);
		txtname.setColumns(10);
		
		txtage = new JTextField();
		txtage.setBounds(299, 97, 155, 20);
		panel.add(txtage);
		txtage.setColumns(10);
		
		txtadnumber = new JTextField();
		txtadnumber.setBounds(299, 150, 155, 20);
		panel.add(txtadnumber);
		txtadnumber.setColumns(10);
		
		txtreport = new JTextField();
		txtreport.setBounds(299, 201, 155, 20);
		panel.add(txtreport);

		txtreport.setColumns(10);
		
		JButton btnNewButton = new JButton("Submit");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String pname,age,aadhar_no,covidrpt;
				
				pname=txtname.getText();
				age=txtage.getText();
				aadhar_no=txtadnumber.getText();
				covidrpt=txtreport.getText();
				try {
					pst= con.prepareStatement("INSERT INTO PATIENT_DB(pname,age,aadhar_no,covidrpt,currdate) VALUES(?,?,?,?,sysdate)");
					pst.setString(1, pname);
					pst.setString(2, age);
					pst.setString(3,aadhar_no);
					pst.setString(4, covidrpt);
					pst.executeUpdate();
					if(covidrpt.equals("POSITIVE"))
					{
						pst=con.prepareStatement("INSERT INTO POSITIVE_DB(pname,covidrpt) VALUES(?,?)");
						pst.setString(1,pname);
						pst.setString(2,covidrpt);
						pst.executeUpdate();}
					if(covidrpt.equals("NEGATIVE"))
					{
						pst=con.prepareStatement("INSERT INTO NEGATIVE_DB(pname,covidrpt) VALUES(?,?)");
						pst.setString(1,pname);
						pst.setString(2,covidrpt);
						pst.executeUpdate();
					}
					JOptionPane.showMessageDialog(null, "Recorded added successfully");
					table_load();
					txtname.setText("");
					txtage.setText("");
					txtadnumber.setText("");
					txtreport.setText("");
					txtname.requestFocus();
							}
				catch(SQLException e1)
				{
					e1.printStackTrace();
				}
			}
		});
		
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnNewButton.setBounds(778, 126, 97, 36);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Exit");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		btnNewButton_1.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnNewButton_1.setBounds(778, 189, 97, 36);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Clear");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtname.setText("");
				txtage.setText("");
				txtadnumber.setText("");
				txtreport.setText("");
				txtpid.setText("");
				
			}
		});
		btnNewButton_2.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnNewButton_2.setBounds(778, 244, 97, 36);
		frame.getContentPane().add(btnNewButton_2);
		
		JScrollPane display = new JScrollPane();
		display.setBounds(10, 399, 731, 208);
		frame.getContentPane().add(display);
		
		table = new JTable();
		display.setViewportView(table);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "SEARCH", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(128, 339, 511, 50);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		txtpid = new JTextField();

		txtpid.setBounds(202, 17, 157, 20);
		panel_1.add(txtpid);
		txtpid.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("Patient ID");
		lblNewLabel_6.setBounds(10, 23, 101, 14);
		panel_1.add(lblNewLabel_6);
		lblNewLabel_6.setFont(new Font("Times New Roman", Font.BOLD, 18));
		
		JButton btnNewButton_3 = new JButton("SEARCH");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String pid=txtpid.getText();
					pst=con.prepareStatement("Select pname,age,aadhar_no,covidrpt from PATIENT_DB WHERE pid=?");
					pst.setString(1, pid);
					ResultSet rs=pst.executeQuery();
					if(rs.next()==true)
					{
						String pname=rs.getString(1);
						String age=rs.getString(2);
						String aadhar_no=rs.getString(3);
						String covidrpt=rs.getString(4);
						
						txtname.setText(pname);
						txtage.setText(age);
						txtadnumber.setText(aadhar_no);
						txtreport.setText(covidrpt);	
					}
					
					else
					{
						JOptionPane.showMessageDialog(null, "No such ID exists");
						txtname.setText("");
						txtage.setText("");
						txtadnumber.setText("");
						txtreport.setText("");
						
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				
				
			}
		});
		btnNewButton_3.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton_3.setBounds(378, 16, 101, 21);
		panel_1.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("Modify");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String pname,age,aadhar_no,covidrpt,pid;
				
				pname=txtname.getText();
				age=txtage.getText();
				aadhar_no=txtadnumber.getText();
				covidrpt=txtreport.getText();
				pid=txtpid.getText();
				try {
					pst= con.prepareStatement("UPDATE PATIENT_DB set pname=?,age=?,aadhar_no=?,covidrpt=? WHERE pid=?");
					pst.setString(1, pname);
					pst.setString(2, age);
					pst.setString(3,aadhar_no);
					pst.setString(4, covidrpt);
					pst.setString(5, pid);
					pst.executeUpdate();
					if(covidrpt.equals("POSITIVE"))
					{
						pst=con.prepareStatement("UPDATE POSITIVE_DB set pname=?,covidrpt=?");
						pst.setString(1,pname);
						pst.setString(2,covidrpt);
						pst.executeUpdate();}
					if(covidrpt.equals("NEGATIVE"))
					{
						pst=con.prepareStatement("UPDATE NEGATIVE_DB set pname=?,covidrpt=? ");
						pst.setString(1,pname);
						pst.setString(2,covidrpt);
						pst.executeUpdate();
					}
					JOptionPane.showMessageDialog(null, "Recorded Updated");
					table_load();
					txtname.setText("");
					txtage.setText("");
					txtadnumber.setText("");
					txtreport.setText("");
					txtname.requestFocus();
							}
				catch(SQLException e2)
				{
					e2.printStackTrace();
				}
				
				
				
				
			}
		});
		btnNewButton_4.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnNewButton_4.setBounds(778, 441, 97, 36);
		frame.getContentPane().add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("Delete");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String pid;
				
			
				pid=txtpid.getText();
				try {
					pst= con.prepareStatement("DELETE FROM PATIENT_DB WHERE PID=?");
					pst.setString(1, pid);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Recorded Deleted");
					table_load();
					txtname.setText("");
					txtage.setText("");
					txtadnumber.setText("");
					txtreport.setText("");
					txtname.requestFocus();
							}
				catch(SQLException e2)
				{
					e2.printStackTrace();
				}
				
				
				
				
				
			}
		});
		btnNewButton_5.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnNewButton_5.setBounds(778, 512, 97, 36);
		frame.getContentPane().add(btnNewButton_5);
	}
}