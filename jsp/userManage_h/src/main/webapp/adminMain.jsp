
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="./css/main.css">
</head>
<body>
	<%
	 String id = (String)session.getAttribute("user_id");
	if(id == null){
	%>
		<jsp:include page="sessionExpiredMain.jsp"></jsp:include>
	<%
	}
	else{
	%>
		<h1>admin page</h1>
	<div class="main_menu">
		<button class="menu_button" id="readUsers">회원 정보 조회</button>
		<button class="menu_button"id="changeStStatus">회원 상태 변경</button>
		<button class="menu_button"id="changeUserType">회원 권한 변경</button>
		<button class="menu_button"id="logout">로그아웃</button>
	</div>
	<script src="./js/adminMain.js"></script>
	<%
	}
	%>
	
</body>
</html>