<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.sql.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Using mysql database</title>
</head>
<body>
<%
	
	String url = "jdbc:mysql://localhost:3306/bhanu";
	String username = "root";
	String password = "wicore123";
	String query = "select * from student where id = 3";
	Class.forName("com.mysql.jdbc.Driver");
	Connection con = DriverManager.getConnection(url,username,password);
	Statement st = con.createStatement();
	ResultSet rs = st.executeQuery(query);
	rs.next();
	
%>

id : <%=rs.getInt(1) %><br>
student name : <%=rs.getString(2) %> <br>
course : <%=rs.getString(3) %><br>
fee : <%=rs.getString(4) %> 
</body>
</html>