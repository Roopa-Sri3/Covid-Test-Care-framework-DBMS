package net.codeJava;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class OracleJavaTest {
	
	public static void main(String[] args) {	
	String dbURL = "jdbc:oracle:thin:@218.248.07:1521:rdbms";
	String username = "it19737004";
	String password = "vasavi";
	
	try {
		Connection connection = DriverManager.getConnection(dbURL,username,password);
		System.out.println("Connected");
		connection.close();
		
	} catch (SQLException e) {
		System.out.println("Oops,error");
		e.printStackTrace();
	}
	
}
}