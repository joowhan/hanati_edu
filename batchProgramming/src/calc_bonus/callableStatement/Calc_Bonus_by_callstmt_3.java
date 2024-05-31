/*
 * 파일: Calc_Bonus_by_callstmt_3.java
 * 작성일: 2024.05.31
 * 주제: 1개의 sql을 활용하여 데이터 처리
 * ******************** Transaction 단위 *********************
 * Insert into table ~ Select 구문을 사용해서 한번에 처리
 * ************************* SQL 검증 *************************
 * 대상 데이터의 전체 ROWS 와 INSERT 된 ROWS 비교
 * */
package calc_bonus.callableStatement;

import connection.JdbcConnectionVM;
import utils.ExceptionLog;
import utils.ValidattionVM;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Calc_Bonus_by_callstmt_3 {
    public static void main(String[] args) {
        Connection connection = null;
        PreparedStatement pstmt = null;
        CallableStatement cstmt = null;


        //truncate table sql
        String truncate_sql ="truncate table bonus_coupon ";

        // PL/SQL
        // PL/SQL로 대상 데이터 조회 + 보너스 계산 + insert
        String sql ="INSERT INTO BONUS_COUPON(YYYYMM, CUSTOMER_ID, EMAIL, COUPON, CREDIT_POINT, SEND_DT)\n" +
                "WITH TARGET_TABLE AS (\n" +
                "    SELECT ID, EMAIL, CREDIT_LIMIT, CREDIT_POINT, COUPON\n" +
                "    FROM (\n" +
                "        SELECT ID, EMAIL, CREDIT_LIMIT, CREDIT_LIMIT AS CREDIT_POINT,\n" +
                "            CASE\n" + // 쿠폰 계산 처리
                "                WHEN CREDIT_LIMIT < 1000 THEN 'AA'\n" +
                "                WHEN CREDIT_LIMIT BETWEEN 1000 AND 2999 THEN 'BB'\n" +
                "                WHEN (CREDIT_LIMIT BETWEEN 3000 AND 3999) AND  (ADDRESS1 LIKE '%송파구 풍납1동%' AND GENDER='F') THEN 'C2'\n" +
                "                WHEN CREDIT_LIMIT BETWEEN 3000 AND 3999 THEN 'CC'\n" +
                "                WHEN  CREDIT_LIMIT >= 4000 THEN 'DD' \n" +
                "            END AS COUPON\n" +
                "        FROM CUSTOMER\n" +
                "        WHERE ENROLL_DT >='2013/01/01')\n" + // 2013년 이후 고객 초회
                "    WHERE COUPON IS NOT NULL)\n" +
                "SELECT '202406' AS YYYYMM, T.ID, T.EMAIL, T.COUPON, T.CREDIT_POINT, SYSDATE AS SEND_DT\n" + // 해당 데이터 insert
                "FROM TARGET_TABLE T " ;

        try{

            //시간 측정 시작
            long startTime = System.currentTimeMillis();
            /*
            * DBMS Connection
            * */
            // connection 연결 - 서버로 연결
            // connection = JdbcConnectionServer.dbConnected();

            // connection 연결 - 가상머신으로 연결
            connection = JdbcConnectionVM.dbConnected();

            //table truncate
            pstmt = connection.prepareStatement(truncate_sql);
            // query 실행
            pstmt.executeUpdate();
            System.out.println("table truncated!");

            cstmt = connection.prepareCall(sql);
            cstmt.execute();
            // commit
            connection.commit();
            // 시간 측정 종료
            long endTime = System.currentTimeMillis();
            // 처리한 row 수
            System.out.println("*********************************************************************************************************");
            System.out.println("경과 시간: "+(endTime-startTime)+"ms");

            // 검증 process
            int inserted = ValidattionVM.insertValidation();
            System.out.println("실제 insert된 rows: "+inserted);
            ValidattionVM.countBonusGroup();
            System.out.println("*********************************************************************************************************");

        } catch (SQLException e) {
            ExceptionLog.writeLog(e, Calc_Bonus_by_callstmt_3.class.getName());
            System.out.println("[EXCEPTION]: class not found exception: "+e.getMessage());
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            ExceptionLog.writeLog(e, Calc_Bonus_by_callstmt_3.class.getName());
            System.out.println("[EXCEPTION]: class not found exception: "+e.getMessage());
            throw new RuntimeException(e);
        }  finally {
            try{
                if(cstmt != null) cstmt.close();
                if(pstmt != null) pstmt.close();
                connection.close();

            } catch (SQLException e) {
                ExceptionLog.writeLog(e, Calc_Bonus_by_callstmt_3.class.getName());
                System.out.println("[EXCEPTION]: class not found exception: "+e.getMessage());
                e.printStackTrace();
            }
        }

    }
}
