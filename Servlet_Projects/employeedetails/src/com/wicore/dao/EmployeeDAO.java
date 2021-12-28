package com.wicore.dao;

import java.sql.Connection;
import java.sql.SQLException;

import com.mysql.jdbc.PreparedStatement;
import com.wicore.beans.Employee;

public class EmployeeDAO {
	
	public static  void insertIntoEmployee(Employee empobj,Connection con) throws SQLException {
		
		try {
			
			PreparedStatement pst = null;
			pst = (PreparedStatement) con.prepareStatement("insert into empdetails(firstname,lastname,gender,phonenumber,email,state,Address,Employeedesignation) values(?,?,?,?,?,?,?,?)");
		 
			pst.setString(1,empobj.getFirstname());
			pst.setString(2,empobj.getLastname());
			pst.setString(3,empobj.getGender());
			pst.setString(4,empobj.getPhone_no());
			pst.setString(5,empobj.getEmail());
			pst.setString(6,empobj.getState());
			pst.setString(7,empobj.getAddress());
			pst.setString(8,empobj.getEmpdes());
			
			int i = pst.executeUpdate();
			
			if(i>0){
				
				System.out.println("added");
				
			} else{
				
				System.out.println("failed to add");
				
			}
			
		 } catch (SQLException excep) {
			 
	         excep.printStackTrace();
	         
	      } catch (Exception excep) {
	    	  
	         excep.printStackTrace();
	         
		}
		
	}
	
}
