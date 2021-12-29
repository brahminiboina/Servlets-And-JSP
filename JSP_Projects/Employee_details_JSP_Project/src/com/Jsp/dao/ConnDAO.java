package com.Jsp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;

public class ConnDAO {
		static Connection con = null;
		
		public static Connection getcon() {
			
			try {
				
				Class.forName("com.mysql.jdbc.Driver");
				//con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/empdet", "root", "wicore123");
				con = (Connection) DriverManager.getConnection("jdbc:mysql://202.65.157.186/Project_db", "cms", "Samsung@w1c0r3");
				System.out.println("Connection is created successfully:");
				
			} catch (SQLException excep) {
				
		         excep.printStackTrace();
		         
			} catch (Exception excep) {
				
		         excep.printStackTrace();
		    } 
			
			return con;
			
		}
		
		public static Connection closecon() {
			
		         try {
		        	 
		            if (con != null)
		            	
		               con.close();
		            
		            System.out.println("Connection is closed successfully:");
		            
		         } catch (SQLException se) {
		        	 
		            se.printStackTrace();
		            
		         }
		         
				return con;  
		}

	

}
