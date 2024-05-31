package utils;
import connection.JdbcConnectionVM;

import java.sql.*;


public class ValidattionVM {
    public static int insertValidation(){
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "SELECT COUNT(*) FROM BONUS_COUPON";
        int insertedRows = 0;
        try {
            connection = JdbcConnectionVM.dbConnected();
            pstmt  = connection.prepareStatement(sql);
            rs = pstmt.executeQuery();
            if(rs.next()){
                insertedRows = rs.getInt(1);
            }
            return insertedRows;
        }catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public static void countBonusGroup(){
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "SELECT COUPON, COUNT(*) AS NUM " +
                "FROM BONUS_COUPON " +
                "GROUP BY COUPON";
        String coupon ="";
        int insertedRows = 0;
        try {
            connection = JdbcConnectionVM.dbConnected();
            pstmt  = connection.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while(rs.next()){
                coupon = rs.getString(1);
                insertedRows = rs.getInt(2);
                System.out.println(coupon +" : "+insertedRows);
            }

        }catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
