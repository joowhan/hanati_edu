package kr.co.kopo.usermanage_home;

import oracle.jdbc.driver.OracleDriver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcConnection {
	public Connection connection;
	public Statement statement;
	public ResultSet rs;
	public PreparedStatement pstmt;

	private static final String driver = "oracle.jdbc.driver.OracleDriver";
	private static final String WALLET_URL = "jdbc:oracle:thin:@";
	private static final String ORA = "(description= (retry_count=20)(retry_delay=3)(address=(protocol=tcps)(port=1522)(host=adb.ap-seoul-1.oraclecloud.com))(connect_data=(service_name=g56e711c2a2b221_dinkdb_medium.adb.oraclecloud.com))(security=(ssl_server_dn_match=yes)))";
	private static final String USER_ID = "DA2405";
	private static final String USER_PWD = "Data2405";

	public JdbcConnection() {
		try {
			DriverManager.registerDriver(new OracleDriver());
			connection = DriverManager.getConnection(WALLET_URL + ORA, USER_ID, USER_PWD);
			System.out.println("DB 연결 성공!");
		} catch (SQLException e) {
			System.out.println("DB 연결 실패!");
			e.printStackTrace();
		}
	}
}
