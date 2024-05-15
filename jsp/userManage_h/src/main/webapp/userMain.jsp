<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
		<jsp:include page="sessionAliveMain.jsp"></jsp:include>
	<%
	}
	%>
	<!-- <script src="./js/const.js"></script> -->
</body>
</html>