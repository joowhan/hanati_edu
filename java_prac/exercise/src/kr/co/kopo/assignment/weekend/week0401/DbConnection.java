package kr.co.kopo.assignment.weekend.week0401;
import oracle.jdbc.driver.OracleDriver;

import java.sql.*;
public class DbConnection {
    private static final String WALLET_URL = "jdbc:oracle:thin:@";
    private static final String ORA ="(description= (retry_count=20)(retry_delay=3)(address=(protocol=tcps)(port=1522)(host=adb.ap-seoul-1.oraclecloud.com))(connect_data=(service_name=g56e711c2a2b221_dinkdb_medium.adb.oraclecloud.com))(security=(ssl_server_dn_match=yes)))";
    private static final String USER_ID ="DA2405";
    private static final String USER_PWD ="Data2405";


    public static Connection dbConnected() throws SQLException {

        DriverManager.registerDriver(new OracleDriver());
        Connection connection = DriverManager.getConnection(WALLET_URL+ORA, USER_ID, USER_PWD);
        System.out.println("데이터베이스 연결 성공");
        return connection;
    }
}
