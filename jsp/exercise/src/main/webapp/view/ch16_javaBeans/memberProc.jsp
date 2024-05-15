<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="beans.MemberBean" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<jsp:useBean id="memberBean" class="beans.MemberBean" scope="application"/>
	
	<jsp:setProperty property="id" name="memberBean" />
	<jsp:setProperty property="password" name="memberBean" />
	<jsp:setProperty property="name" name="memberBean" />
	<jsp:setProperty property="email" name="memberBean" />
	<jsp:forward page="memberConfirm.jsp"></jsp:forward>
</body>
</html>