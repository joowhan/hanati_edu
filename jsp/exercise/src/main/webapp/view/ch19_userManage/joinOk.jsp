
<%@page import="kr.co.tlf.ex.frontcontroller.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here?</title>
</head>
<body>
	<jsp:useBean id="memberDTO" class="dto.MemberDTO" scope="application"></jsp:useBean>
	<jsp:setProperty property="*" name="memberDTO"/>
	<%
	MemberDAO mDao = new MemberDAO();
	
	
	
	
	%>
</body>
</html>