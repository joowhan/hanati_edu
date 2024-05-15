<%@page import="userManage.dto.MemberDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="userManage.dao.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="./css/table.css">
</head>
<body>
    <h1>Member List</h1>
    <table border="1" class="user_table">
        <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>password</th>
                <th>Email</th>
                <th>Phone</th>
                <th>Status</th>
                <th>User Type</th>
            </tr>
        </thead>
        <tbody>
            <% 
            
            MemberDAO mDao= new MemberDAO();
            ArrayList<MemberDTO> memberList = mDao.readAllUsers();
            if (memberList != null && !memberList.isEmpty()) {
                
                for (MemberDTO member : memberList) {
            %>
            <tr>
                <td><%= member.getIdUser() %></td>
                <td><%= member.getNmUser() %></td>
                <td><%=member.getNmPaswd() %></td>
                <td><%= member.getNmEmail() %></td>
                <td><%= member.getNoMobile() %></td>
                <td><%= member.getStStatus() %></td>
                <td><%= member.getCdUserType() %></td>
            </tr>
            <% 
                }
            } else {
            %>
            <tr>
                <td colspan="6">No members found.</td>
            </tr>
            <% } %>
        </tbody>
    </table>
    <div>
    	<button class="go_home_btn">메인으로 돌아가기</button>
    </div>
    <script type="text/javascript" src="./js/adminDetail.js"></script>
</body>
</html>