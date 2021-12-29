<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  
 <%@ page import="java.text.*,java.util.*,java.sql.Driver, java.sql.DriverManager,java.sql.*,javax.sql.*,javax.naming.*,java.io.*,java.net.URLEncoder, com.Jsp.dao.ConnDAO" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<meta charset="UTF-8">
    	<meta name="viewport" content="width=device-width, initial-scale=1.0">
    	<meta http-equiv="X-UA-Compatible" content="ie=edge">
		<title>Employee details</title>
		
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
		<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
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
		function verify(){
		alert("Im verifying");
		var x=confirm('Are you sure you want to delete?');
			if (x) {
		 		//form.action='/JSP_pro/View.jsp?action=delete&emp_id='+emp_id;
		 	} 
			else {
				return false;
			}
		}
		 </script>
		
	
		
	</head>
	
	<body>
	
		
		<% 
		 try{
		Cookie ck[]=request.getCookies(); 
		String msg = null;
 			if(ck!=null){  
			 	
				for (Cookie n: ck){
					
					System.out.print("\n Name: "+ n.getName()+"\n");
					
				
					 if(n.getName().equals("username")){
					 	msg = n.getValue();
					 }
					
				}%>
				 <%if(msg == null) response.sendRedirect("cookie.jsp"); %>
					
				
	<div class="header">
  		<a href="#default" class="logo">WICORE LABS</a>
  			<div class="header-right">
    
    			<div class="dropdown">
  <img src="css/signup-bg.jpg" class="dropbtn">
  <div class="dropdown-content">
    <a href="#"><%=msg%></a>
    <p style="margin-left:0.5em"><a href="logout.jsp">logout</a></p><br>
   <a href="changepassword">change password</a>
  </div>
</div>
  			</div>
	</div>
		<div class="main">

      
            <!-- <img src="images/signup-bg.jpg" alt=""> -->
            <div class="container1">
                <div class="signup-content">
                    <form method="POST" id="signup-form" class="signup-form" action = "logout.jsp">
                        <h2 class="form-title">Employee Details</h2>
						<label for="agree-term" class="label-agree-term text-align"> <a href="http://202.65.157.186/empb/AddEditEmp.jsp?action=ADD" class="term-service">
						<span class="glyphicon glyphicon-plus-sign"></span>
						ADD NEW
						</a>
						</label>
						
                        
		
 			
			
      <%
     
        		Connection con = null;
        		 Class driver_Class = Class.forName("com.mysql.jdbc.Driver");
        		
        		Driver driver = (Driver) driver_Class.newInstance();
				con = (Connection) DriverManager.getConnection("jdbc:mysql://202.65.157.186/Project_db", "cms", "Samsung@w1c0r3");
        		
				PreparedStatement ps = null;
 	       		ResultSet rs = null;
          		String query = "select emp_id, firstname, lastname, gender, phonenumber, email, state, Address, Employeedesignation from empdetails";
          		 ps = con.prepareStatement(query);
          		 rs = ps.executeQuery();
        
        		if(rs.next()) {

					rs.beforeFirst();
			
        	%>
       				<div class="form-group">
        			<table class="rtable" border=1 width=10% height=10% align="center">
        			  <thead>
        			      	<tr>
        					<th>emp_id</th>
        					<th>firstName</th>
        					<th>LastName</th>
        					<th>Gender</th>
        					<th>PhoneNumber</th>
        					<th>Email</th>
        					<th>State</th>
        					<th>Address</th>
        					
        					<th>Employeedesignation</th>
        					<th>ACTION</th>
        					
        					
        				</tr>
        				</thead>
        				
        				
         				<% while (rs.next()) { %>
         				<%int emp_id = rs.getInt("emp_id"); %>
         				<tbody>
         				<tr>
         					<td><%=rs.getInt("emp_id")%></td>
           					<td><%=rs.getString("firstname")%></td> 
            				<td><%=rs.getString("lastname")%></td>
            				<td><%=rs.getString("gender")%></td>
              				<td><%=rs.getString("phonenumber")%></td>
               				<td><%=rs.getString("email")%></td>
                			<td><%=rs.getString("state")%></td>
               	 			<td><%=rs.getString("Address")%></td>
                			<td><%=rs.getString("Employeedesignation")%></td>
                			<td><a href = 'http://202.65.157.186/empb/AddEditEmp.jsp?action=edit&emp_id=<%=emp_id %>'><i class="fa fa-edit fa-1g" style="margin-left:0.5em"></i></a><a href = 'http://202.65.157.186/empb/AddEditEmp.jsp?action=delete&emp_id=<%=emp_id%>'><span class=\"glyphicon glyphicon-trash\" onclick="if (confirm('Are you sure you want to delete?')) { form.action='/empb/View.jsp?action=delete&emp_id=<%=emp_id %>'; } else { return false; }"><i class="fa fa-trash fa-lg" style="margin-left:0.8em"></i></span></a></td>
                			</tr>
                			<%System.out.println("emp_id is"+emp_id); %>
                		
          				<% } %>
          		</tbody>
          			</table>
          			</div>
          			
                    </form>
				</div>
				</div>
				
           
        </section>
        </div>

 
          	
        	<%} 
 			else { %>
        	
            		<p><h2>No records found</h2></p>
           
       		 <%}
 			
 				 
        		if (rs!=null) {rs.close();rs=null;}
         	if (ps!=null) {ps.close();ps=null;}
         	if (con!=null) {con.close();con=null;}
 			
 			}
         	else{ 
 				response.sendRedirect("cookie.jsp");
 			
 		    	out.print("Please login first");  
 			    
 			}
 			}catch(Exception e){
 				e.printStackTrace();
 			}finally {
				
 				Connection con = null;
 				Connection Con = (Connection) ConnDAO.closecon();
 				/*try {
 					Connection con = null; 
		            if (con != null)
		            	
		               con.close();
		            
		            System.out.println("Connection is closed successfully:");
		            
		         } catch (SQLException se) {
		        	 
		            se.printStackTrace();
		            
		         }*/
		         
			}
 			
 			
 			
 			
 		%>	
        
 			
        		
        	

		
	
	</body>
</html>           
                