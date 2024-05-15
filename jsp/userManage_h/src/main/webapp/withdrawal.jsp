<%@page import="userManage.dao.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:useBean id="memberDTO" class="userManage.dto.MemberDTO" scope="application"></jsp:useBean>
	
	<%
		String id = (String)session.getAttribute("user_id");
		MemberDAO mDao = new MemberDAO();
		if(mDao.withdrawal(id)){
			response.sendRedirect("logout.jsp");
		}
		else{
			response.sendRedirect("userMain.jsp");
		}
	%>
</body>
</html>