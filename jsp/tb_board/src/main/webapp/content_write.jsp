<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="content_stored.do" method="post">
		<label for="nmWriter">작성자</label> <input type="text" name="nmWriter"
			id="nmWriter" /> <label for="nmTitile">제목</label> <input type="text"
			name="nmTitle" id="nmTitle" /> <label for="nmContent">내용</label>
		<textarea rows="5" cols="30" id="nmContent" name="nmContent"></textarea>

		<div>
<!-- 			<button  class="go_home_btn">저장</button> -->
		</div>
		<input type="submit" value="저장">
	</form>
	<script type="text/javascript" src="./js/content.js"></script>
</body>
</html>