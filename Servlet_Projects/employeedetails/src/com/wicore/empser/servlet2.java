package com.wicore.empser;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class servlet2
 */

public class servlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public servlet2() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		process(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		process(request,response);
	}

	protected void process(HttpServletRequest request, HttpServletResponse response) {
		try{
			response.setContentType("text/html"); 
			 PrintWriter out = response.getWriter();
			 response.setContentType("text/html");
				out.write("<html>");
				
				out.write("<head>");
				
				out.write("<title> Employee details</title>");
				
				out.write("<body>");
		        
				//out.write("<form name ='empdet'  action = '/employeedetails/add'>");
        
				out.write("<center>                          \r\n" + 
						"		<h1><strong>URL CON </strong></h1>\r\n" + 
						"		</center>\r\n" + 
						"		<br>");
        
     
				out.write("<p>\r\n" + 
						"			First Name\r\n" + 
						"			<input id = 'firstname' name = 'fname'  type='text'  placeholder='Enter First Name'>\r\n" + 
						"		\r\n" + 
						"		<p>\r\n" + 
						"			Designation \r\n" + 
						"			<input id = 'designation' name='designation'  type='text'  placeholder = 'Enter designation'><br>");
        
				
				out.write("</body>");
				out.write("</head>");
				out.write("</html>");
				
				String name=request.getParameter("name");      
			    String des=request.getParameter("designation");
			 out.write("\n name : " +name);
			 out.write("\n Designation :" +des);
			 
		}catch(Exception e){
			
			System.out.println(e);
			
			}    
}
}
