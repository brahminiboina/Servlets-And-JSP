<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.text.*,java.util.*,java.sql.Driver, java.sql.DriverManager,java.sql.*,javax.sql.*,javax.naming.*,java.io.*,java.net.URLEncoder,com.Jsp.bean.Emp,com.Jsp.dao.ConnDAO,com.Jsp.dao.EmpDAO" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
    	<meta http-equiv="X-UA-Compatible" content="ie=edge">
    
		<title>change password</title>
		
		<!-- Font Icon -->
    	<link rel="stylesheet" href="fonts/material-icon/css/material-design-iconic-font.min.css">

    	<!-- Main css -->
    	<link rel="stylesheet" href="css/style.css">
		<meta name="robots" content="noindex, follow">
		
		<!-- JS -->
    	<script src="js/jquery.min.js"></script>
    	<script src="js/main.js"></script>
		<!-- Global site tag (gtag.js) - Google Analytics -->
		<script async src="https://www.googletagmanager.com/gtag/js?id=UA-23581568-13"></script>
		<script>
 		 	window.dataLayer = window.dataLayer || [];
  			function gtag(){dataLayer.push(arguments);}
  			gtag('js', new Date());

  			gtag('config', 'UA-23581568-13');
		</script>
<script defer src="https://static.cloudflareinsights.com/beacon.min.js" data-cf-beacon='{"rayId":"65d9625d1d50c92c","token":"cd0b4b3a733644fc843ef0b185f98241","version":"2021.5.2","si":10}'></script>
		
	
		<script>
			function reg(){
				var username = document.getElementById("username").value;
				if(username == ""){
		
					alert("Please enter  username");
					return false;
				
				}
				var password = document.getElementById("password").value;
				if(password == ""){
				
					alert("Please enter current password");
					return false;
				
				}
				var npassword = document.getElementById("Npassword").value;
				if(npassword == ""){
				
					alert("Please enter new  password");
					return false;
				
				}
				var cpassword = document.getElementById("cpassword").value;
				if(cpassword != npassword ){
				
					alert("New password and confirm password doesn't match");
					return false;
		
				}
				//alert("Congrats!!! Your Password is changed successfully");
			}
	
</script>

</head>
<body>
<form action = "changepassword.jsp">
<div class="main">

        <section class="signup">
            <!-- <img src="images/signup-bg.jpg" alt=""> -->
            <div class="container">
                <div class="signup-content">
                    <form method="POST" id="signup-form" class="signup-form">
                        
<%
try{
	String username = request.getParameter("UserName");
	String Password = request.getParameter("Password");
	String Newpass = request.getParameter("NPassword");
	String conpass = request.getParameter("CPassword");
	String pass = "";
	String un = "";
	Connection con =  ConnDAO.getcon();
	Statement st = con.createStatement();
	ResultSet rs = null;
 	rs = st.executeQuery("select * from credentials where c_password= '"+ Password + "' and c_user_name = '"+username+"'");
	if (rs.next()) { 
		 pass = rs.getString("c_password");
		 un = rs.getString("c_user_name");
		 System.out.println("old password"+rs.getString("c_password"));
	} 
	
		if(Newpass != null && Newpass.equals(conpass))
		{
			if(un != null && un.equals(username))
			{
				if (pass != null && pass.equals(Password))
				{
					st = con.createStatement();
					int i = st.executeUpdate("update credentials set c_password ='"+ Newpass + "' where c_password= '"+ Password + "' and c_user_name = '"+username+"'");
					%>
					
					<%st.close();
					con.close();
					response.sendRedirect("loginprocess.jsp?ch_msg=Password changed successfully");
				} else {%>
				
					<center><font color=red"> <%="Old Password doesn't match" %></font></center>
                   
					<% }
			}else{%>
				<center><font color=red"> <%="Please enter Valid User Name" %></font></center>
			
			<% 	}
			
		}else{%>
	
		
	<% 	}
		
} catch (Exception e) {
	e.printStackTrace();
	System.out.println(e);
}
%>
<h2 class="form-title">Change Password </h2>
                            <div class="form-group">
						<!-- 	<span class="label-input100">Username</span> -->
                            <input type="text" class="form-input" name="UserName" id="username" placeholder="User Name"/>
                        </div>
						                        <div class="form-group">
                            <input type="password" class="form-input" name="Password" id="password" placeholder="Current Password"/>
                            <span toggle="#password" class="zmdi zmdi-eye field-icon toggle-password"></span>
                        </div>
						<div class="form-group">
                            <input type="password" class="form-input" name="NPassword" id="Npassword" placeholder="New Password"/>
                            <span toggle="#Npassword" class="zmdi zmdi-eye field-icon toggle-Npassword"></span>
                        </div>
						<div class="form-group">
                            <input type="password" class="form-input" name="CPassword" id="cpassword" placeholder="Confirm Password"/>
                            <span toggle="#cpassword" class="zmdi zmdi-eye field-icon toggle-cpassword"></span>
                              </div>                       
						
                      
                        <div class="form-group">
                            <input type="submit" style = "cursor:pointer" onclick="return reg()" class="form-submit" value="SUBMIT"/>
                            <p>
								<a href="loginprocess.jsp">goto login page</a><br>
		
                        </div>
                    </form>
					
                </div>
            </div>
        </section>

    </div>


</form>
</body>
</html>