package calc_bonus.statement;

import connection.JdbcConnectionServer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Calc_Bonus_by_stmt_5 {
    public static void main(String[] args) {
        Connection connection = null;
        Statement stmt = null;
        Statement stmt_insert = null;
        ResultSet rs = null;

        String customerId = "";
        String enrollDt = "";
        int creditLimit = 0;
        String address1 = "";
        String email = "";
        String gender = "";
        String coupon = "";
        int count = 0;

        // 전체 고객 데이터 조회
        String sql ="SELECT * FROM CUSTOMER " +
                    "WHERE ENROLL_DT >='2018-01-01' ";
//                   + AND ROWNUM <=100 " + // 100개의 데이터로 TEST
        try{
            //시간 측정 시작
            long startTime = System.currentTimeMillis();
            // connection 연결
            connection = JdbcConnectionServer.dbConnected();
            // 전체 데이터를 clien로 load
            stmt = connection.createStatement();
            stmt.setFetchSize(1000);
            rs = stmt.executeQuery(sql);
            stmt_insert = connection.createStatement();
            while(rs.next()){
                //데이터 저장
                customerId = rs.getString(1);
                address1 = rs.getString(5);
                creditLimit = rs.getInt(9);
                email = rs.getString(10);
                enrollDt = rs.getString(13);
                gender = rs.getString(14);
                

                // 고객 별 보너스 계산
                if(creditLimit < 1000)
                    coupon = "AA";
                else if(creditLimit >=1000 && creditLimit <=2999)
                    coupon = "BB";
                else if(creditLimit>=3000 && creditLimit<=3999) {
                    if (address1.contains("송파구 풍납1동"))
                        coupon = "C2";
                    else
                        coupon ="CC";
                } else if (creditLimit >=4000) {
                    coupon ="DD";
                }

                // System.out.println(customerId + address1 + creditLimit + email + enrollDt + gender + coupon);
                String sql2 = "INSERT INTO BONUS_COUPON(YYYYMM, CUSTOMER_ID, EMAIL, COUPON, CREDIT_POINT, SEND_DT) " +
                        "VALUES ('202406','"+customerId+"','"+ email+"','"+coupon+"',"+ creditLimit+", SYSDATE)";
                stmt_insert.executeQuery(sql2);
                count ++;

                if(count%10000==0)
                    connection.commit();
            }
            long endTime = System.currentTimeMillis();
            System.out.println("총 "+count+" rows 처리 성공, 경과 시간: "+(endTime-startTime)+"ms");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            try{
                if(rs !=null) rs.close();
                if(stmt != null) stmt.close();
                if(stmt_insert!=null)stmt_insert.close();
                connection.close();

            } catch (SQLException e) {
                e.printStackTrace();
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }

    }
}