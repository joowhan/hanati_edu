
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="./css/list.css">
</head>
<body>
	<div><button class="button">글 작성</button></div>
	
	<table class="board_table">
		<thead>
			<tr>
				<th>번호</th>
				<th>작성자</th>
				<th>제목</th>
				<th>날짜</th>
				<th>조회수</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list}" var="mbDto">
				<tr>
					<td>${mbDto.getNbBoard()}</td>
					<td>${mbDto.getNmWriter()}</td>
					<td><a href="./readDeail.do?num=${ mbDto.getNbBoard()}">${mbDto.getNmTitle() }</a></td>
					<td>${mbDto.getDaWriter()}</td>
					<td>${mbDto.getCnHit()}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<script type="text/javascript" src="./js/list.js"></script>
</body>
</html>