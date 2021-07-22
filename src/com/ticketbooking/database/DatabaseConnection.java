/**
 * 
 */
package com.ticketbooking.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;




/**
 * @author Dhanapal
 *
 */
public class DatabaseConnection {
	private static Connection con = null;
	private static String mysqlURL = "jdbc:mysql://localhost:3306/tickets_booking";
	private static String USERNAME = "root";
	private static String PASSWORD = "root";
	
	static{
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(mysqlURL, USERNAME, PASSWORD);
		} catch (ClassNotFoundException e) {
			// TODO: handle exception
			System.out.println("Error While load driver class for create connection :: " + e.getMessage());
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error While SQL connection :: " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	public  static Connection getConnection(){
		return con;
	}
	
	

}
