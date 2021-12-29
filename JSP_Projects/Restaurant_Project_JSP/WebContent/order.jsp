<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import = "java.util.List" %>
<%@ page import = "com.wicore.domain.MenuItem" %>

<html>
	<body>
		<jsp:include page="/header.jsp" />
		<h2>Order your food</h2>

		<form action='orderReceived' method='POST' >
		<ul>
		
		<%
		
		List<MenuItem> menuItems = (List<MenuItem>)request.getAttribute("menuItems");
		System.out.println("hii");
		for (MenuItem menuItem : menuItems) {
			System.out.println("hii");
			out.println("<li>" + menuItem + "<input type='text' name='item_" +menuItem.getId() +"' /> </li>");
		}
		 %>
		
		</ul>
		<input type='submit' />
		</form>
		
		<jsp:include page="/footer.jsp" />
	</body>
</html>