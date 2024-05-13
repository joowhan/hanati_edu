<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:set var="username" value="홍길동"></c:set>
	<c:out value="${username}"></c:out>
	<c:catch var="exception"></c:catch>
	<c:if test="${username}"></c:if>
		<p>hello</p>

	<c:forEach var="item" begin="1" end="9" step="1" varStatus="status">
		<c:forEach begin="1" end="9" step="1" var="item2" varStatus="status2">
			<p>${item} * ${item2} = ${item* item2}  </p>
		</c:forEach>
	</c:forEach>
	
	<%-- <c:redirect url="https://www.naver.com"></c:redirect> --%>
	
	
	
	
</body>
</html>