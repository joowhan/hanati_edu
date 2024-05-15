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
	String id = request.getParameter("id");
	String password = request.getParameter("password");
	
	MemberDAO mDao = new MemberDAO();
	// 비밀번호가 일치한다면?
	if(mDao.getPassword(id).equals(password)){
		memberDTO = mDao.readMember(memberDTO, id);
		
		// 탈퇴한 회원이라면
		if(memberDTO.getStStatus().equals("ST02")){
	%>
			<script>
			alert("로그인 실패!");
			window.location.href = "./login.jsp";
			</script>
	<%
		}
		//정상 회원이라면
		else{
			//session active, 유효시간 30분 
			session.setAttribute("user_id", id);
			session.setMaxInactiveInterval(60*30);
			//user
			if(memberDTO.getCdUserType().equals("10")){
				response.sendRedirect("userMain.jsp");
			}
			//manager
			else{
				response.sendRedirect("adminMain.jsp");
			}
		}

	}
	else{
	%>	
		<script>
		alert("로그인 실패!");
		window.location.href = "./login.jsp";
		</script>	
	<% 
	}
	%>



</body>
</html>