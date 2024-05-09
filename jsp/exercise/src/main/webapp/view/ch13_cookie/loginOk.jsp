<%@page import="java.nio.file.LinkOption"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%!
		String id, password;
		String storedId = "admin";
		String storedPwd= "1234";
	%>

	<% 
	id = request.getParameter("id");
	password = request.getParameter("password");
	
	if(id.equals(storedId) && password.equals(storedPwd)){
	%>
	<jsp:forward page="welcome.jsp"></jsp:forward>
	<%
	}
	else{
		response.sendRedirect("login.jsp");
	}
	%>
</body>
</html>