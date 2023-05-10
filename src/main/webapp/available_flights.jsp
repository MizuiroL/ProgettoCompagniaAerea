<%@page import="com.mizuiro.air.model.Flight"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="mystyle.css">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link
	href="https://fonts.googleapis.com/css2?family=Noto+Serif:wght@700&display=swap"
	rel="stylesheet">
<title>Available flights</title>
</head>
<body>
	<h3>Available flights</h3>
	
	<div class="flex_options">
	<c:forEach var="flight" items="${flights}">
		<a href="SingleFlightServlet?flightId=${flight.getFlightId()}"><%@ include file="flight.jsp" %></a>
	</c:forEach>
	</div>
</body>
</html>