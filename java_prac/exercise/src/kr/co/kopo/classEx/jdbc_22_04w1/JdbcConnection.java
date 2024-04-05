package kr.co.kopo.classEx.jdbc_22_04w1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcConnection {
    public static final String WALLET_URL = "jdbc:oracle:thin:@";
    public static final String ORA ="(description= (retry_count=20)(retry_delay=3)(address=(protocol=tcps)(port=1522)(host=adb.ap-seoul-1.oraclecloud.com))(connect_data=(service_name=g56e711c2a2b221_dinkdb_medium.adb.oraclecloud.com))(security=(ssl_server_dn_match=yes)))";
    public static final String USER_ID ="DA2405";
    public static final String USER_PWD ="Data2405";
    public static void main(String[] args) throws SQLException {
        Connection connection = null;
        try {
            //        Class.forName("oracle.jdbc.OracleDriver");
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            connection = DriverManager.getConnection(WALLET_URL+ORA, USER_ID, USER_PWD);
            System.out.println("연결 성공");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
