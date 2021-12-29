<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   <%@ page import="java.text.*,java.util.*,java.sql.Driver, java.sql.DriverManager,java.sql.*,javax.sql.*,javax.naming.*,java.io.*,java.net.URLEncoder,com.Jsp.bean.Emp,com.Jsp.dao.ConnDAO,com.Jsp.dao.EmpDAO" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<% 

	
	Cookie ck1[]=request.getCookies(); 
	String msg = null;
	if(ck1!=null){  
		for (Cookie n: ck1){
				System.out.print("\n Name: "+ n.getName()+"\n");%>
				 
				
			<% 	if(n.getName().equals("username")){
					 msg = n.getValue();
					  System.out.println("username:-------"+msg);	%>		 
				<% }
				
			}
	}
	if(msg!=null){	 
	
	
	 %>
<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	    <meta charset="UTF-8">
	    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	    <meta http-equiv="X-UA-Compatible" content="ie=edge">
	    
		<title>user details</title>
		
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
		
			function approve(){
				alert("Approved");
			}
		</script>

	</head>
	<body>
		<form action="approve.jsp">
			


<%String username = request.getParameter("UserName");
	String password = request.getParameter("Password"); %>

	<div class="header">
  <a href="#" class="logo">WICORE LABS</a>
  <div class="header-right">
    
    <div class="dropdown">
  <img src="css/signup-bg.jpg" class="dropbtn">
  <div class="dropdown-content">
    <a href="#"><%=msg %></a>
    <a href="logout.jsp">logout</a><br>
   <a href="changepassword">change password</a>
  </div>
</div>
  </div>
</div>
    <div class="main">

      
            <!-- <img src="images/signup-bg.jpg" alt=""> -->
            <div class="container1">
                <div class="signup-content">
                    <form method="POST" id="signup-form" class="signup-form">
                        <h2 class="form-title"> User Details</h2>

  		<%
  			try{
  		
  		Connection con =  ConnDAO.getcon();
		PreparedStatement ps = null;
 		ResultSet rs = null;
 	
  	    	String qry = "select c_id,  c_first_name, c_last_name, c_mobile_no, c_email_id, c_user_name, c_password, c_role_type, _approved from credentials where c_role_type='user'"; 
				System.out.println("Edit Query : "+qry);
				
				ps = con.prepareStatement(qry);
		 	 	rs = ps.executeQuery();
  		
  		%>
  		<div class="form-group">
  		 <table class="rtable" border=1 width=10% height=10% align="center" id = cred>
  		 <thead>
        			    	<tr>
        					<th>c_id</th>
        					
        					<th>firstName</th>
        					<th>LastName</th>
        					<th>MobileNumber</th>
        					<th>Email</th>
        					<th>username</th>
        					<th>password</th>
        					<th>roletype</th>
        					<th>approved</th>
        				
        				</tr>
        				</thead>
        				
         				<% while (rs.next()) { %>
         				
         				<% int c_id = rs.getInt("c_id"); 
         				String approved = rs.getString("_approved"); %>
         				<tr>
         					
         					<td><input type="hidden" name="c_id" value=<%=rs.getInt("c_id")%>></td>
           					<td><%=rs.getString("c_first_name")%></td> 
            				<td><%=rs.getString("c_last_name")%></td>
              				<td><%=rs.getString("c_mobile_no")%></td>
               				<td><%=rs.getString("c_email_id")%></td>
                			<td><%=rs.getString("c_user_name")%></td>
               	 			<td><%=rs.getString("c_password")%></td>
                			<td><%=rs.getString("c_role_type")%></td>
                			
                			
                			<%String approveVal = request.getParameter(c_id+"_approved"); 
                			System.out.println("approved"+approved);%>
                			
                			<td>Yes<input type="checkbox"  name=<%= rs.getInt("c_id")+"_approved"%> <%=approved.equalsIgnoreCase("Y")?"checked='checked'":""%> value = "Yes">
                			
                			</td>
                			
                			</tr>
                			
                			
                			<% System.out.println("approved"   +approved);%>
                			<%System.out.println("c_id is"+c_id);%>
                			<%	 System.out.println("approved is:---" +rs.getInt("c_id")+"_approved");	
                				//String approve = request.getParameter("approved");
            				 	//System.out.println("checkbox _approve is" +approve);%>
          
 					
		 <%} %>
		 </table>
		 	<table width="100%" border="0" align="center" cellpadding="2" cellspacing="0">
		<tr align="center">
		
		<td align="right" class="textBold" nowrap valign="right" width ="100%" ><b>Export as </b></td><td width ="8%" align="right" valign="middle" class="textBold" nowrap>
		<input type="image" style="border:none; background-color:transparent;" src="<%=request.getScheme()%>://<%=request.getServerName()%>:<%=request.getServerPort()%>/img/images/excel-icon.gif" title="Excel" name="image" alt="" onclick="return formCheck(this.form,'Excel');"/><input type="image" style="border:none; background-color:transparent;" src="<%=request.getScheme()%>://<%=request.getServerName()%>:<%=request.getServerPort()%>/img/images/word-icon.gif" title="Word" name="image" alt="" onclick="return formCheck(this.form,'Word');"/><input type="image" style="border:none; background-color:transparent;" src="<%=request.getScheme()%>://<%=request.getServerName()%>:<%=request.getServerPort()%>/img/images/pdf-icon.gif" title="PDF" name="image" alt="" onclick="return formCheck(this.form,'pdf');"/>&nbsp;<input type="image" style="border:none; background-color:transparent;" src="<%=request.getScheme()%>://<%=request.getServerName()%>:<%=request.getServerPort()%>/img/images/csv_images.jpg" title="CSV" name="image" alt="" onclick="return formCheck(this.form,'CSV');"/>&nbsp;
		</td>
		</tr></table>	 
		</div>
						                                             
						
                      
                        <div class="form-group">
                        <center>
                            <input type="submit" style = "cursor:pointer" onclick="return approve()" class="form-submit1" value="SUBMIT"/>
                        </center>
                        </div>
                    </form>
					
                </div>
           
        </section>

    </div>
		
		
		
<%
	}
catch(Exception e){
	e.printStackTrace();
}finally {
				
 				Connection con = null;
 				Connection Con = (Connection) ConnDAO.closecon();
 		}%>
 </div>
</form>

</body>
</html>

  	<%} else{
			response.sendRedirect("cookie.jsp");
	 		System.out.println("username:-------"+msg);
	 	}
			
%>
        
    
 