package net.codeJava;
import java.awt.EventQueue;
import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

public class CovidStatus {

	private JFrame frame;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CovidStatus window = new CovidStatus();
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
	public CovidStatus() {
		initialize();
		OracleJavaTest();
	}

	Connection con;
	PreparedStatement pst;
	Statement st;
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
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 790, 622);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Covid Status", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(25, 80, 707, 469);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton btnNewButton = new JButton("POSITIVE CASES");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					pst=con.prepareStatement("Select pname,covidrpt from POSITIVE_DB");
					rs=pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
				}
				catch (SQLException e1)
				{
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnNewButton.setBounds(10, 50, 222, 62);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("NEGATIVE CASES");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					pst=con.prepareStatement("Select pname,covidrpt from NEGATIVE_DB");
					rs=pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
				}
				catch (SQLException e2)
				{
					e2.printStackTrace();
				}
			}
		});
		btnNewButton_1.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnNewButton_1.setBounds(10, 188, 222, 55);
		panel.add(btnNewButton_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(255, 28, 377, 213);
		panel.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JTextArea txtinfo = new JTextArea();
		txtinfo.setEditable(false);
		txtinfo.setBounds(266, 282, 377, 164);
		panel.add(txtinfo);
		
		
		JButton btnNewButton_2 = new JButton("COUNT OF CASES");
		btnNewButton_2.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					String p="Select count(*) from POSITIVE_DB";
					String n="Select count(*) from NEGATIVE_DB";
					pst=con.prepareStatement(p);
					rs=pst.executeQuery();
					if(rs.next())
					{
						String sum=rs.getString("count(*)");
						txtinfo.append("\n"+sum+"  -Postive Case(s)");
					}
					pst=con.prepareStatement(n);
					rs=pst.executeQuery();
					if(rs.next())
					{
						String sum=rs.getString("count(*)");
						txtinfo.append("\n"+sum+"  -Negative Case(s)");
					}
				}
				catch (SQLException e2)
				{
					e2.printStackTrace();
				}
			}
		});
		btnNewButton_2.setBounds(27, 333, 195, 55);
		panel.add(btnNewButton_2);
		
		JLabel lblNewLabel = new JLabel("COVID TEST CARE FRAMEWORK");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel.setBounds(165, 6, 411, 56);
		frame.getContentPane().add(lblNewLabel);
	}
}