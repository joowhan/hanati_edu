
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%@ page import="java.util.Enumeration" %>
	<%
	
	session.setAttribute("memberId", "admin");
	String id = (String)session.getAttribute("memberId");
	
	Enumeration enumeration = session.getAttributeNames();
	
	while(enumeration.hasMoreElements()){
		String name = enumeration.nextElement().toString();
		String value = session.getAttribute(name).toString();
		out.println(name +" " +value);
	}
	
	session.removeAttribute("memberId");
	
	session.invalidate();

	%>
</body>
</html>