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
			<!-- Sign up form -->
			<div id="sign-up-container">
				<h3>Get Started</h3>
				<form action="./joinOk.jsp" method="post"
					onsubmit="return validatePassword();">

					<label for="nmUser">이름</label> <input type="text" name="nmUser"
						id="nmUser" required="required" /> <label for="idUser">아이디:</label>
					<input type="text" id="idUser" name="idUser" required="required"
						title="영문자, 숫자 5~15자리" pattern="[a-zA-Z0-9]{5,15}"> <label
						for="nmPaswd">비밀번호</label> <input type="password" id="nmPaswd"
						name="nmPaswd" required="required"
						title="영문자(대문자/소문자 1 이상 포함)+숫자(1 이상 포함), 5 ~15자리"
						pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-Z\d]{5,15}$">
					<label for="confirmPassword">비밀번호 확인</label> <input type="password"
						id="confirmPassword" name="confirmPassword" required="required">
					<label for="noMobile">연락처</label> <input type="text" id="noMobile"
						name="noMobile" pattern="(010)-\d{3,4}-\d{4}"
						title="형식 010-0000-0000" required="required"> <label
						for="nmEmail">이메일</label> <input type="email" id="nmEmail"
						name="nmEmail" required="required"> <label
						for="cdUserType">권한</label>
					<div class="user_type">
						<input type="radio" name="cdUserType" value="10"
							required="required"> 사용자 <input type="radio"
							name="cdUserType" value="20" required="required"> 관리자
					</div>


					<div id="form-controls">
						<button type="submit">전송</button>
					</div>



					<input type="checkbox" name="terms" id="terms"> <label
						for="terms">I agree to the <a href="#" class="termsLink">Terms
							of service</a> and <a href="#" class="termsLink">Privacy Policy</a>.
					</label>
				</form>
			</div>
			<div id="animation-container">
				<lottie-player
					src="https://assets3.lottiefiles.com/packages/lf20_aesgckiv.json"
					background="transparent" speed="1"
					style="width: 520px; height: 520px;" loop autoplay></lottie-player>
			</div>
		</div>
	</div>
	<script
		src="https://unpkg.com/@lottiefiles/lottie-player@latest/dist/lottie-player.js"></script>
	<script>
		function validatePassword() {
			let password1 = document.getElementById("nmPaswd").value;
			let password2 = document.getElementById("confirmPassword").value;

			if (password1 !== password2) {
				alert("비밀번호가 일치하지 않습니다.");
				return false;
			}
			return true;
		}
	</script>
</body>
</html>