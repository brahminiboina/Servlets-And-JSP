package com.Jsp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.Jsp.bean.Emp;




public class EmpDAO {
public static  void insertIntoEmployee(Emp e,Connection con) throws SQLException {
		
		try {
			
			PreparedStatement pst = null;
			pst = (PreparedStatement) con.prepareStatement("insert into empdetails(firstname,lastname,gender,phonenumber,email,state,Address,Employeedesignation) values(?,?,?,?,?,?,?,?)");
		 
			pst.setString(1,e.getFirstname());
			System.out.println("first name:"+e.getFirstname());
			pst.setString(2,e.getLastname());
			pst.setString(3,e.getGender());
			pst.setString(4,e.getPhone_no());
			pst.setString(5,e.getEmail());
			pst.setString(6,e.getState());
			pst.setString(7,e.getAddress());
			pst.setString(8,e.getEmpdes());
			
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