package net.codeJava;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class login {

	private JFrame loginframe;
	private JTextField txtuser;
	private JPasswordField txtpwd;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					login window = new login();
					window.loginframe.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		loginframe = new JFrame();
		loginframe.setBounds(100, 100, 549, 409);
		loginframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		loginframe.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("COVID TESTCARE FRAMEWORK");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 22));
		lblNewLabel.setBounds(98, 10, 376, 33);
		loginframe.getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "USERLOGIN", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLUE));
		panel.setBounds(43, 79, 455, 195);
		loginframe.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("LOGIN");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel_1.setBounds(185, 10, 73, 32);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("USERNAME");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblNewLabel_2.setBounds(52, 92, 101, 23);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("PASSWORD");
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblNewLabel_3.setBounds(52, 150, 101, 23);
		panel.add(lblNewLabel_3);
		
		txtuser = new JTextField();
		txtuser.setBounds(206, 92, 170, 23);
		panel.add(txtuser);
		txtuser.setColumns(10);
		
		txtpwd = new JPasswordField();
		txtpwd.setBounds(206, 149, 170, 23);
		panel.add(txtpwd);
		
		JButton btnNewButton = new JButton("LOGIN");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username=txtuser.getText();
				@SuppressWarnings("deprecation")
				String pwd=txtpwd.getText();
				
				if(pwd.contains("123456")&& username.contains("admin"))
				{
					txtuser.setText(null);
					txtpwd.setText(null);
					loginframe.dispose();
					Users.main(null);
					
				}
				else
				{
					JOptionPane.showMessageDialog(null,"INVALID LOGIN","ERROR",JOptionPane.ERROR_MESSAGE);
					txtuser.setText(null);
					txtpwd.setText(null);
				}
					
				
			}
		});
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnNewButton.setBounds(114, 308, 123, 38);
		loginframe.getContentPane().add(btnNewButton);
		
		JButton btnReset = new JButton("RESET");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtuser.setText(null);
				txtpwd.setText(null);
			}
		});
		btnReset.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnReset.setBounds(314, 308, 123, 38);
		loginframe.getContentPane().add(btnReset);
	}
}
