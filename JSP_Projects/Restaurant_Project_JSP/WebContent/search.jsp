<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Restaurant menu</title>
</head>
<body>
<%@include file="header.jsp" %>
<h2>Find your favourite dish</h2>
		<form action="SearchResults.jsp" method="get">
			Find all dishes containing : <input type="text" name="searchTerm" id="searchTerm" /> <input type="submit" value="search" />
		</form>
</body>
</html>