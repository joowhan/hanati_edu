<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>sub.jsp 페이지입니다.</h1>
	<jsp:include page="include02.jsp" flush="false"></jsp:include>
	
	<jsp:forward page="forward_param.jsp">
		<jsp:param value="test" name="id"/>
		<jsp:param value="1234" name="password"/>
	</jsp:forward>
</body>
</html>