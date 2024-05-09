<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="./ch11_paramRequest.jsp" method="get">
		<label for="name">이름</label> <input type="text" name="name"> 
		<label for="id" >아이디</label>
			 <input type="text" name="id"> 
			 <label for="password">비밀번호</label> <input type="password" name="password">
		<div>
			<label for="hobby">취미</label> <input type="checkbox" name="hobby">
			<label for="swim">swim</label> <input type="checkbox" name="hobby" value="swim">
			<label for="cook">cook</label> <input type="checkbox" name="hobby" value="cook">
			<label for="running">running</label> <input type="checkbox"
				name="hobby" value="running"> <label for="sleeping">sleeping</label>
				<input type="checkbox" name="hobby" value="sleeping">
		</div>

		<label for="major">전공</label> 
		<input type="radio" name="major"> 
		<label for="korean">국어</label> 
		<input type="radio" name="major"> 
		<label for="english">영어</label> 
		<input type="radio" name="major"> 
		<label for="math">수학</label> 
		<div>
		<label for="protocol">프로토콜</label>
		<select name="protocol">
			<option value="http">http </option>
			<option value="ftp">ftp </option>
		</select>
		</div>
		<button type="submit" name="submit">전송</button> 
		<button type="reset" name="reset">초기화</button>
	</form>
</body>
</html>