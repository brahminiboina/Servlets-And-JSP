<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.text.*,java.util.*,java.sql.Driver, java.sql.DriverManager,java.sql.*,javax.sql.*,javax.naming.*,java.io.*,java.net.URLEncoder,com.Jsp.bean.Emp,com.Jsp.dao.ConnDAO,com.Jsp.dao.EmpDAO" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
Connection con = ConnDAO.getcon();

String firstname = request.getParameter("FirstName");
String lastname = request.getParameter("LastName");
String mobileno = request.getParameter("MobileNumber");
String emailid = request.getParameter("EmailId");
String username = request.getParameter("UserName");
String password = request.getParameter("Password");

//String action = request.getParameter("action");
//String c_id = request.getParameter("emp_id");
try{
	PreparedStatement pst = null;
	pst = (PreparedStatement) con.prepareStatement("insert into credentials(c_first_name,c_last_name,c_mobile_no,c_email_id,c_user_name,c_password) values(?,?,?,?,?,?)");
	pst.setString(1,firstname);
	pst.setString(2,lastname);
	pst.setString(3,mobileno);
	pst.setString(4,emailid);
	pst.setString(5,username);
	pst.setString(6,password);
	//pst.setString(7,"user");
	int i = pst.executeUpdate();
	
	if(i>0){
		
		System.out.println("added");
		
	} else{
		
		System.out.println("failed to add");
		
	}
	response.sendRedirect("loginprocess.jsp");
	
}catch(Exception e){
	System.out.println("PLEASE HANDLE THE ERROR--->"+e);
	e.printStackTrace();
}

%>
</form>
</body>
</html>