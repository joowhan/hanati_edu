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
 	String pwd = request.getParameter("password");
 	
 	if(id.equals("test") && pwd.equals("1234")){
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