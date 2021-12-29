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
	    
	    <title>Adding and Editing the employee details</title>
	    
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
				
		<script type = "text/javascript">
				
					
					function goback(){
					
						window.history.back();
			
					}

					function add(){
			
						var firstname = document.getElementById("Firstname").value;
						if(firstname == ""){
				
							alert("Please enter your first name");
							return false;
				
						}
			
						var lastname = document.getElementById("Lastname").value;
						if(lastname == ""){
				
							alert("Please enter your last name");
							return false;
				
						}
			
						/*var gender =document.getElementById("gender").value;
						if(gender == ''){
				
							alert("select gender");
							return false;

		    			}*/
		    			var gender = document.querySelector( 'input[name="gender"]:checked');   
		    			if(gender == null) { 
		    				alert("select gender");
		    				return false;
		    			}
		    			         
			
						var phno = document.getElementById("Mobilenumber").value;
						if(phno == ""){
				
							alert("Please enter your phone number");
							return false;
				
						}
			
						var email = document.getElementById("email").value;
						if(email == ""){
				
							alert("Please enter your email in the format of ---@--.com");
							return false;
				
						}
			
						var state = document.getElementById("state").value;
						if(state == 'select'){
				
							alert("please enter your state");
							return false;
				
						}
			
						var Address = document.getElementById("Address").value;
						if(Address == ''){
				
							alert("Please select your address");
							return false;
							
						}
						
						var empdes = document.getElementById("empdes").value;
						if(empdes == 'select'){
							
							alert("please select your designation");
							return false;
							
						}
			
						//var info = "<table cellpadding='5' cellspacing='7' border='6'><tr><th>First Name</th><th>Last Name</th><th>Gender</th><th>Mobile_no</th><th>E-mail</th><th>State</th><th>Address</th><th>Designation</th></tr><tr><td>"+firstname+"</td><td>"+lastname+"</td><td>"+gender+"</td><td>"+phno+"</td><td>"+email+"</td><td>"+state+"</td><td>"+Address+"</td><td>"+empdes+"</td></tr></table>";
							
						//document.getElementById("tableinfo").innerHTML=info;
						//document.write(info);
						//document.write("<br>")
						alert("Thank You!!! You're successfully registered..");
					}
			</script>
			
			
	</head>
	<body>

		<!-- --IF CONDITION OF EDIT -->
	<%	Connection con = ConnDAO.getcon();
		String action = request.getParameter("action");
		String emp_id = request.getParameter("emp_id");
			
		if (action.equalsIgnoreCase("edit")) {%>
		<% Cookie ck[]=request.getCookies(); 
		String msg = null;
 			if(ck!=null){  
			 	
				for (Cookie n: ck){
					
					System.out.print("\n Name: "+ n.getName()+"\n");
					
				
					 if(n.getName().equals("username")){
					 	msg = n.getValue();
					 }
				}
 			}
					
				if(msg != null) {%>	
					
	<%  
			String firstname = "firstname";
	    	String lastname = "lastname";
	    	String gender = "gender";
	    	String phonenumber = "phonenumber";
	    	String email = "email";
	    	String state = "state";
	   	 	String Address = "Address";
	    	String Employeedesignation = "Employeedesignation";
	
		 	PreparedStatement ps = null;
	     	ResultSet rs = null;
    
     		String query =" select emp_id, firstname, lastname, gender, phonenumber, email, state, Address, employeedesignation from empdetails where emp_id='"+emp_id+"'";
     		System.out.println("Edit Query : "+query);
     		System.out.println("Edit emp_id : "+emp_id);
     
	      	ps = con.prepareStatement(query);
	     	rs = ps.executeQuery();
        
	      	if(rs.next()) {
	     	   System.out.println("Entered into rs,next()");
	     	   firstname = rs.getString("firstname");
	     	   lastname = rs.getString("lastname");
	     	   gender = rs.getString("gender");
	     	   phonenumber = rs.getString("phonenumber");
	     	   email = rs.getString("email");
	     	   state = rs.getString("state");
	            Address = rs.getString("Address");
	            Employeedesignation = rs.getString("Employeedesignation");
	            
	      	}
	      	
            if (rs!=null) {rs.close();rs=null;}
            if (ps!=null) {ps.close();ps=null;}	
 
	%>
	<!-- html code for edit form -->
		<form name = "f1" action = "/empb/AddNew.jsp">
		<div class="header">
  <a href="#default" class="logo">WICORE LABS</a>
  <div class="header-right">
   <div class="dropdown">
  <img src="css/signup-bg.jpg" class="dropbtn">
  <div class="dropdown-content">
    <a href="#"><%=msg %></a>
    <a href="logout.jsp">logout</a>
   <a href="changepassword">change password</a>
  </div>
</div>
  
    
  </div>
</div>
    <div class="main">

        <section class="signup">
            <!-- <img src="images/signup-bg.jpg" alt=""> -->
            <div class="container">
                <div class="signup-content">
                    <form method="POST" id="signup-form" class="signup-form">
                        <h2 class="form-title">Employee Registration </h2>
                            <div class="form-group">
						<!-- 	<span class="label-input100">Username</span> -->
                           	
                           	<input type="text" class="form-input" name="fname" id="Firstname" value="<%=firstname%>" placeholder="First Name"/>
                            </div>
                            <p>
                            <div class="form-group">
						<!-- 	<span class="label-input100">Username</span> -->
                           	
                            <input type="text" class="form-input" name="lname" id="Lastname" value="<%=lastname%>" placeholder="Last Name"/>
                        </div>
						
						<div class="form-group">
							<label  id = "gender" value="<%=gender%>"><b>Gender:  </b></label>
                            <input name="gender" id="rd1" value = "male" type="radio">
              				<label for="rd1">Male</label>
              				<input name="gender" id="rd2" value = "female" type="radio" class="">
              				<label for="rd2">Female</label>
              				<input name="gender" id="rd3" value = "others" type="radio" class="">
              				<label for="rd3">Others</label>
                        </div>
						<div class="form-group">
                            <input type="text" class="form-input" name="phoneno" id="Mobilenumber" value="<%=phonenumber%>" placeholder="Mobile number"/>
                        </div>
                        <div class="form-group">
                            <input type="email" class="form-input" name="email" id="email" value="<%=email%>" placeholder="Your Email"/>
                        </div>
						<div class="form-group">
                           
                <div class="input_field select_option">
                <%   try{
		String qry = "select s_id, s_name from state ";
		Statement st = con.createStatement();
		  rs = st.executeQuery(qry); %>
		
		 <label value="<%=state%>"><b>State</b></label>
		 <select name = "State" id = "state">
                  <option value="select"><%=state%></option>
		<% while(rs.next()){%>
			<option value="<%=rs.getString("s_name") %>"><%=rs.getString("s_name") %></option>

			<%}
		}catch(Exception e){
			e.printStackTrace();
		}
		%>

	</select>
                <div class="select_arrow"></div>
              </div>
                
                        </div>
                        <div class="form-group">
                        	<label for = "text" value="<%=Address%>"><b>Employee Address</b></label>
                            <textarea  id = "Address" name="Address" class="form-input" placeholder="Address"></textarea>
                           
                        </div>
                        <div class="form-group">
                             <div class="input_field select_option">
                	 


<%   try{
		String qry = "select d_id, d_name from Designation ";
		Statement st = con.createStatement();
		  rs = st.executeQuery(qry); %>
		
		 <label><b>Employee Designation:</b></label>
		 <select id = "empdes" name = "Employee Designation">
         <option value="select"><%=Employeedesignation%></option>
		<% while(rs.next()){%>
			<option value="<%=rs.getString("d_name") %>"><%=rs.getString("d_name") %></option>

			<%}
		}catch(Exception e){
			e.printStackTrace();
		}
		%>

	</select>
	
                <div class="select_arrow"></div>
              </div>
                        </div>
						
                      
                        <div class="form-group">
                            <input type="submit"   onclick = "return add()" class="form-submit1" value="Add"/>
							<input type="submit"  onclick = "return goback()" class="form-submit1" value="Back"/>
                        </div>
                    </form>
					<input type='hidden' name='action' value="<%=action%>">	               	
					<input type='hidden' name='emp_id' value="<%=emp_id%>">
					<div id = "tableinfo" name="tableinfo" value = "tableinfo"></div>
                </div>
            </div>
        </section>

    </div>
			   
	        </form>
	        
		<% }else{
			response.sendRedirect("cookie.jsp");
		}
 			}%>
	
		<!-- --IF CONDITION OF ADD -->
		<%

		if (action.equalsIgnoreCase("ADD")) { %>
		<% Cookie ck[]=request.getCookies(); 
		String msg = null;
 			if(ck!=null){  
			 	
				for (Cookie n: ck){
					
					System.out.print("\n Name: "+ n.getName()+"\n");
					
				
					 if(n.getName().equals("username")){
					 	msg = n.getValue();
					 }
				}
 			}
					
				if(msg != null){ %>	
					

				<!-- html code for add form -->
					<form name = "f1" action = "/empb/AddNew.jsp">
					<div class="header">
  <a href="#default" class="logo">WICORE LABS</a>
  <div class="header-right">
   <div class="dropdown">
  <img src="css/signup-bg.jpg" class="dropbtn">
  <div class="dropdown-content">
    <a href="#"><%=msg %></a>
    <a href="logout.jsp">logout</a>
    <a href="changepassword">change password</a>
  </div>
</div>
    
    
  </div>
</div>
    <div class="main">

        <section class="signup">
            <!-- <img src="images/signup-bg.jpg" alt=""> -->
            <div class="container">
                <div class="signup-content">
                    <form method="POST" id="signup-form" class="signup-form">
                        <h2 class="form-title">Employee Registration </h2>
                            <div class="form-group">
						<!-- 	<span class="label-input100">Username</span> -->
                            
                            <input type="text" class="form-input" name="fname" id="Firstname" placeholder="First Name"/>  
                            
                            <p><input type="text" class="form-input" name="lname" id="Lastname" placeholder="Last Name"/>
                        </div>
						
						<div class="form-group">
								<label name="gender" ><b>Gender:  </b></label>
                          		<input name="gender" id="rd1" type="radio" value = "male">
				              	<label for="rd1"  >Male</label>
				              	<input name="gender" id="rd2" value = "female" type="radio" class="" >
				              	<label for="rd2">Female</label>
				              	<input name="gender" id="rd3" value = "Others" type="radio" class="">
				              	<label for="rd3">Others</label>
                        </div>
						<div class="form-group">
                            <input type="text" class="form-input" name="phoneno" id="Mobilenumber" placeholder="Mobile number"/>
                        </div>
                        <div class="form-group">
                            <input type="email" class="form-input" name="email" id="email" placeholder="Your Email"/>
                        </div>
						<div class="form-group">
                           
                <div class="input_field select_option">
                <%   try{
		String qry = "select s_id, s_name from state ";
		Statement st = con.createStatement();
		 ResultSet rs = st.executeQuery(qry); %>
		
		 <label  ><b>State</b></label>
                <select name = "State" id = "state">
                  <option value="select">select</option>
		<% while(rs.next()){%>
			<option value="<%=rs.getString("s_name") %>"><%=rs.getString("s_name") %></option>

			<%}
		}catch(Exception e){
			e.printStackTrace();
		}
		%>

	</select>
                <div class="select_arrow"></div>
              </div>
                
                        </div>
                        <div class="form-group">
                            <textarea  id = "Address" name="Address" class="form-input" placeholder="Address"></textarea>
                           
                        </div>
                        <div class="form-group">
                             <div class="input_field select_option">
                            

	<%   try{
		String qry = "select d_id, d_name from Designation ";
		Statement st = con.createStatement();
		 ResultSet rs = st.executeQuery(qry); %>
		
		 <label><b>Employee Designation:</b></label>
		 <select id = "empdes" name = "Employee Designation">
       	 <option value="select">select</option>
		<% while(rs.next()){%>
			<option value="<%=rs.getString("d_name") %>"><%=rs.getString("d_name") %></option>

			<%}
		}catch(Exception e){
			e.printStackTrace();
		}
		%>

	</select>


                <div class="select_arrow"></div>
              </div>
                        </div>
						
                      
                        <div class="form-group">
                            <input type="submit"   onclick = "return add()" class="form-submit1" value="Add"/>
							<input type="submit"   onclick = "return goback()" class="form-submit1" value="Back"/>
                        </div>
                    </form>
					<input type='hidden' name='action' value= "<%=action %>">
				<div id = "tableinfo" name="tableinfo" value = "tableinfo"></div>
				<%System.out.println("end of add"); %>
                </div>
            </div>
        </section>

    </div>
				
				</form>
				
			<% }else{
				response.sendRedirect("cookie.jsp");
			}
 			}%>
			
			<%try{  
	 		if (action.equalsIgnoreCase("delete")) {%>
	 		
					
	 			<input onclick = "form.action confirm()">
	 			<% Cookie ck[]=request.getCookies(); 
					String msg = null;
 					if(ck!=null){  
			 	
					for (Cookie n: ck){
					
						System.out.print("\n Name: "+ n.getName()+"\n");
					
				
					 	if(n.getName().equals("username")){
					 	msg = n.getValue();
					 	} 
					}
					if(msg!=null){
						
					%>	
						<strong><%=msg %></strong>
					
					<%  System.out.println("update delete :-----"+ emp_id); 
					String delQry = "delete from empdetails where emp_id = '"+emp_id+"'";     
					 PreparedStatement ps = con.prepareStatement(delQry);
					 int count = ps.executeUpdate();
					 
					 
				}else{
					response.sendRedirect("/empb/cookie.jsp");
				}
					
	 		}
 					response.sendRedirect("/empb/View.jsp"); 
	 		}
	 		
			}catch(SQLException e){
				e.printStackTrace();
			}catch(Exception e1){
				e1.printStackTrace();
			}
	
			%>
	</body>
</head>
</html>