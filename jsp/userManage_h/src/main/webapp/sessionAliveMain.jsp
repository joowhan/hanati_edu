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
<h1><%out.println(session.getAttribute("user_id")); %>님의 페이지입니다. user page</h1>
	<div class="main_Menu">
		<button class="menu_button" id="edit_info">개인정보 관리(수정 및 조회)</button>
		<button class="menu_button" id="exit_user">회원 탈퇴</button>
		<button class="menu_button" id="logout">로그아웃</button>
	</div>
	<script src="./js/userMain.js"></script>
</body>
</html>