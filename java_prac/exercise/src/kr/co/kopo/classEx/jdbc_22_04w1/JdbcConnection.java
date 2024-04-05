package kr.co.kopo.classEx.jdbc_22_04w1;

import oracle.jdbc.driver.OracleDriver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JdbcConnection {
    public static final String WALLET_URL = "jdbc:oracle:thin:@";
    public static final String ORA ="(description= (retry_count=20)(retry_delay=3)(address=(protocol=tcps)(port=1522)(host=adb.ap-seoul-1.oraclecloud.com))(connect_data=(service_name=g56e711c2a2b221_dinkdb_medium.adb.oraclecloud.com))(security=(ssl_server_dn_match=yes)))";
    public static final String USER_ID ="DA2405";
    public static final String USER_PWD ="Data2405";



    public static void
    main(String[] args) throws SQLException {
        Connection connection = null;
        PreparedStatement pstmt = null;

        try {
            //        Class.forName("oracle.jdbc.OracleDriver");
            DriverManager.registerDriver(new OracleDriver());
            connection = DriverManager.getConnection(WALLET_URL+ORA, USER_ID, USER_PWD);
            System.out.println("연결 성공");
//            String sql  = new StringBuilder().append("CREATE TABLE temp AS SELECT * FROM EMP").toString();
//            pstmt = connection.prepareStatement(sql);
//            int rows = pstmt.executeUpdate();
//            String insert_sql = new StringBuilder().append("INSERT INTO TEMP").append("VALUES(?,?,?,?,?,?,?,?)").toString();
//            pstmt = connection.prepareStatement(insert_sql);
            String sql2 = "INSERT INTO USERS(ID_USER, NM_USER, ID_PASWD, NB_AGE, ID_EMAIL )"+"VALUES(?,?,?,?,?)";
            pstmt = connection.prepareStatement(sql2);
            pstmt.setString(1,"test1");
            pstmt.setString(2,"홍길동");
            pstmt.setString(3,"test1");
            pstmt.setInt(4,25);
            pstmt.setString(5,"test1@test1.com");

            int rows = pstmt.executeUpdate();
            System.out.println(rows);

            String sql3 = new StringBuilder().append("UPDATE users SET ")
                    .append("NM_USER=?,")
                    .append("ID_PASWD=?,")
                    .append("NB_AGE=?,")
                    .append("ID_EMAIL=?")
                    .append("WHERE ID_USER=\'test1\'")
                    .toString();
            pstmt = connection.prepareStatement(sql3);

            pstmt.setString(1, "joo");
            pstmt.setString(2,"test2");
            pstmt.setInt(3, 26);
            pstmt.setString(4, "test1@test2.com");

            rows = pstmt.executeUpdate();



        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (connection != null) {
                try {
                    pstmt.close();
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
