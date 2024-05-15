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
		out.println("서버 "+request.getServerName());
		out.println(request.getServerPort());
		out.println(request.getMethod());
		out.println(request.getProtocol());
		out.println(request.getRequestURL());
		out.println(request.getRequestURI());
	
	%>
</body>
</html>