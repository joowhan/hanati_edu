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
		int a;
	%>
	<%
		age = request.getParameter("age");
		a = Integer.parseInt(age);
		if(a>=20)
			response.sendRedirect("pass.jsp?age="+age);
		else
			response.sendRedirect("notpass.jsp?age="+age);
	%>
</body>
</html>