<%@page import="userManage.dao.MemberDAO"%>
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
	<jsp:useBean id="memberDTO" class="userManage.dto.MemberDTO"
		scope="application"></jsp:useBean>
	<%
	String id = (String) session.getAttribute("user_id");
	MemberDAO mDao = new MemberDAO();

	memberDTO = mDao.readMember(memberDTO, id);
	%>
	<div id="form-container">
		<div id="form-inner-container">
			<div id="sign-up-container">
				<h3 class="page_title">정보 조회 및 수정</h3>
				<h3>
					아이디:
					<%
				out.println(id);
				%>
				</h3>
				<form action="modifyOk.jsp" method="post">
					<label for="nmUser">이름</label> <input type="text" name="nmUser"
						id="nmUser" required="required" value=<%=memberDTO.getNmUser()%> />

					<label for="nmPaswd">비밀번호</label> <input type="password"
						id="nmPaswd" name="nmPaswd" required="required"
						title="영문자(대문자/소문자 1 이상 포함)+숫자(1 이상 포함), 5 ~15자리"
						pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-Z\d]{5,15}$"
						value=<%=memberDTO.getNmPaswd()%>> <label
						for="confirmPassword">비밀번호 확인</label> <input type="password"
						id="confirmPassword" name="confirmPassword" required="required"
						value=<%=memberDTO.getNmPaswd()%>> <label
						for="noMobile">연락처</label> <input type="text" id="noMobile"
						name="noMobile" pattern="(010)-\d{3,4}-\d{4}"
						title="형식 010-0000-0000" required="required"
						value=<%=memberDTO.getNoMobile()%>> <label
						for="nmEmail">이메일</label> <input type="email" id="nmEmail"
						name="nmEmail" required="required"
						value=<%=memberDTO.getNmEmail()%>>
					<button type="submit">수정하기</button>
					<input type="text" hidden="hidden">
				</form>
			</div>
		</div>
	</div>




</body>
</html>