<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page  import="java.text.*,java.util.*,java.sql.Driver, java.sql.DriverManager,java.sql.*,javax.sql.*,javax.naming.*,java.io.*,java.net.URLEncoder,com.Jsp.bean.Emp,com.Jsp.dao.ConnDAO,com.Jsp.dao.EmpDAO" %>
				
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%
		Connection con = ConnDAO.getcon();
		System.out.println("Entered into AddNEW");
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

		try {
	
			if (action.equalsIgnoreCase("ADD")) {
				
				Emp e = new Emp();
				e.setFirstname(firstname);
				e.setLastname(lastname);
				e.setGender(gender);
				e.setPhone_no(phoneno);
				e.setEmail(email);
				e.setState(state);
				e.setAddress(address);
				e.setEmpdes(employeedesignation);
	 
				//Connection con =  ConnectionDAO.getcon();
				EmpDAO.insertIntoEmployee(e, con);
		
			 }

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
	
			response.sendRedirect("/empb/View.jsp");
			
			}catch(Exception e){
	
				System.out.println("exception in employee servlet:" +e); 
				e.printStackTrace();
		
			}finally {
	
					Connection Con = (Connection) ConnDAO.closecon();
			}

%>


</body>
</html>