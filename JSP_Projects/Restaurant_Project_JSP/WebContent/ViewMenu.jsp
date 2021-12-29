<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List, java.util.Date" %>
<%@ page import= "com.wicore.data.MenuDataService, com.wicore.domain.MenuItem" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Restaurant menu</title>
</head>
<body>
<%@include file="header.jsp" %>
<h2>Menu</h2>
<!--<c:out value="hello world"></c:out> -->
<ul>
	
	<%
	List<MenuItem> menuItems = (List<MenuItem>) request.getAttribute("menuItems");
	for (MenuItem menuItem : menuItems) { %>
		<li><p><%= "  "+ menuItem+" "  %>   </p></li>
	<%}
	%>
	
	</ul>
<%@include file="footer.jsp" %>
</body>
</html>