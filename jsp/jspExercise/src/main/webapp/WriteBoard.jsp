<%--
  Created by IntelliJ IDEA.
  User: DA
  Date: 2024-04-12
  Time: 오후 3:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>WRITE</title>
</head>
<body>
    <form action="/write-board", method="get">
        제목: <input type="text" name="title" size="10"><br/>
        내용: <input type="text" name="content" size="10"><br/>
        작성자: <input type="text" name="writer" size="10">
        <input type="submit" value="전송">
    </form>
</body>
</html>
