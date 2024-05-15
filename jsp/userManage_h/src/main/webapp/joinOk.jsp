<%@page import="userManage.dao.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here?</title>
</head>
<body>
	<jsp:useBean id="memberDTO" class="userManage.dto.MemberDTO" scope="application"></jsp:useBean>
	<jsp:setProperty property="*" name="memberDTO"/>
	<%
	MemberDAO mDao = new MemberDAO();
	//이미 존재하는 id라면?
	if(mDao.isExistId(memberDTO.getIdUser())){
	%>
			<script>
			alert("이미 존재하는 ID 입니다!");
			window.location.href = "./login.jsp";
			</script>
	
	<%
	}
	else if(mDao.signUp(memberDTO)){
	%>
		<script>
		alert("회원가입 성공!");
		window.location.href = "./login.jsp";
		</script>	
		
	<%
	}
	
	else{
	%>
		<script>
		alert("회원가입 실패!");
		window.location.href = "./login.jsp";
		</script>	
		
	<%
	}
	//response.sendRedirect("./login.jsp");
	
	%>
<script src="">
</script>
</body>
</html>