<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<a href="insert.do">insert</a>
	<hr/>
	<a href="http://localhost:8080<%= request.getContextPath() %>/update.do">update</a>
	<hr/>
	<a href="http://localhost:8080/jsp_25_2_ex1_frontex/select.do">select</a>
	<hr/>
	<a href="<%= request.getContextPath() %>/delete.do">delete</a>
	
</body>
</html>


