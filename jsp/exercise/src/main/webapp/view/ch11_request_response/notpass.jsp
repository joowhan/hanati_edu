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
		String age;
	%>
	<%
		age= request.getParameter("age");
		out.println("당신의 나이는"+ age +"미성년자 ");
	%>
</body>
</html>