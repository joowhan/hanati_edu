package userManage.dao;

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

	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String WALLET_URL = "jdbc:oracle:thin:@";
	private String ORA = "(description= (retry_count=20)(retry_delay=3)(address=(protocol=tcps)(port=1522)(host=adb.ap-seoul-1.oraclecloud.com))(connect_data=(service_name=g56e711c2a2b221_dinkdb_medium.adb.oraclecloud.com))(security=(ssl_server_dn_match=yes)))";
	private String USER_ID = "DA2405";
	private String USER_PWD = "Data2405";

	public JdbcConnection() {
		try {
			Class.forName(driver);
			connection = DriverManager.getConnection(WALLET_URL + ORA, USER_ID, USER_PWD);

		} catch (ClassNotFoundException e) {
			System.out.println("DB 연결에 실패하였습니다.");
			/* throw new RuntimeException(e); */
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
