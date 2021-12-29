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
    	
		<title>Login form</title>
		
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
			function login(){
	
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
<%
			//Connection con = ConnDAO.getcon();
			
		%>
<%
//String c_id =request.getParameter("c_id");
String err;
String Err_msg=request.getParameter("Err_msg")!=null?request.getParameter("Err_msg"):"";
String ch_msg=request.getParameter("ch_msg")!=null?request.getParameter("ch_msg"):"";
String f_msg=request.getParameter("f_msg")!=null?request.getParameter("f_msg"):"";
String username = request.getParameter("UserName");
String password = request.getParameter("Password");
String  remember = request.getParameter("remember");
int c_id = 1;
boolean isValidCred = false; 
try{
	Connection con =  ConnDAO.getcon();
	PreparedStatement ps = null;
 	ResultSet rs = null;
		//String qry = "select * from credentials where c_user_name = '"+username+"' and c_password = '"+password+"' and c_role_type = 'admin'"; 
		String query =" select c_id from credentials where  c_user_name = '"+username+"' and c_password = '"+password+"'  and _approved = 'Y'" ;
		System.out.println("Edit Query : "+query);
		System.out.println("Edit c_id : "+c_id);

  		ps = con.prepareStatement(query);
 	 	rs = ps.executeQuery();
 	 	
 	 	 
  	if(rs.next()) {
  		 //Valid credential
  	    isValidCred = true; 
  	  Cookie ck=new Cookie("username",username);
  		ck.setMaxAge(10*60);
      response.addCookie(ck);
      
      //Cookie ck1=new Cookie("password",password); 
      //ck1.setMaxAge(60);
      //response.addCookie(ck1);
      

	if (isValidCred) {
    //Redirect to view page
		response.sendRedirect("View.jsp");

	} else{
		
	}
  	
  	}else {
  	 %>
    
  			
 <%  }

  	

  	
}catch(Exception e){
	e.printStackTrace();
	System.out.println("error" +e);
}

%>

<form action = "loginprocess.jsp">
	
		<div class="main">

        <section class="signup">
            <!-- <img src="images/signup-bg.jpg" alt=""> -->
            <div class="container">
                <div class="signup-content">
                    <form method="POST" id="signup-form" class="signup-form">
                    
                    	<center><font color="green"><%=Err_msg %></font></center>
                    	<center><font color="green"><%=ch_msg %></font></center>
                    	
                    
                        <h2 class="form-title" >Login</h2>
                        <div class="form-group">
                        
                         <span class="glyphicon glyphicon-plus-sign"></span>
                            <input type="text" class="form-input" name="UserName"  id="Uname" placeholder="User Name"/>
                        </div>
                        
                        <div class="form-group">
                            <input type="password" class="form-input" name="Password" id="password" placeholder="Password"/>
                            <span toggle="#password" class="zmdi zmdi-eye field-icon toggle-password"></span>
                        </div>
                      
					  
					  
                        <div class="form-group">
                            <label for="agree-term" class="label-agree-term"> <a href="forgotpassword.jsp" class="term-service">Forgot Password</a></label>
                            <label for="agree-term" class="label-agree-term text-align"> <a href="register.jsp" class="term-service">Register</a></label>
                           <p> <label for="agree-term" class="label-agree-term"> <a href="changepassword.jsp" class="term-service">Change Password</a></label>
                        </div>
						<p class="loginhere">
                        Login an admin account ? <a href="login.jsp" class="loginhere-link">Login here</a>
                    </p>
                        <div class="form-group">
                            <input type="submit" style = "cursor:pointer" name="submit" id="submit" onclick = "return login()" class="form-submit" value="Login"/>
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