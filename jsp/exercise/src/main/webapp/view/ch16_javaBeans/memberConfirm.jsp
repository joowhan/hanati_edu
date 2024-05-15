<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
		<jsp:useBean id="memberBean" class="beans.MemberBean" scope="application"/>
		<%-- <jsp:getProperty property="*" name="memberBean"/> --%>
		아이디: <jsp:getProperty property="id" name="memberBean"/>
		비밀번호: <jsp:getProperty property="password" name="memberBean"/>
		이름: <jsp:getProperty property="name" name="memberBean"/>
		이메일: <jsp:getProperty property="email" name="memberBean"/>
</body>
</html>