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
		String id = (String)session.getAttribute("user_id");
	
		MemberDAO mDao = new MemberDAO();
		if(mDao.updateUser(memberDTO, id)){
	%>
		<script>
		alert("회원 정보 수정 성공!");
		window.location.href = "./userMain.jsp";
		</script>		
	<%
		}
		else{
	%>
			<script>
				alert("수정 실패!");
				window.location.href = "./userMain.jsp";
			</script>	
	<%
		}
	%>
</body>
</html>