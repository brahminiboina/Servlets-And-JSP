<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.text.*,java.util.*,java.sql.Driver, java.sql.DriverManager,java.sql.*,javax.sql.*,javax.naming.*,java.io.*,java.net.URLEncoder,com.Jsp.bean.Emp,com.Jsp.dao.ConnDAO,com.Jsp.dao.EmpDAO" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
    	<meta http-equiv="X-UA-Compatible" content="ie=edge">
    	
    	<title>User Registration</title>

    	<!-- Font Icon -->
    	<link rel="stylesheet" href="fonts/material-icon/css/material-design-iconic-font.min.css">

    	<!-- Main css -->
    	<link rel="stylesheet" href="css/style.css">
    	
		<meta name="robots" content="noindex, follow">
		
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
	
			var fn = document.getElementById("Firstname").value;
			if(fn == ""){
			
				alert("Please enter first name");
				return false;
			
			}
	
			var ln = document.getElementById("Lastname").value;
			if(ln == ""){
			
				alert("Please enter last name");
				return false;
			
			}
			var mbno = document.getElementById("Mobilenumber").value;
			if(mbno == ""){
			
				alert("Please enter your mobile number");
				return false;
				
			}	
	
			var email = document.getElementById("email").value;
			if(email == ""){
			
				alert("Please enter your email in the format of ---@--.com");
				return false;
					
			}
			
			var username = document.getElementById("Username").value;
			if(username == ""){
			
				alert("Please enter user name");
				return false;
			
			}
	
			var password = document.getElementById("password").value;
			if(password == ""){
			
				alert("Please enter  password");
				return false;
			
			}
			var cpassword = document.getElementById("re_password").value;
			if(cpassword != password ){
			
				alert("Please confirm your  password");
				return false;
			
			}
			alert("Congrats!!! Your Registration is successful");
			}
	
		</script>
		
	</head>
<body>
	<form action = "insert.jsp">
	
		<div class="main">

        <section class="signup">
            <!-- <img src="images/signup-bg.jpg" alt=""> -->
            <div class="container">
                <div class="signup-content">
                    <form method="POST" id="signup-form" class="signup-form">
                        <h2 class="form-title">New User Registration</h2>
                            <div class="form-group">
						<!-- 	<span class="label-input100">Username</span> -->
                            <input type="text" class="form-input" name="FirstName" id="Firstname" placeholder="First Name"/>
                        </div>
						<div class="form-group">
                            <input type="text" class="form-input" name="LastName" id="Lastname" placeholder="Last Name"/>
                        </div>
						<div class="form-group">
                            <input type="text" class="form-input" name="MobileNumber" id="Mobilenumber" placeholder="Mobile number"/>
                        </div>
                        <div class="form-group">
                            <input type="email" class="form-input" name="EmailId" id="email" placeholder="Your Email"/>
                        </div>
						<div class="form-group">
                            <input type="text" class="form-input" name="UserName" id="Username" placeholder="User Name"/>
                        </div>
                        <div class="form-group">
                            <input type="text" class="form-input" name="Password" id="password" placeholder="Password"/>
                            <span toggle="#password" class="zmdi zmdi-eye field-icon toggle-password"></span>
                        </div>
                        <div class="form-group">
                            <input type="password" class="form-input" name="CPassword" id="re_password" placeholder="Repeat your password"/>
                        </div>
						<div class="form-group">
                            <label for="agree-term" class="label-agree-term"> login as an admin account ?<a href="http://202.65.157.186/empb/login.jsp" class="term-service">Login</a></label>
                          
                            <label for="agree-term" class="label-agree-term text-align"> Already have an account ?<a href="http://202.65.157.186/empb/loginprocess.jsp" class="term-service">Signin</a></label>
                        </div>
                      
                        <div class="form-group">
                            <input type="submit" name="submit" id="submit" onclick="return reg()" class="form-submit" value="SUBMIT"/>
                        </div>
                    </form>
					
                </div>
            </div>
        </section>

    </div>
	</form>		

</body>
</html>