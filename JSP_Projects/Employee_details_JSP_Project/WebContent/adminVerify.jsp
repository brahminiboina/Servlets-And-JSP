<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.text.*,java.util.*,java.sql.Driver, java.sql.DriverManager,java.sql.*,javax.sql.*,javax.naming.*,java.io.*,java.net.URLEncoder,com.Jsp.bean.Emp,com.Jsp.dao.ConnDAO,com.Jsp.dao.EmpDAO" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<style>
	body {
			background-image: url(https://www.ndami.club/images/signup-bg.jpg);
			font: 20px/20px sans-serif ;
			
			
		}
</style>
</head>
<body>
<% 	try{
	String username = request.getParameter("UserName");
	String password = request.getParameter("Password");
		boolean isValidate = false;
		Connection con =  ConnDAO.getcon();
		PreparedStatement ps = null;
 		ResultSet rs = null;
 	
		//String querry = "select * from credentials where c_user_name = '"+username+"' and c_password = '"+password+"'"; 
		String query =" select c_id from credentials where  c_user_name = '"+username+"' and c_password = '"+password+"' and c_role_type ='admin'";	
		System.out.println("Edit Query : "+query);
		//System.out.println("Edit c_id : "+c_id);
		
  		ps = con.prepareStatement(query);
 	 	rs = ps.executeQuery();
 	 	//isValidate=rs.next();
  		if(rs.next()){
  			 //cookies creation
  			System.out.println("username:-------"+username);
  	 		Cookie ck=new Cookie("username",username);  		 
  			ck.setMaxAge(10*60);
  	  		response.addCookie(ck);
  	  		
  	 		//Valid credential
  			System.out.println("entered into if");
  			rs.beforeFirst();
  		 	isValidate = true;
  			System.out.println("isvalid : "+isValidate);
  			response.sendRedirect("admin.jsp");
  			
  		}else{%>
  			
  			<center >
  				<h1>INVALID CREDENTIALS</h1>
  				<%out.println("Username or password is incorrect");%>
  				<p>
				To Re-login
				<a href= "login.jsp"> click here</a>
  			</center>
  		<% }
	}catch(Exception e){
  			e.printStackTrace();
  		}%>
  		
</body>
</html>