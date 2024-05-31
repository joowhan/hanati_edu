package connection;

import oracle.jdbc.driver.OracleDriver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcConnectionVM {
    private static final String jdbcDriver="oracle.jdbc.driver.OracleDriver";
    private static final String KOPO_URL="jdbc:oracle:thin:@192.168.119.119:1521/dinkdb";
    private static final String USER_ID="scott";
    private static final String USER_PWD ="tiger";

    public static Connection dbConnected() throws SQLException, ClassNotFoundException {
        Class.forName(jdbcDriver);
        DriverManager.registerDriver(new OracleDriver());
        Connection connection = DriverManager.getConnection(KOPO_URL, USER_ID, USER_PWD);
        // 자동 commit off
        connection.setAutoCommit(false);
//        System.out.println("데이터베이스 연결 성공");
        return connection;
    }
    public static void dbUnconnected(Connection connection){
        try{
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
