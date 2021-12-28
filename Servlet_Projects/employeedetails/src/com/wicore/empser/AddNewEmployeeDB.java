package com.wicore.empser;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.wicore.beans.Employee;
import com.wicore.dao.ConnectionDAO;
import com.wicore.dao.EmpDao;
import com.wicore.dao.EmployeeDAO;

/**
 * Servlet implementation class employeeservlet
 */
public class AddNewEmployeeDB extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ServletResponse response;

    /**
     * Default constructor. 
     */
    public AddNewEmployeeDB() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		process(request,response);
		
	
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		process(request,response);
		
	}
	
	protected void process(HttpServletRequest request, HttpServletResponse response) {
		
		String firstname = request.getParameter("fname");
		String lastname = request.getParameter("lname");
		String gender = request.getParameter("gender");
		String phoneno = request.getParameter("phoneno");
		String email = request.getParameter("email");
		String state = request.getParameter("State");
		String address = request.getParameter("Address");
		String employeedesignation = request.getParameter("Employee Designation");
		String action = request.getParameter("action");
		String emp_id = request.getParameter("emp_id");
		
		Connection con =  ConnectionDAO.getcon();
		
		try {
			
			if (action.equalsIgnoreCase("add")) {
				
				Employee empobj = new Employee();
				empobj.setFirstname(firstname);
				empobj.setLastname(lastname);
				empobj.setGender(gender);
				empobj.setPhone_no(phoneno);
				empobj.setEmail(email);
				empobj.setState(state);
				empobj.setAddress(address);
				empobj.setEmpdes(employeedesignation);
			 
				//Connection con =  ConnectionDAO.getcon();
				EmployeeDAO.insertIntoEmployee(empobj, con);
				
			 }
			
			// response.sendRedirect("/employeedetails/home.html");
				   
			else if (action.equalsIgnoreCase("edit")) {
				
				System.out.println("update Edit :-----"+ emp_id);        
			     // String query = "select name,email from emp where Emp_id=emp_id";
				 String updQry = "update empdetails set firstname='"+firstname+"', lastname = '"+lastname+"', gender = '"+gender+"', phonenumber = '"+phoneno+"', email = '"+email+"', state = '"+state+"' , Address = '"+address+"', Employeedesignation = '"+employeedesignation+"'  where emp_id='"+emp_id+"'";
				 PreparedStatement ps = con.prepareStatement(updQry);
				 int count = ps.executeUpdate();
			     //System.out.println("Edit Query : "+query);

			}                               
			
			else if (action.equalsIgnoreCase("delete")) {
				
				System.out.println("update delete :-----"+ emp_id); 
				String delQry = "delete from empdetails where emp_id = '"+emp_id+"'";     
				 PreparedStatement ps = con.prepareStatement(delQry);
				 int count = ps.executeUpdate();
			     //System.out.println("Edit Query : "+query);

			}
			
			response.sendRedirect("/employeedetails/home.html");
			 
		}catch(Exception e){
			
				System.out.println("exception in employee servlet:" +e); 
				e.printStackTrace();
				
		}finally {
			
				Connection Con = (Connection) ConnectionDAO.closecon();
		}
	}

}
