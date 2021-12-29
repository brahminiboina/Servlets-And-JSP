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
	    
		<title>Admin form</title>
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
			function submit(){
	
			var username = document.getElementById("Uname").value;
			if(username == ""){
			
			alert("Please enter user name");
			return false;
			
			}
	
			var password = document.getElementById("password").value;
			if(password == ""){
			
			alert("Please enter your password");
			return false;
			
			}
			
			}
	
		</script>
		
</head>
<body>
<form action = "/empb/adminVerify.jsp">
<div class="main">

        <section class="signup">
            <!-- <img src="images/signup-bg.jpg" alt=""> -->
            <div class="container">
                <div class="signup-content">
                    <form method="POST" id="signup-form" class="signup-form">
                        <h2 class="form-title">Admin Login</h2>
                        <div class="form-group">
                            <input type="text" class="form-input" name="UserName" id="Uname" placeholder="User Name"/>
                        </div>
                        
                        <div class="form-group">
                            <input type="password" class="form-input" name="Password" id="password" placeholder="Password"/>
                            <span toggle="#password" class="zmdi zmdi-eye field-icon toggle-password"></span>
                        </div>
                      
					  
					  
                        <div class="form-group">
                            <label for="agree-term" class="label-agree-term"> <a href="forgotpassword" class="term-service">Forgot Password</a></label>
                        	<label for="agree-term" class="label-agree-term text-align"> <a href="loginprocess.jsp" class="term-service">go to home page</a></label>
                      
                        </div>
						
                        <div class="form-group">
                            <input type="submit" style = "cursor:pointer" name="submit" id="submit" onclick = "return submit()"  class="form-submit" value="Login"/>
                        </div>
                    </form>
                  <!--  <p class="loginhere">
                        Login an admin account ? <a href="#" class="loginhere-link">Login here</a>
                    </p>-->
                </div>
            </div>
        </section>

    </div>
</form>	
</body>
</html>