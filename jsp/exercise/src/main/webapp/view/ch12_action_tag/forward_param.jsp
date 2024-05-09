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
	%>
	<%
		id = request.getParameter("id");
		password = request.getParameter("password");
	%>
	<h1>forwoard_param.jsp 입니다.</h1>
	<div>
	<p>아이디: <%=id %></p>
	<p>비밀번호: <%=password %></p>
	</div>
</body>
</html>