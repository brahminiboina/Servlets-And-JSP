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
<%
String searchTerm = request.getParameter("searchTerm");

MenuDataService menuDataService = new MenuDataService();
List<MenuItem> menuItems = menuDataService.find(searchTerm);

if (menuItems.size() > 0) {%>

	<h2>Dishes containing<%= " "+ searchTerm %> </h2><ul>
<%
	for (MenuItem menuItem : menuItems) {%>
		<li><%=" "+ menuItem + " "  + menuItem.getDescription() %></li></ul>
<%	}
}
else {%>
	
	<p>I'm sorry, there are no dishes containing<%=" "+ searchTerm %>
<%}
%>
<%@include file="footer.jsp" %>
</body>
</html>