package com.wicore.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.wicore.beans.Employee;


public class EmpDao {
	
	static String query = "select  firstname,lastname,gender,phonenumber,email,state,Address,Employeedesignation " + " FROM empdetails";

	public static  void ViewTable(Employee empobj,Connection con) throws SQLException {
		
        System.out.println("<html><body>"); 
        
		try {
			
			Statement stmt = con.createStatement();
			
			ResultSet rs = stmt.executeQuery("select * from empdetails");
			
			System.out.println("<table border=1 width=50% height=50%>");
			
			System.out.println("<tr><th>firstname</th><th>lastName</th><th>gender</th><th>phonenumber</th><th>email</th><th>state</th><th>Address</th><th>Employeedesignation</th><tr>");
		    
			while (rs.next()) {
				
		        String firstname = rs.getString("firstname");
		        String lastname = rs.getString("lastname");
		        String gender = rs.getString("gender");
		        String phonenumber = rs.getString("phonenumber");
		        String email = rs.getString("email");
		        String state = rs.getString("state");
		        String Address = rs.getString("Address");
		        String Employeedesignation = rs.getString("Employeedesignation");
		        
		        /*System.out.println(firstname + ", " + lastname + ", " + gender + ", " + phonenumber + 
		        		", " + email + ", " + state + ", " + Address + ", " + Employeedesignation);*/
		        System.out.println("<tr><td>" + firstname + "</td><td>" + lastname + "</td><td>" + gender + "</td><td>" + phonenumber + "</td><td>" + email + "</td><td>" + state + "</td><td>" + Address + "</td><td>" + Employeedesignation +"</td></tr>");   
		        
		        System.out.println("</table>");  
	            
		        System.out.println("</html></body>");
		        
		     }
			
		} catch (SQLException e) {
			
		    System.out.println("please handle the exception");
		    e.printStackTrace();
		    
		}
	}
}


