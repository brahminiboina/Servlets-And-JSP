package com.wicore.empser;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wicore.dao.ConnectionDAO;

/**
 * Servlet implementation class EmpForm
 */

public class EditForm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditForm() {
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
		
		process(request,response);
	}
	
	protected void process(HttpServletRequest request, HttpServletResponse response)  {
		
		Connection con =  ConnectionDAO.getcon();
	
		//Add page with action tag.
		try {
			
				PrintWriter out =response.getWriter();
				
				String action = request.getParameter("action");
				System.out.println("first form action:---"+action);
				
				String emp_id =  request.getParameter("emp_id");
				System.out.println("first form emp_id:---"+emp_id);
				
				if ( action.equalsIgnoreCase("add")) {
					
						response.setContentType("text/html");
						out.write("<html>");
						
						out.write("<head>");
						
						out.write("<title> Employee details</title>");
		        
						out.write("<script type = 'text/javascript' src = 'employee.js'>");
		        
						out.write("function goback(){\r\n" + 
								"	window.history.back();\r\n" + 
								"}");
		        
						out.write("function add(){\r\n" + 
								"	\r\n" + 
								"	var firstname = document.getElementById('firstname' ).value;\r\n" + 
								"	if(firstname == ''){\r\n" + 
								"		alert('Please enter your first name');\r\n" + 
								"		return false;\r\n" + 
								"	}\r\n" + 
								"	\r\n" + 
								"	var lastname = document.getElementById('lastname').value;\r\n" + 
								"	if(lastname == ''){\r\n" + 
								"		alert('Please enter your last name');\r\n" + 
								"		return false;\r\n" + 
								"	}\r\n" + 
								"	\r\n" + 
								"	var gender =document.getElementById('gender').value;\r\n" + 
								"	if(gender == ''){\r\n" + 
								"		alert('select gender');\r\n" + 
								"		return false;\r\n" + 
								"\r\n" + 
								"    }\r\n" + 
								"	\r\n" + 
								"	var phno = document.getElementById('phno').value;\r\n" + 
								"	if(phno == ''){\r\n" + 
								"		alert('Please enter your phone number');\r\n" + 
								"		return false;\r\n" + 
								"	}\r\n" + 
								"	\r\n" + 
								"	var email = document.getElementById('email').value;\r\n" + 
								"	if(email == ''){\r\n" + 
								"		alert('Please enter your email in the format of ---@--.com');\r\n" + 
								"		return false;\r\n" + 
								"	}\r\n" + 
								"	\r\n" + 
								"	var state = document.getElementById('state').value;\r\n" + 
								"	if(state == 'select'){\r\n" + 
								"		alert('please enter your state');\r\n" + 
								"		return false;\r\n" + 
								"	}\r\n" + 
								"	\r\n" + 
								"	var Address = document.getElementById('Address').value;\r\n" + 
								"	if(Address == ''){\r\n" + 
								"		alert('Please select your address');\r\n" + 
								"		return false;\r\n" + 
								"	}\r\n" + 
								"	\r\n" + 
								"	var empdes = document.getElementById('empdes').value;\r\n" + 
								"	if(empdes == 'select'){\r\n" + 
								"		alert('please select your designation');\r\n" + 
								"		return false;\r\n" + 
								"	}\r\n" + 
								"	");
		        
						out.write("	var info = '<table cellpadding='5' cellspacing='7' border='6'><tr><th>First Name</th><th>Last Name</th><th>Gender</th><th>Mobile_no</th><th>E-mail</th><th>State</th><th>Address</th><th>Designation</th></tr><tr><td>'+firstname+'</td><td>'+lastname+'</td><td>'+gender+'</td><td>'+phno+'</td><td>'+email+'</td><td>'+state+'</td><td>'+Address+'</td><td>'+empdes+'</td></tr></table>';\r\n" + 
								"	\r\n" + 
								"");
		        
						out.write("document.getElementById('tableinfo').innerHTML=info;");
		        
						out.write("alert('Thank You!!! You're successfully registered..');}");
		       
		       
						out.write("</script>");
						out.write("<style>");
		        
						out.write("#firstname{\r\n" + 
								"					font-family: Lucida Console;  \r\n" + 
								"					color: blue;       \r\n" + 
								"			    }\r\n" + 
								"			    #lastname{   \r\n" + 
								"					font-family: Lucida Console;  \r\n" + 
								"					color: blue;       \r\n" + 
								"			    }\r\n" + 
								"			    #phno{   \r\n" + 
								"					font-family: Lucida Console;  \r\n" + 
								"					color: blue;       \r\n" + 
								"			    }\r\n" + 
								"			    #email{   \r\n" + 
								"					font-family: Lucida Console;  \r\n" + 
								"					color: blue;       \r\n" + 
								"			    }\r\n" + 
								"				\r\n" + 
								"			    .container{\r\n" + 
								"					border: 5px outset black;\r\n" + 
								"					text-align :center;\r\n" + 
								"					background-color :powderblue;\r\n" + 
								"					font-size :20px;\r\n" + 
								"					color : #330000 ;\r\n" + 
								"			    }<br>\r\n" + 
								"				");
		        
						out.write("</style></head>");
		        
						out.write("<body>");
		        
						out.write("<form name ='empdet'  action = '/employeedetails/add'>");
		        
						out.write("<center>                          \r\n" + 
								"		<h1><strong>Employee Registration </strong></h1>\r\n" + 
								"		</center>\r\n" + 
								"		<br>");
		        
						out.write("<div class='container'>");
		        
						out.write("<p>\r\n" + 
								"			First Name\r\n" + 
								"			<input id = 'firstname' name = 'fname'  type='text'  placeholder='Enter First Name'>\r\n" + 
								"		\r\n" + 
								"		<p>\r\n" + 
								"			Last Name \r\n" + 
								"			<input id = 'lastname' name='lname'  type='text'  placeholder = 'Enter Last Name'><br>");
		        
						out.write("<p>\r\n" + 
								"			<label name='gender' ><b>Gender:  </b></label>\r\n" + 
								"			<input  id = 'gender' name='gender' type = 'radio' value = 'male'>\r\n" + 
								"			 male\r\n" + 
								"			<input id = 'gender' name='gender' type = 'radio' value = 'female'>\r\n" + 
								"			 female\r\n" + 
								"			<input id = 'gender' name='gender' type = 'radio' value = 'other'>\r\n" + 
								"			 other");
		        
						out.write("<p>\r\n" + 
								"			Phone Number \r\n" + 
								"			<input id = 'phno' name='phoneno'  type='text'  placeholder = 'Enter ph.no'><br>");
		        
						out.write("<p>\r\n" + 
								"			<label><b>Email </b></label>\r\n" + 
								"			<input id = 'email' name='email' type='text'  placeholder = 'Enter email'><br>");
		        
						out.write("<p>\r\n" + 
								"			<label><b>State</b></label>\r\n" + 
								"			<select id = 'state'  name = 'State'  >\r\n" + 
								"			<option>select</option>\r\n" + 
								"			<option>AndhraPradesh</option>\r\n" + 
								"			<option>Telangana</option>\r\n" + 
								"			</select>");
		        
						out.write("<p>\r\n" + 
								"			<label for = 'text'><b>Employee Address</b></label>\r\n" + 
								"			<textarea id = 'Address'  name='Address'  rows = '3' colomns= '2'>\r\n" + 
								"			</textarea>");
		        
						out.write("<P>\r\n" + 
								"			<label ><b>Employee Designation:</b></label>\r\n" + 
								"			<select id = 'empdes' name = 'Employee Designation'>\r\n" + 
								"			<option>select</option>\r\n" + 
								"			<option>Java Developer</option>   \r\n" + 
								"			<option>Web Designer</option>\r\n" + 
								"			<option>Software Tester</option>\r\n" + 
								"			</select><br>");
		        
						out.write("<p>\r\n" + 
								"			<input type = 'submit' onclick = 'return add()' value = 'ADD'>\r\n" + 
								"			<input type = 'submit' onclick = 'return goback()' value = 'BACK'>\r\n" + 
								"		</div>");
		                out.write("<input type='hidden' name='action' value='"+action+"'>");
		        
		                out.write("<div id = 'tableinfo' name='tableinfo' value = 'tableinfo'></div>");
		                
		                out.write("/form");
		        
		                out.write("</body></html>");
		
					}
		
		}catch(Exception e1) {
			
			e1.printStackTrace();
		}
		
		//Edit page with action tag and emp_id.
        try {
        	
        	PrintWriter out =response.getWriter();
        	
        	String action = request.getParameter("action");
       		String emp_id =  request.getParameter("emp_id");
    	     
       		if (action.equalsIgnoreCase("edit")) {
             
       			// String query = "select name,email from emp where Emp_id=emp_id";
       			String query ="select emp_id, firstname, lastname, gender, phonenumber, email, state, Address, employeedesignation from empdetails where emp_id='"+emp_id+"'";
                System.out.println("Edit Query : "+query);
                
                PreparedStatement pst = con.prepareStatement(query);
                ResultSet rs = pst.executeQuery();
                      
                 String firstname = "firstname";
      	         String lastname = "lastname";
      	         String gender = "gender";
      	         String phonenumber = "phonenumber";
      	         String email = "email";
      	         String state = "state";
      	         String Address = "Address";
      	         String Employeedesignation = "Employeedesignation";
      	         
      	         
                 if(rs.next()) {
                	   
	            	   firstname = rs.getString("firstname");
	            	   lastname = rs.getString("lastname");
	            	   gender = rs.getString("gender");
	            	   phonenumber = rs.getString("phonenumber");
	            	   email = rs.getString("email");
	            	   state = rs.getString("state");
	                   Address = rs.getString("Address");
	                   Employeedesignation = rs.getString("Employeedesignation");

	                   response.setContentType("text/html");
	                   
	                   out.write("<html>");
	                   
	                   out.write("<head>");
	                   
	                   out.write("<title> Employee details</title>");
		        
	                   out.write("<script type = 'text/javascript' src = 'employee.js'>");
		        
	                   out.write("function goback(){\r\n" + 
	                		   "	window.history.back();\r\n" + 
	                		   "}");
		        
	                   out.write("function add(){\r\n" + 
	                		   "	\r\n" + 
	                		   "	var firstname = document.getElementById('firstname' ).value;\r\n" + 
	                		   "	if(firstname == ''){\r\n" + 
	                		   "		alert('Please enter your first name');\r\n" + 
	                		   "		return false;\r\n" + 
	                		   "	}\r\n" + 
	                		   "	\r\n" + 
	                		   "	var lastname = document.getElementById('lastname').value;\r\n" + 
	                		   "	if(lastname == ''){\r\n" + 
	                		   "		alert('Please enter your last name');\r\n" + 
	                		   "		return false;\r\n" + 
	                		   "	}\r\n" + 
	                		   "	\r\n" + 
	                		   "	var gender =document.getElementById('gender').value;\r\n" + 
	                		   "	if(gender == ''){\r\n" + 
	                		   "		alert('select gender');\r\n" + 
	                		   "		return false;\r\n" + 
	                		   "\r\n" + 
	                		   "    }\r\n" + 
	                		   "	\r\n" + 
	                		   "	var phno = document.getElementById('phno').value;\r\n" + 
	                		   "	if(phno == ''){\r\n" + 
	                		   "		alert('Please enter your phone number');\r\n" + 
	                		   "		return false;\r\n" + 
	                		   "	}\r\n" + 
	                		   "	\r\n" + 
	                		   "	var email = document.getElementById('email').value;\r\n" + 
	                		   "	if(email == ''){\r\n" + 
	                		   "		alert('Please enter your email in the format of ---@--.com');\r\n" + 
	                		   "		return false;\r\n" + 
	                		   "	}\r\n" + 
	                		   "	\r\n" + 
	                		   "	var state = document.getElementById('state').value;\r\n" + 
	                		   "	if(state == 'select'){\r\n" + 
	                		   "		alert('please enter your state');\r\n" + 
	                		   "		return false;\r\n" + 
	                		   "	}\r\n" + 
	                		   "	\r\n" + 
	                		   "	var Address = document.getElementById('Address').value;\r\n" + 
	                		   "	if(Address == ''){\r\n" + 
	                		   "		alert('Please select your address');\r\n" + 
	                		   "		return false;\r\n" + 
	                		   "	}\r\n" + 
	                		   "	\r\n" + 
	                		   "	var empdes = document.getElementById('empdes').value;\r\n" + 
	                		   "	if(empdes == 'select'){\r\n" + 
	                		   "		alert('please select your designation');\r\n" + 
	                		   "		return false;\r\n" + 
	                		   "	}\r\n" + 
	                		   "	");
		        
	                   	out.write("	var info = '<table cellpadding='5' cellspacing='7' border='6'><tr><th>First Name</th><th>Last Name</th><th>Gender</th><th>Mobile_no</th><th>E-mail</th><th>State</th><th>Address</th><th>Designation</th></tr><tr><td>'+firstname+'</td><td>'+lastname+'</td><td>'+gender+'</td><td>'+phno+'</td><td>'+email+'</td><td>'+state+'</td><td>'+Address+'</td><td>'+empdes+'</td></tr></table>';\r\n" + 
	                   			"	\r\n" + 
	                   			"");
		        
	                   	out.write("document.getElementById('tableinfo').innerHTML=info;");
		        
	                   	out.write("alert('Thank You!!! You're successfully registered..');}");
		       
	                   	out.write("</script>");
	                   	
	                   	out.write("<style>");
		        
	                   	out.write("#firstname{\r\n" + 
	                   			"					font-family: Lucida Console;  \r\n" + 
	                   			"					color: blue;       \r\n" + 
	                   			"			    }\r\n" + 
	                   			"			    #lastname{   \r\n" + 
	                   			"					font-family: Lucida Console;  \r\n" + 
	                   			"					color: blue;       \r\n" + 
	                   			"			    }\r\n" + 
	                   			"			    #phno{   \r\n" + 
	                   			"					font-family: Lucida Console;  \r\n" + 
	                   			"					color: blue;       \r\n" + 
	                   			"			    }\r\n" + 
	                   			"			    #email{   \r\n" + 
	                   			"					font-family: Lucida Console;  \r\n" + 
	                   			"					color: blue;       \r\n" + 
	                   			"			    }\r\n" + 
	                   			"				\r\n" + 
	                   			"			    .container{\r\n" + 
	                   			"					border: 5px outset black;\r\n" + 
	                   			"					text-align :center;\r\n" + 
	                   			"					background-color :powderblue;\r\n" + 
	                   			"					font-size :20px;\r\n" + 
	                   			"					color : #330000 ;\r\n" + 
	                   			"			    }<br>\r\n" + 
	                   			"				");
		        
	                   	out.write("</style></head>");
		        
	                   	out.write("<body>");
		        
	                   	out.write("<form name ='empdet'  action = '/employeedetails/add'>");
		        
	                   	out.write("<center>                          \r\n" + 
	                   			"		<h1><strong>Employee Registration </strong></h1>\r\n" + 
	                   			"		</center>\r\n" + 
	                   			"		<br>");
		        
	                   	out.write("<div class='container'>");
		        
	                   	out.write("<p>\r\n" + 
	                   			"			First Name\r\n" + 
	                   			"			<input id = 'firstname' name = 'fname' value = '"+firstname+"' type='text'  placeholder='Enter First Name'>\r\n" + 
	                   			"		\r\n" + 
	                   			"		<p>\r\n" + 
	                   			"			Last Name \r\n" + 
	                   			"			<input id = 'lastname' name='lname' value = '"+lastname+"' type='text'  placeholder = 'Enter Last Name'><br>");
		        
	                   	out.write("<p>\r\n" + 
	                   			"			<label name='gender' value = '"+gender+"'><b>Gender:  </b></label>\r\n" + 
	                   			"			<input  id = 'gender' name='gender' type = 'radio' value = 'male'>\r\n" + 
	                   			"			 male\r\n" + 
	                   			"			<input id = 'gender' name='gender' type = 'radio' value = 'female'>\r\n" + 
	                   			"			 female\r\n" + 
	                   			"			<input id = 'gender' name='gender' type = 'radio' value = 'other'>\r\n" + 
	                   			"			 other");
		        
	                   	out.write("<p>\r\n" + 
	                   			"			Phone Number \r\n" + 
	                   			"			<input id = 'phno' name='phoneno' value = '"+phonenumber+"' type='text'  placeholder = 'Enter ph.no'><br>");
		        
	                   	out.write("<p>\r\n" + 
	                   			"			<label><b>Email </b></label>\r\n" + 
	                   			"			<input id = 'email' name='email' value = '"+email+"' type='text'  placeholder = 'Enter email'><br>");
		        
	                   	out.write("<p>\r\n" + 
	                   			"			<label><b>State</b></label>\r\n" + 
	                   			"			<select id = 'state'  name = 'State'  >\r\n" + 
	                   			"			<option>select</option>\r\n" + 
	                   			"			<option>AndhraPradesh</option>\r\n" + 
	                   			"			<option>Telangana</option>\r\n" + 
	                   			"			</select>");
		        
	                   	out.write("<p>\r\n" + 
	                   			"			<label for = 'text'><b>Employee Address</b></label>\r\n" + 
	                   			"			<textarea id = 'Address'  name='Address' value = '"+Address+"' rows = '3' colomns= '2'>\r\n" + 
	                   			"			</textarea>");
		        
	                   	out.write("<P>\r\n" + 
	                   			"			<label ><b>Employee Designation:</b></label>\r\n" + 
	                   			"			<select id = 'empdes' name = 'Employee Designation'>\r\n" + 
	                   			"			<option>select</option>\r\n" + 
	                   			"			<option>Java Developer</option>   \r\n" + 
	                   			"			<option>Web Designer</option>\r\n" + 
	                   			"			<option>Software Tester</option>\r\n" + 
	                   			"			</select><br>");
		        
	                   	out.write("<p>\r\n" + 
	                   			"			<input type = 'submit' onclick = 'return add()' value = 'ADD'>\r\n" + 
	                   			"			<input type = 'submit' onclick = 'return goback()' value = 'BACK'>\r\n" + 
	                   			"		</div>");
	                   	
	                   	out.write("<input type='hidden' name='action' value='"+action+"'>");
	                   	
	                   	out.write("<input type='hidden' name='emp_id' value='"+emp_id+"'>");
		        
	                   	out.write("<div id = 'tableinfo' name='tableinfo' value = 'tableinfo'></div>");
		        
	                   	out.write("</form>");
	                   	
	                   	out.write("</body>");
	                   	
	                   	out.write("</html>");
	                   	
                   }
       		}
       		
            System.out.println("second form edited action:---"+action);
            System.out.println("second form edited emp_id:---"+emp_id);
			
		}catch(Exception e) {
			
			//out.println("please handle the error");
			e.printStackTrace();
			
		}
        
	}	

}
