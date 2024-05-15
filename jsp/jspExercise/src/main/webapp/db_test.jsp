<%@page import="java.sql.*"%>
<%@page import="java.sql.Statement"%>
<%@page import="javax.sql.*"%>
<%@page import="javax.naming.*"%>
<%@page import="java.io.*"%>
<%@page import="java.util.*"%>
<%@page import="oracle.jdbc.driver.OracleDriver"%>


<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
</head>
<body>
<%!
    Connection connection;
    Statement statement;
    ResultSet resultSet;
    PreparedStatement pstmt;
    String driver = "oracle.jdbc.driver.OracleDriver";
    String WALLET_URL = "jdbc:oracle:thin:@";
    String ORA ="(description= (retry_count=20)(retry_delay=3)(address=(protocol=tcps)(port=1522)(host=adb.ap-seoul-1.oraclecloud.com))(connect_data=(service_name=g56e711c2a2b221_dinkdb_medium.adb.oraclecloud.com))(security=(ssl_server_dn_match=yes)))";
    String USER_ID ="DA2405";
    String USER_PWD ="Data2405";

    String query = "SELECT * FROM USERS";
%>

<%
    try{
        Class.forName(driver);
        connection = DriverManager.getConnection(WALLET_URL+ORA, USER_ID, USER_PWD);

        statement = connection.createStatement();

        pstmt = connection.prepareStatement(query);
        resultSet = pstmt.executeQuery();
        out.println("성공했습니다~");
        resultSet.next();
        out.println(resultSet.getString(1));
    }
    catch (ClassNotFoundException e) {
        System.out.println("DB 연결에 실패하였습니다.");
        /* throw new RuntimeException(e); */
    }
    catch(SQLException e){
        e.printStackTrace();
    }





%>
</body>
</html>