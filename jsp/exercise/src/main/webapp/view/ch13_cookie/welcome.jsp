<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	String id = request.getParameter("id");
	String pwd =request.getParameter("password");
	
	Cookie cookie = new Cookie(id, pwd);
	response.addCookie(cookie);
%>

<h1>어서오세욥</h1>

<form action="logout.jsp" method="get">
	<button type="submit" name="logout">로그아웃</button>
</form>

</body>
</html>