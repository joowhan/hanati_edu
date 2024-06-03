package calc_bonus.statement;

import connection.JdbcConnectionVM;
import utils.ExceptionLog;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ExceptionTest {
    public static void main(String[] args) {
        Connection connection = null;
        PreparedStatement pstmt = null;
        String sql ="select * from non_exist_table";
        try {
            // 일부러 예외를 발생시킴
//            throw new Exception("This is a test exception.");
            connection = JdbcConnectionVM.dbConnected();
            pstmt = connection.prepareStatement(sql);
            pstmt.executeQuery();
        } catch (SQLException e) {
            // 예외를 로그 파일에 기록
            ExceptionLog.writeLog(e, ExceptionTest.class.getName());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        // 예외가 잘 기록되었는지 확인
        System.out.println("Exception logged successfully.");
    }
}
