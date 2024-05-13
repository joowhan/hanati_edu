<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="./css/login.css">
</head>
<body>


	<div id="form-container">
		<div id="form-inner-container">
			<div id="sign-in-container">
				<form action="./loginOk.jsp" method="post">
					<label for="id">아이디:</label> <input type="text" id="id" name="id" required="required">
					<label for="비밀번호">비밀번호: </label> <input type="password"
						id="password" name="password" required="required">

					<div id="form-controls">
						<button type="submit">로그인</button>
						
					</div>
					<input type="checkbox" name="terms" id="terms"> <label
						for="terms">I agree to the <a href="#" class="termsLink">Terms
							of service</a> and <a href="#" class="termsLink">Privacy Policy</a>.
					</label>
				</form>
				<div class="page_link">
				<a href="join.jsp">회원가입</a> <a href="userMain.jsp">메인화면</a>
				</div>
			</div>
			<div id="animation-container">
				<lottie-player
					src="https://assets3.lottiefiles.com/packages/lf20_aesgckiv.json"
					background="transparent" speed="1"
					style="width: 520px; height: 520px;" loop autoplay></lottie-player>
			</div>
		</div>
	</div>
	<script src="https://unpkg.com/@lottiefiles/lottie-player@latest/dist/lottie-player.js"></script>
</body>
</html>