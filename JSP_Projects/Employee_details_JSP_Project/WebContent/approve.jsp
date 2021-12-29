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
<% Cookie ck[]=request.getCookies(); 
		String msg = null;
 			if(ck!=null){  
			 	
				for (Cookie n: ck){
					
					System.out.print("\n Name: "+ n.getName()+"\n");
					
				
					 if(n.getName().equals("username")){
					 	msg = n.getValue();
					 }
					
				}
			}	 %>

<%
if(msg!=null){
	Connection con = ConnDAO.getcon();
	System.out.println("ContentIds : ");
	String[] c_ids = request.getParameterValues("c_id");
	PreparedStatement ps = null;
	for(int i=0;i<c_ids.length;i++){
		String approveStr = ""; 
    	String c_id = c_ids[i];
    	System.out.println("ContentIds : "+c_ids[i]);

    	//Update logic
    	String approveVal = request.getParameter(c_id+"_approved");
    	System.out.println("ApprovedVal : "+approveVal);
    	if(approveVal!=null && approveVal.equalsIgnoreCase("Yes")) {
    	
    		System.out.println("ApprovedVal : "+approveVal);
    		approveStr = "Y"; 		
    
    	}else{
    	
    		System.out.println("ApprovedVal : "+approveVal);
    		approveStr = "N";
    	
    	}
   		String updQry = "update credentials set _approved = '"+approveStr+"' where c_id = '"+c_id+"'";  
    	System.out.println("query : "+updQry);
		ps = con.prepareStatement(updQry);
		int j = ps.executeUpdate();
	
	
	}
	response.sendRedirect("login.jsp");
}else{
		response.sendRedirect("cookie.jsp");
		System.out.println("username:-------"+msg);
	}


%>
</form>
</body>
</html>