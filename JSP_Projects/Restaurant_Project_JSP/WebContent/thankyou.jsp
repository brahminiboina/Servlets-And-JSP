<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
<% 	
//Double total = (Double) request.getAttribute("total");
	//System.out.println("total--->"+total);
%>
		<!--  <p>Thank you - your order has been received. You need to pay $ </p>-->
			

	<c:out value="Thank you - your order has been received. You need to pay $ ${total}" ></c:out> 
<jsp:include page="footer.jsp" />

</body>
</html>