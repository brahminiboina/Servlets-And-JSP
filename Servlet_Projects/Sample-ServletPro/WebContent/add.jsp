<%@page import="java.io.PrintWriter, java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" errorPage="error.jsp"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>adding two numbers</title>
</head>
<body bgcolor="beige">
<%@include file= "header.html" %>
<%! int p = 3, %>
<%
String name = request.getParameter("name");
int a = Integer.parseInt(request.getParameter("num1"));
int b = Integer.parseInt(request.getParameter("num2"));
int k = a/b;
int c = p+k;
//int d = k/0;  //------> using  exception implicit object

//out.println(name+" is performing the additon" );%>
<marquee direction="right"><mark><%=name %></mark><%=" is performing the task " %></marquee>
<p>
<% out.println("The result is: "+k);%>
<p>
<%
out.println("The result using declaration tag is : "+c);
%>
<%! c = 24; %><p>
<% out.println("The result using 2nd declaration tag: "+c);//it takes from the service method%>
<p>
<%
//response.sendRedirect("http://www.google.com");
String driver=config.getInitParameter("dname");  
out.print("driver name(using config object) is = "+driver); 
 %> 
 <p>
<%
//response.sendRedirect("http://www.google.com");
String coun=application.getInitParameter("country");  
out.print("Country name(using application context) is = "+coun); 
 %> 
 <%
//By using session
 //session.setAttribute("user", name);
//By using PageContext
pageContext.setAttribute("user",name,PageContext.SESSION_SCOPE);
 %>
 <p>
Today is : <%=new Date() %> 
<!--  Today is: <%= java.util.Calendar.getInstance().getTime() %>  -->


  
 <p>
 <a href="show.jsp">show.jsp</a>
 
</body>
</html>