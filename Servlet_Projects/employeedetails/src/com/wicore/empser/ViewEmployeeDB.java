package com.wicore.empser;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wicore.beans.Employee;
import com.wicore.dao.ConnectionDAO;
import com.wicore.dao.EmpDao;
import com.wicore.dao.EmployeeDAO;

/**
 * Servlet implementation class empservlet
 */

public class ViewEmployeeDB extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewEmployeeDB() {
        super();
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
		
		try {
			
				Connection con =  ConnectionDAO.getcon();
				PrintWriter out = response.getWriter(); 
			
				response.setContentType("text/html");
				out.println("<html>");
				out.println("<body>");  
				
				try { 
					
						Statement stmt = con.createStatement();  
						ResultSet rs = stmt.executeQuery("select * from empdetails");
	           
	            
						if(rs.next()) {
	            	
								rs.beforeFirst();
								out.println("<table border=1 width=10% height=10% id = emp>");
								out.println("<tr><th>emp_id</th><th>firstName</th><th>LastName</th><th>Gender</th><th>PhoneNumber</th><th>Email</th><th>State</th><th>Address</th><th>Employeedesignation</th><th>ACTION</th><tr>");  
	            	
								while (rs.next()) { 
	            	  
									int emp_id = rs.getInt("emp_id");
									String FirstName = rs.getString("firstname");
									String LastName = rs.getString("lastname");
									String Gender = rs.getString("gender"); 
									String PhoneNumber = rs.getString("phonenumber");
									String Email = rs.getString("email");
									String State = rs.getString("state");
									String Address = rs.getString("address");
									String Employeedesignation = rs.getString("employeedesignation"); 
									
									out.println("<tr><td>" + emp_id + "</td><td>" + FirstName + "</td><td>" + LastName + "</td><td>" + Gender + "</td><td>" + PhoneNumber + "</td><td>" + Email+ "</td><td>" + State + "</td><td>" + Address + "</td><td>" + Employeedesignation +"</td>"+"<td><a href = 'http://localhost:8080/employeedetails/empform?action=edit&emp_id="+emp_id+"'><span class=\"glyphicon glyphicon-pencil\">edit</span></a><br><a href = 'http://localhost:8080/employeedetails/add?action=delete&emp_id="+emp_id+"'><span class=\"glyphicon glyphicon-trash\">delete</span></a></td>"+"</tr>");
									System.out.println("viewservlet emp_id:---"+emp_id);
									
								}
								
						}
						
						else {
							
							out.println("<h2>No records are available</h2>");
						}
	          
						out.println("</table>");
						//out.println("<a href = '/employeedetails/home.html'>BACK</a>");
						out.println("</html></body>"); 
	            
	          
	            }catch (Exception e){
	            	
	            	System.out.println("error");  
	            	e.printStackTrace();
	        	   
	           	}
				
			}catch(Exception e){
				
				System.out.println("exception in employee servlet:" +e); 
				e.printStackTrace();
				
			}finally {
				
				Connection Con = (Connection) ConnectionDAO.closecon();
			}
	}

}
