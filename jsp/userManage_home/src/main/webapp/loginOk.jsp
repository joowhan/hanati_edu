<%@page import="kr.co.kopo.usermanage_home.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<jsp:useBean id="memberDTO" class="kr.co.kopo.usermanage_home.MemberDTO" scope="application"></jsp:useBean>

<%
	String id = request.getParameter("id");
	String password = request.getParameter("password");
	
	MemberDAO memberDAO = new MemberDAO();
	
	

%>

</body>
</html>