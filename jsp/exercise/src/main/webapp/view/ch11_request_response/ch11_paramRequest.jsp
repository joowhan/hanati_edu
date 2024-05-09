<%@page import="java.util.Arrays"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%@page import ="java.util.Enumeration" %>
	
	 	<%!
		String name, id, password, major, protocol;
		String[] hobby;
	%>
	
	<%
		request.setCharacterEncoding("UTF-8");
		
		name = request.getParameter("name");
		id = request.getParameter("id");
		password = request.getParameter("password");
		major = request.getParameter("major");
		protocol = request.getParameter("protocol");
		hobby = request.getParameterValues("hobby");
		
		Enumeration parameters = request.getParameterNames();
	%>
	
	이름&nbsp;: <%=name %> <br/>
	아이디&nbsp;: <%=id %> <br/>
	비밀번호&nbsp;: <%=password %> <br/>
	취미&nbsp;: <%=Arrays.toString(hobby) %> <br/>
	전공&nbsp;: <%=major %> <br/>
	프로토콜&nbsp;: <%=protocol%> <br/>
	
</body>
</html>