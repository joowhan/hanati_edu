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
<jsp:setProperty property="*" name="memberDTO"/>

<%
	MemberDAO mDao = new MemberDAO();
	// System.out.println(memberDTO.getIdUser()+ " "+memberDTO.getStStatus() );
	
	mDao.updateStStatus(memberDTO);
%>
		<script>
		alert("수정 성공!");
		window.location.href = "./changeStStatus.jsp";
		</script>	
</body>
</html>