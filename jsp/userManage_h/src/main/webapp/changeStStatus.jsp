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
                <td>
                   <form action="updateStatus.jsp" method="post">
                        <input type="hidden" name="idUser" value="<%= member.getIdUser() %>">
                        <select name="stStatus">
                            <option value="ST01" <%= member.getStStatus().equals("ST01") ? "selected" : "" %>>ST01</option>
                            <option value="ST02" <%= member.getStStatus().equals("ST02") ? "selected" : "" %>>ST02</option>
                            <!-- 다른 상태에 대한 옵션들을 추가 -->
                        </select>
                        <button type="submit">Update</button>
                    </form>
				</td>
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