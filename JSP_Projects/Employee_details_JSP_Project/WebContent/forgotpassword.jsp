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
	    
		<title>forgot password</title>
		
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
		</script>

	</head>
	<body>
		<form action = "forgotpassword.jsp">
		
		<div class="main">

        <section class="signup">
            <!-- <img src="images/signup-bg.jpg" alt=""> -->
            <div class="container">
                <div class="signup-content">
                    <form method="POST" id="signup-form" class="signup-form">
                        <h2 class="form-title">Forgot Password </h2>
                            <div class="form-group">
						<!-- 	<span class="label-input100">Username</span> -->
                            <input type="text" class="form-input" name="UserName" id="username" placeholder="Enter Your User Name"/>
                        </div>
						                                             
						
                      
                        <div class="form-group">
                            <input type="submit" style = "cursor:pointer" onclick="return reg()" class="form-submit" value="SUBMIT"/>
                        
                        <p>
							<a href="loginprocess.jsp">goto login page</a><br>
						<p>
                        </div>
                    </form>
					
                </div>
            </div>
        </section>

    </div>
		
		
<%
try{
	String username = request.getParameter("UserName");	
	String un = "";
	Connection con =  ConnDAO.getcon();
	Statement st = con.createStatement();
	ResultSet rs = null;
 	rs = st.executeQuery("select * from credentials where  c_user_name = '"+username+"'" );
	if (rs.next()) { 
		
		 un = rs.getString("c_user_name");
		 System.out.println("old password"+rs.getString("c_password"));
	
	if(un != null && un.equals(username))
	{%>
	<center>
	<strong>
		<% out.println("Username:" +un);%> <p>
		<% String qry = "select c_password from credentials where  c_user_name = '"+username+"'" ;
		out.println("Password:" +rs.getString("c_password"));%>
		</strong></center>
		
	<%}else{%>
	<center>
		<% out.println("Please enter Valid User Name");%>
		</center>
	<%	}
	}
}catch (Exception e) {
	e.printStackTrace();
	System.out.println(e);
}
		%>
		</p>
		</div>
		</form>
</body>
</html>