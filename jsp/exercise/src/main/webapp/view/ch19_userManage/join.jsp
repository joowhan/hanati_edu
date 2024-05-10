<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="joinOK.jsp" method="post">
		<label for="name">이름</label>
		<input type="text" name="name" id="name" />
		<label for="id">아이디:</label>
		<input type="text" id="id" name="id">
		<label for="비밀번호">비밀번호</label>
		<input type="password" id="password" name="password">
		<label for="phone">연락처</label>
		<input type="text" id="phone" name="phone">
		<label for="email">이메일</label>
		<input type="email" id="email" name="email">
		<button type="submit">전송</button>
	</form>
</body>
</html>